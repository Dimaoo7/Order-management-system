package system.order;

import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private Long id;
    private Long clientId;
    private String description;
    private int amount;
    private String status;
    private LocalDate createdAt;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return amount == order.amount && Objects.equals(id, order.id) && Objects.equals(clientId, order.clientId) && Objects.equals(description, order.description) && Objects.equals(status, order.status) && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, description, amount, status, createdAt);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Order(Long id, Long clientId, String description, int amount, String status, LocalDate createdAt) {
        this.id = id;
        this.clientId = clientId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }
}
