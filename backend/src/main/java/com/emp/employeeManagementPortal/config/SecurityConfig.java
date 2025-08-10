package com.emp.employeeManagementPortal.config;

import com.emp.employeeManagementPortal.service.UserAuthDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    /**
     * B-Crypt Pwd encoder used for hashing
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Authenticates the Credentials while Login
     * Checks the input pwd with the encoded password stored in DB
     * @param userDetailsService it is the userDeatilsService Class
     * @param passwordEncoder it is the passwordEncoder
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserAuthDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /**
     * This is Spring Security's security feature.
     * Here the configuration of the web Application is done
     * @param http
     * @param daoAuthenticationProvider
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
        http
                /**
                 * here the given endpoints can be seen by user without LoggingIn
                 * as Spring security has its own Login pwd which has to be disabled
                 * and have our own.
                 */
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        // Public endpoints
                                        "/home", "/login", "/register","/dashboard",
                                        // Public static pages these endpoints forward/redirect to
                                        "/Home.html", "/Login.html", "/Register.html","/Dashboard.html",
                                        // Static assets (valid patterns for PathPatternParser)
                                        "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.ico",
                                        // Add other public resources if needed
                                        "/h2-console/**"

                                ).permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login") //the login endpoint
                        .loginProcessingUrl("/validate-login") // the form action name mentioned in the Login.html
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard" , true) // if login is successful
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/home") //if logout is successful, then redirect to the home page
                        .clearAuthentication(true) //Wipes the in-memory authentication info from the current security context
                        .invalidateHttpSession(true) //destroy the server sessions
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/register", "/validate-login"))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authenticationProvider(daoAuthenticationProvider);
        return http.build();

    }
}
