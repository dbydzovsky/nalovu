import cz.dbydzovsky.nalovu.security.CustomAuthenticationProvider
import cz.dbydzovsky.nalovu.security.MyUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.Arrays


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
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.authenticationProvider(authProvider)
        return authenticationManagerBuilder.build()
    }

    companion object {
        const val SUB_PATHS = "*/**"
    }
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.headers().frameOptions().disable().and()
            .cors().and()
            .csrf().disable()
            .authenticationProvider(authenticationProvider())
            .authorizeRequests().antMatchers(
                "${AppPaths.API_REGISTRATION}/**",
                "${AppPaths.API_LOGIN}/**",
                "${AppPaths.API_LOGOUT}/**",
                "/js/**",
                "/css/**",
                "/img/**"
          ).permitAll().anyRequest()
            .authenticated().and()
            .logout()
            .invalidateHttpSession(true).clearAuthentication(true)
            .logoutRequestMatcher(AntPathRequestMatcher(AppPaths.API_LOGOUT)).logoutSuccessUrl("/")
            .permitAll()
        return http.build()
    }
}