package katachi.spring.execise.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated());

        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("user")
                .passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .permitAll()).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout"));

        http.headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable));

        return http.build();
    }
    @Bean
    InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user"))
            .authorities("ROLE_USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

//    @Bean
//    InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("user"))
//                .roles("GENERAL")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }

}
