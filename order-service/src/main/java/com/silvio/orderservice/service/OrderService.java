package com.silvio.orderservice.service;

import com.silvio.orderservice.dto.InventoryResponse;
import com.silvio.orderservice.dto.OrderLineItemsDTO;
import com.silvio.orderservice.dto.OrderRequest;
import com.silvio.orderservice.model.Order;
import com.silvio.orderservice.model.OrderLineItems;
import com.silvio.orderservice.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()//specifica il metodo di richiesta hhtp
                .uri("http://inventory-service/api/inventory", //url verso cui fare la chiamata
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()) //parametri richiesti
                .retrieve()//recupera la risposta
                .bodyToMono(InventoryResponse[].class)//specificare il tipo di risposta
                .block();//con questo metodo il thread si blocca e aspetta la fine dell'esecuzione della chiamata rendendo la chiamata del tipo "sincrono"

        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::getIsInStock);

        if (allProductsInStock) {

            checkOrderInformations(order);
            orderRespository.save(order);
        } else {
            throw new IllegalArgumentException("Prodotti non presenti nell'inventario");
        }

    }

    private void checkOrderInformations(Order order) {
        for (OrderLineItems orderLineItems : order.getOrderLineItemsList()) {
            if (orderLineItems.getQuantity() == null || orderLineItems.getQuantity() == 0)
                throw new IllegalArgumentException("Inerire la quantità del prodotto " + orderLineItems.getSkuCode());
        }

    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());

        return orderLineItems;

    }

    public List<Order> getAllOrders() {
        return orderRespository.getAll();
    }
}
