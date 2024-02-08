package com.silvio.discoveryservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${eureka.username}")
    private String username;
    @Value("${eureka.password}")
    private String password;

    /**
     * Metodo per la configurazione della catena di filtri di sicurezza.
     * Utilizza Spring Security per definire le regole di sicurezza per le richieste http
     *
     * @param http oggetto utilizzato per configurare la sicurezza HTTP
     * @return SecurityFilterChain configurata in base alle regole specificate
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // disabilita la protezione da attacchi CSRF
                .authorizeHttpRequests(auth ->
                        auth
                                .anyRequest() // Ogni richiesta fatta a questo servizio
                                .hasAuthority("USER") // deve avere come autorità "USER"
                ).httpBasic();

        return http.build();
    }

    /**
     * Questo metodo è utilizzato per configurare la sicurezza globale del servizio.
     * In particolare, configura un sistema di autenticazione in memoria con un singolo utente
     *
     * @param authenticationManagerBuilder AuthenticationManager è l'interfaccia principale per l'autenticazione in Spring Security.
     *                                     Se il principal dell'autenticazione in input è valido e verificato, AuthenticationManager#authenticate
     *                                     restituisce un'istanza di Authentication con il flag di autenticazione impostato a true.
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        //Crea un PasswordEncoder che delega a un altro PasswordEncoder basato su un prefisso che trova nel token di password.
        // Questo è il modo consigliato per creare un PasswordEncoder da Spring Security 5.
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        authenticationManagerBuilder.inMemoryAuthentication() // Questo metodo configura l’autenticazione per memorizzare gli utenti nella memoria.
                .withUser(username) // Crea un utente con il nome utente specificato.
                .password(encoder.encode(password)) //Imposta una password per l'utente, viene codificata con l'encoder creato in precedenza
                .authorities("USER"); // imposta l'autorità per l'utente

        /*
        Username e password vengono usati anche dagli altri servizi per comunicare con il discovery server.
        Le credenziali vengono specificate nel file application.properties dei vari servizi in questo modo:
            eureka.client.service-url.defaultZone=http://username:password@localhost:8761/eureka
         */
    }
}
