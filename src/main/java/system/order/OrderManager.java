package system.order;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.order.Dto.Order;

import java.util.*;
import java.util.stream.Collectors;

public class OrderManager {

    public static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    public List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        logger.info("Order was successfully added {}", order);
    }

    public void showAllOrders(Order order) {
        if (orders.isEmpty()) {
            logger.info("No orders available");
        } else {
            orders.forEach(System.out::println);
        }
    }

    public Optional<Order> findOrderById(Long id) {
        return orders.stream()
                .filter(order -> Objects.equals(order.getId(), id))
                .findFirst();
    }

    public void deleteOrder(Long id) {
        boolean removed = orders.removeIf(order -> order.getId().equals(id));
        if (removed) {
            logger.info("Order with ID {} successfully removed.", id);
        } else {
            logger.info("Order with ID {} not found.", id);
        }
    }

    public void updateOrder(Long id, String newDescription, int newAmount, String newStatus) {
        findOrderById(id).ifPresentOrElse(
                order -> {
                    order.setDescription(newDescription);
                    order.setAmount(newAmount);
                    order.setStatus(newStatus);
                    logger.info("Order updated: {}", order);
                },
                () -> logger.info("Order with ID {} not found", id)
        );
    }

    public void showOrdersByStatus(String status) {
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
        if (filteredOrders.isEmpty()) {
            logger.info("No orders with status:{}", status);
        } else {
            filteredOrders.forEach(System.out::println);
        }
    }

    public void showOrdersByAmountGreaterThan(int amount) {
        orders.stream()
                .filter(order -> order.getAmount() > amount)
                .forEach(System.out::println);
    }

    public long countOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .count();
    }




}


















