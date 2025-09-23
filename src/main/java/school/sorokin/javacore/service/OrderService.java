package school.sorokin.javacore.service;

import school.sorokin.javacore.entity.Order;
import school.sorokin.javacore.exception.OrderNotFoundExceprion;
import school.sorokin.javacore.repository.OrderRepository;

import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        int id = orderRepository.saveOrder(order);
        if (id > 0) {
            return "Order processed successfully";
        } else {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Optional<Order> order = orderRepository.getOrderById(id);
        if (order.isPresent()) {
            return order.get().getTotalPrice();
        } else {
            throw new OrderNotFoundExceprion("Order not found");
        }
    }
}
