package system.order;


import java.util.*;
import java.util.stream.Collectors;

public class OrderManager {

    public List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order successfully added");
    }

    public void showAllOrders(Order order) {
        if (orders.isEmpty()) {
            System.out.println("No orders available");
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
            System.out.println("Order with ID " + id + " successfully removed.");
        } else {
            System.out.println("Order with ID " + id + " not found.");
        }
    }

    public void updateOrder(Long id, String newDescription, int newAmount, String newStatus) {
        findOrderById(id).ifPresentOrElse(
                order -> {
                    order.setDescription(newDescription);
                    order.setAmount(newAmount);
                    order.setStatus(newStatus);
                    System.out.println("Order updated: " + order);
                },
                () -> System.out.println("Order with ID " + id + " not found")
        );
    }

    public void showOrdersByStatus(String status) {
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
        if (filteredOrders.isEmpty()) {
            System.out.println("No orders with status:" + status);
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


















