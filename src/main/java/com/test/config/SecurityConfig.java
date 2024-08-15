package com.test.config;

import com.test.services.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UtilisateurService utilisateurService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public SecurityConfig(UtilisateurService utilisateurService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utilisateurService = utilisateurService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/api/utilisateurs/login").permitAll()
                        //.requestMatchers("/api/utilisateurs/**").permitAll()
                        //.requestMatchers("/api/utilisateurs/**", "/api/roles/**", "/api/entrepots/**").hasRole("ADMIN")
                        //.requestMatchers("/api/bonentrees/**", "/api/detailsentrees/**").hasAnyRole("ADMIN", "MANAGER")
                        //.requestMatchers("/api/bonsorties/**", "/api/detailssorties/**").hasAnyRole("ADMIN", "MANAGER")
                        //.requestMatchers("/api/produits/**").hasAnyRole("ADMIN", "MANAGER", "VENDEUR")
                        //.requestMatchers("/api/fournisseurs/**", "/api/categories/**", "/api/notifications/**").hasAnyRole("ADMIN", "MANAGER")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(utilisateurService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }
}
