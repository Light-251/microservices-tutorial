package com.silvio.inventoryservice;

import com.silvio.inventoryservice.model.Inventory;
import com.silvio.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    /*
    CommandLineRunner è un'interfaccia funzionale che definisce il metodo run(String... args).
    Questa interfaccia è parte di Spring Boot e viene utilizzata per eseguire operazioni durante
    l'avvio dell'applicazione. Nel caso specifico, stiamo popolando la tabella dell'inventario
    nel database con dati iniziali. Il metodo run viene chiamato automaticamente all'avvio
    dell'applicazione Spring, fornendo un punto di ingresso per eseguire azioni di
    inizializzazione o configurazione.
     */
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("lenovo-legion-y540");
            inventory.setQuantity(5);

            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("acer-aspire-e15");
            inventory1.setQuantity(0);

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
        };
    }
}
