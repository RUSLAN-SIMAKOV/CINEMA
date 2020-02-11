package cinema.service;

import java.util.List;
import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.User;

public interface OrderService {

    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
