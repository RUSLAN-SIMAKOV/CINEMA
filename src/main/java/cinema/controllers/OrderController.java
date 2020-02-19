package cinema.controllers;

import static java.util.stream.Collectors.toList;
import cinema.dto.OrderDto;
import cinema.dto.OrderResponseDto;
import cinema.model.Order;
import cinema.model.Ticket;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/complete")
    private void completeOrder(@RequestBody OrderDto orderDto) {
        List<Ticket> ticketList = shoppingCartService.getByUser(userService
                .findByEmail(orderDto.getUserEmail())).getTickets();
        orderService.completeOrder(ticketList, userService
                .findByEmail(orderDto.getUserEmail()));
    }

    @GetMapping(value = "/")
    private List<OrderResponseDto> getOrderHistory(@RequestParam(name = "email") String email) {
        List<Order> orders = orderService.getOrderHistory(userService.findByEmail(email));
        return orders.stream().map(o -> getOrderDto(o)).collect(toList());
    }

    private OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUser(order.getUser().toString());
        orderResponseDto.setTickets(order.getTickets().stream()
                .map(t -> getOrderResponse(t)).collect(toList()));
        return orderResponseDto;
    }

    private String getOrderResponse(Ticket t) {
        String ticket = t.toString();
        return ticket;
    }
}
