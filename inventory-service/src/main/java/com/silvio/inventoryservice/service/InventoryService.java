package com.silvio.inventoryservice.service;

import com.silvio.inventoryservice.dto.InventoryResponse;
import com.silvio.inventoryservice.model.Inventory;
import com.silvio.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /*
    L'annotazione @Transactional viene utilizzata in Spring per gestire transazioni nel contesto
    di un'applicazione. Quando un metodo è annotato con @Transactional, Spring avvia una
    transazione prima dell'esecuzione del metodo e la completa al termine del metodo.
    Se il metodo viene completato con successo, la transazione viene confermata e tutte le
    modifiche apportate al database durante l'esecuzione del metodo vengono rese permanenti.
    Se, invece, si verifica un'eccezione durante l'esecuzione del metodo, la transazione viene
    annullata (rollback), e le modifiche al database vengono annullate.

    In breve, l'utilizzo di @Transactional garantisce la coerenza e l'integrità del database.
    Le operazioni di lettura non richiedono necessariamente l'annotazione @Transactional, ma
    le operazioni di scrittura o aggiornamento dei dati, che possono compromettere la
    coerenza del database, dovrebbero essere gestite all'interno di transazioni.

    Questo approccio alla gestione delle transazioni è particolarmente utile quando si lavora
    con operazioni complesse che coinvolgono più azioni sul database, assicurando che tutte le
    azioni siano completate correttamente o annullate se si verifica un problema durante
    l'esecuzione.

    readOnly:
    La proprietà readOnly è un modo di informare il gestore di transazioni (Hibernate) che il metodo
    non effettuerà operazioni di scrittura o aggiornamento sul database. Questa informazione
    può consentire al gestore di transazioni di ottimizzare il contesto di transazione, ad
    esempio, evitando di eseguire un commit alla fine del metodo per transazioni di sola lettura.
     */
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
