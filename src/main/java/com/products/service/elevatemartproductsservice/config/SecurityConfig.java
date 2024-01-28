package com.products.service.elevatemartproductsservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    private String[] authorizationRequireAdminNonIdempotentPostRequest = {
            "api/v1/attribute/save",
            "api/v1/category/save",
            "api/v1/tax/save",
            "api/v1/product/save",
    };

    private String[] authorizationRequireAdminNonIdempotentPutRequest ={
            "api/v1/product/update",
            "api/v1/product/updateSku",
            "api/v1/product/updateQuantities",
            "api/v1/product/updateImageUrl",
            "api/v1/product/uploadFile",
            "api/v1/tax/update",
            "api/v1/tax/updatePercentage",
            "api/v1/tax/updateCode",
            "api/v1/category/update",
            "api/v1/category/updateStatus",
            "api/v1/attribute/update",
            "api/v1/attribute/updateStatus",
    };
    private String[] authorizationRequireAdminIdempotentGetRequest = {
            "api/v1/attribute/fetchById",
            "api/v1/attribute/fetchAll",
            "api/v1/category/fetchById",
            "api/v1/category/fetchAll",
            "api/v1/tax/fetchById",
            "api/v1/tax/fetchAll",
//            "api/v1/product/productBySku",
            "api/v1/product/fetchAll"
    };

    private String[] authorizationRequireAdminIdempotentRequestDelete = {
            "api/v1/attribute/delete",
            "api/v1/attribute/deleteMultiple",
            "api/v1/category/delete",
            "api/v1/category/deleteMultiple",
            "api/v1/tax/delete",
            "api/v1/product/delete",
            "api/v1/product/deleteMultiple"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        RequestCache nullRequestCache = new NullRequestCache();
        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES));
        http
                .sessionManagement(sessionManger ->sessionManger.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors ->{
                    cors
                            .configurationSource(request->{
                                CorsConfiguration cfg = new CorsConfiguration();
                                cfg.setAllowCredentials(true);
                                cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                                cfg.setAllowedMethods(Collections.singletonList("*"));
                                cfg.setAllowedHeaders(Collections.singletonList("*"));
                                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                cfg.setMaxAge(3600L);
                                return cfg;
                            });
                })
                .authorizeHttpRequests(authorize->
                        authorize
                                .requestMatchers("/swagger-ui*/**","v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.POST, authorizationRequireAdminNonIdempotentPostRequest).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, authorizationRequireAdminNonIdempotentPutRequest).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,authorizationRequireAdminIdempotentRequestDelete).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,authorizationRequireAdminIdempotentGetRequest).hasAnyRole("ADMIN","USER")
                                .anyRequest().authenticated())
                .csrf(csrf->
                        csrf
                                .csrfTokenRequestHandler(requestAttributeHandler)
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthorisedUserLoggingFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(),BasicAuthenticationFilter.class)
                .requestCache((cache)->cache.
                        requestCache(nullRequestCache))
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
