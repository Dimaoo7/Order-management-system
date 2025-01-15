package system.order;

import system.order.Dto.Order;

import java.time.LocalDate;

public class App{
        public static void main(String[] args) {
            OrderRepository orderRepository = new OrderRepository();

            Order order = new Order(1L, 101L, "Test Order", 500, "Pending", LocalDate.now());
            orderRepository.addOrder(order);
        }

}
