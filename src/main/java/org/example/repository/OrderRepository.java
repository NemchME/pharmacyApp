package org.example.repository;

import org.example.model.Order;
import org.example.model.OrderInfo;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<OrderInfo> findByUserId(Integer userId);
}
