package appCitas.AppCitasSAS.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SeguridadConfig {

    @Autowired
    private PacienteDetailsServiceImpl pacienteDetailsService;

    @Autowired
    private EmpleadoDetailsServiceImpl empleadoDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider pacienteAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(pacienteDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DaoAuthenticationProvider empleadoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(empleadoDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
	        .authorizeHttpRequests(auth ->
	        auth
	            .requestMatchers("/", "/webjars/**", "/css/**", "/script/**", "/auth/**").permitAll()
	            .anyRequest().authenticated()
		    )
	        .formLogin(login ->
	        login
	            .loginPage("/auth/login") // Página de inicio de sesión común
	            .defaultSuccessUrl("/privada/home", true)
	            .loginProcessingUrl("/auth/login-post")
	        )
		    .logout(logout ->
		        logout
		            .logoutUrl("/auth/logout")
		            .logoutSuccessUrl("/")
		    );
        http.authenticationProvider(pacienteAuthenticationProvider());
        http.authenticationProvider(empleadoAuthenticationProvider());
        
        return http.build();
   }
}
