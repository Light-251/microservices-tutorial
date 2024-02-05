package com.silvio.orderservice.repository;

import com.silvio.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRespository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o")
    List<Order> getAll();
}
