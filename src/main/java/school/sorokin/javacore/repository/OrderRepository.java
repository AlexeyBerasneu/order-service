package school.sorokin.javacore.repository;

import school.sorokin.javacore.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order);
    Optional<Order> getOrderById(int id);
}
