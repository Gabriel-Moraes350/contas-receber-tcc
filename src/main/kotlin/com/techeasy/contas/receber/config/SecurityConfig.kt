package com.techeasy.contas.receber.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE",
                        "PATCH", "OPTIONS", "HEAD")
            }
        }
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest()
                .permitAll()

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

        http.csrf().disable()

    }

//    @Bean
//    override fun userDetailsService(): UserDetailsService? {
//        val user: UserDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build()
//        return InMemoryUserDetailsManager(user)
//    }

}