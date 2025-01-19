package system.order;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import system.order.Database.DatabaseConnection;
import system.order.Dto.Order;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
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

    //Достает все заказы из бд
    public void getAllOrders(Order order) throws SQLException {
        String sql = "SELECT * FROM orders";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setDate(5, Date.valueOf(order.getCreatedAt()));

            orders.add(order);
        }
    }

    //Добавление нового заказа в бд
    public void addNewOrderBd(Order order) throws SQLException {
        String sql = "INSERT INTO orders (client_id, description, amount, status, created_at) VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setDate(5, Date.valueOf(order.getCreatedAt()));

            int rowsAffect = preparedStatement.executeUpdate();
            if (rowsAffect > 0) {
                System.out.println("Order successfully added to the database.");
            }

        }
    }
}



















