package com.silvio.orderservice.controller;

import com.silvio.orderservice.dto.OrderRequest;
import com.silvio.orderservice.model.Order;
import com.silvio.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        orderService.placeOrder(orderRequest);
        return "Order placed succesfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }
}
