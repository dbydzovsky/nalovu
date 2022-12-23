import cz.dbydzovsky.nalovu.security.CustomAuthenticationProvider
import cz.dbydzovsky.nalovu.security.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authProvider: CustomAuthenticationProvider,
    private val userDetailsService: MyUserDetailsService
){
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val auth = DaoAuthenticationProvider()
        auth.setUserDetailsService(userDetailsService)
        auth.setPasswordEncoder(passwordEncoder())
        return auth
    }
//    @Bean
//    fun authManager(http: HttpSecurity): AuthenticationManager {
//        val authenticationManagerBuilder = http.getSharedObject(
//            AuthenticationManagerBuilder::class.java
//        )
//        authenticationManagerBuilder.authenticationProvider(authProvider)
//        return authenticationManagerBuilder.build()
//    }

    companion object {
        const val SUB_PATHS = "*/**"
    }
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authenticationProvider(authenticationProvider())
        http.authorizeRequests().antMatchers(
            "/registration**", "/js/**",
            "/css/**", "/img/**"
        ).permitAll().anyRequest()
            .authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
            .invalidateHttpSession(true).clearAuthentication(true)
            .logoutRequestMatcher(AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
            .permitAll()
        return http.build()
    }
}