package system.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import system.order.Dto.Order;
import system.order.Dto.OrderDto;

@Mapper
public interface OrderMapper {
    OrderManager INSTANCE = Mappers.getMapper(OrderManager.class);

    OrderDto toDto(Order order);

    Order fromDto(OrderDto orderDto);

}
