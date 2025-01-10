package com.book.manager.sample_book_manager.presentation.config

import com.book.manager.sample_book_manager.application.service.AuthenticationService
import com.book.manager.sample_book_manager.application.service.security.BookManagerOperatorDetailsService
import com.book.manager.sample_book_manager.domain.enum.RoleType
import com.book.manager.sample_book_manager.presentation.handler.BookManagerAccessDeniedHandler
import com.book.manager.sample_book_manager.presentation.handler.BookManagerAuthenticationEntryPoint
import com.book.manager.sample_book_manager.presentation.handler.BookManagerAuthenticationFailureHandler
import com.book.manager.sample_book_manager.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val authenticationService: AuthenticationService
) {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers("/login").permitAll()
                .requestMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
                .anyRequest().authenticated()
        }
            .csrf { it.disable() }
            .formLogin { formLogin ->
                formLogin
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("pass")
                    .successHandler(BookManagerAuthenticationSuccessHandler())
                    .failureHandler(BookManagerAuthenticationFailureHandler())
            }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling
                    .authenticationEntryPoint(BookManagerAuthenticationEntryPoint())
                    .accessDeniedHandler(BookManagerAccessDeniedHandler())
            }
            .cors { cors -> cors.configurationSource(corsConfigurationSource()) }

        return httpSecurity.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("^https?://(localhost|127\\.0\\.0\\.1)(:[0-9]+)?\$")
        corsConfiguration.allowCredentials = true

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
        return corsConfigurationSource
    }

    @Bean
    fun userDetailsService(): BookManagerOperatorDetailsService {
        return BookManagerOperatorDetailsService(authenticationService)
    }
}
