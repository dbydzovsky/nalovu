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
//    @Bean
//    fun corsConfigurer(): WebMvcConfigurer {
//        return object : WebMvcConfigurerAdapter() {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/**").allowedOrigins("*")
//            }
//        }
//    }
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("*")
//        configuration.allowedMethods = listOf(
//            "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"
//        )
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.allowCredentials = true
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
//    @Bean
//    fun corsMappingConfigurer(): WebMvcConfigurer? {
//        return object : WebMvcConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/**")
//                    .allowedOrigins("http://localhost:3000","http://localhost:8080")
//                    .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD")
//                    .maxAge(3600)
//                    .allowedHeaders("Requestor-Type")
//                    .exposedHeaders("X-Get-Header")
//            }
//        }
//    }

//    @Bean
//    fun corsWebFilter(): CorsWebFilter {
//        val corsConfig = CorsConfiguration()
//        corsConfig.allowedOrigins = Arrays.asList("*")
//        corsConfig.maxAge = 3600L
//        corsConfig.addAllowedMethod("*")
//        corsConfig.addAllowedHeader("Requestor-Type")
//        corsConfig.addExposedHeader("X-Get-Header")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", corsConfig)
//        return CorsWebFilter(source)
//    }

//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
//    }
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
            "/registration**", "/js/**", "/signin**",
            "/css/**", "/img/**"
        ).permitAll().anyRequest()
            .authenticated().and()
            .logout()
            .invalidateHttpSession(true).clearAuthentication(true)
            .logoutRequestMatcher(AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
            .permitAll()
        return http.build()
    }
}