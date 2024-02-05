package com.silvio.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /*
    La differenza principale tra la creazione di un bean di tipo WebClient.Builder e un bean di tipo WebClient risiede
    nel livello di personalizzazione e nel controllo che si ha sulle chiamate HTTP.

    Creando un bean di tipo WebClient.Builder, hai la possibilità di personalizzare ulteriormente il WebClient in un secondo momento,
    prima di effettuare la chiamata HTTP. Ad esempio, potresti voler aggiungere filtri, gestori di errori personalizzati,
    ecc., a seconda del contesto specifico in cui stai utilizzando il WebClient.

    D’altra parte, se crei un bean di tipo WebClient, l’istanza di WebClient sarà completamente costruita al momento
    della creazione del bean e non potrai personalizzarla ulteriormente in seguito.
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
