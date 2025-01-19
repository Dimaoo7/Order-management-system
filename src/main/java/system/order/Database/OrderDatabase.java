package system.order.Database;

import system.order.Dto.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OrderDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/orders";
    private static final String USER = "postgres";
    private static final String PASSWORD = "216785";

    public void saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO orders (id, clientId, description, amount, status, createdAt) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.setLong(2, order.getClientId());
            statement.setString(3, order.getDescription());
            statement.setInt(4, order.getAmount());
            statement.setString(5, order.getStatus());
            statement.setDate(6, java.sql.Date.valueOf(order.getCreatedAt()));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
