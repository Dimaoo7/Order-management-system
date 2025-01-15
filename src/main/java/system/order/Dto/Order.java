package system.order.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long clientId;
    private String description;
    private int amount;
    private String status;
    private LocalDate createdAt;
}
