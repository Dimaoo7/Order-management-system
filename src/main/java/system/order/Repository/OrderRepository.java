package system.order.Repository;

import system.order.Database.DatabaseConnection;
import system.order.Dto.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
    public void addOrder(Order order) {
        String query = "INSERT INTO orders (client_id, description, amount, status, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getClientId());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setInt(3,order.getAmount());
            preparedStatement.setString(4,order.getStatus());
            preparedStatement.setDate(5, java.sql.Date.valueOf(order.getCreatedAt()));

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " заказ(ов) добавлено. ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
