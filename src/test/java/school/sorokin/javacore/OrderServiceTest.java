package school.sorokin.javacore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.sorokin.javacore.entity.Order;
import school.sorokin.javacore.exception.OrderNotFoundExceprion;
import school.sorokin.javacore.repository.OrderRepository;
import school.sorokin.javacore.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepositoryMock;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepositoryMock = mock(OrderRepository.class);
        orderService = new OrderService(orderRepositoryMock);
    }

    @Test
    void saveOrder_Success() {
        Order order = new Order(1, "Phone", 100, 100.0);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(1);
        String message = orderService.processOrder(order);
        assertTrue(message.contains("Order processed successfully"));
        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    @Test
    void saveOrder_Failure() {
        Order order = new Order(2, "Washer", -2, 10.0);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(0);
        String message = orderService.processOrder(order);
        assertTrue(message.contains("Order processing failed"));
        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    @Test
    void saveOrder_Exception() {
        Order order = null;
        when(orderRepositoryMock.saveOrder(order)).thenThrow(new RuntimeException("Connection issue"));
        Exception exception = assertThrows(RuntimeException.class, () -> orderService.processOrder(order));
        assertEquals("Connection issue", exception.getMessage());
        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    @Test
    void calculateTotal_Success() {
        Order order = new Order(1, "Phone", 10, 100.0);
        int id = 1;
        when(orderRepositoryMock.getOrderById(id)).thenReturn(Optional.of(order));
        double total = orderService.calculateTotal(id);
        assertEquals(1000.0, total);
        verify(orderRepositoryMock, times(1)).getOrderById(id);
    }

    @Test
    void calculateTotalWithNotExistOrder() {
        int id = 2;
        when(orderRepositoryMock.getOrderById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(OrderNotFoundExceprion.class, () -> orderService.calculateTotal(id));
        String message = exception.getMessage();
        assertEquals("Order not found", message);
        verify(orderRepositoryMock, times(1)).getOrderById(id);
    }
}