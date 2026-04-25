package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        var inMemory = new InMemoryUserDetailsManager();
//        inMemory.createUser();
//        return inMemory;
//    }e

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
//                .formLogin(f -> f.loginPage("/login").permitAll())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                })
//                .logout(logout -> logout.logoutUrl("/logout")
//                        .logoutSuccessUrl("/").permitAll())
        ;
        return http.build();
    }


}
