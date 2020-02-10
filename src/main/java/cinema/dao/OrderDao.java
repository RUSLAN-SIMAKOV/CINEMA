package cinema.dao;

import java.util.List;
import cinema.model.Order;
import cinema.model.Ticket;
import cinema.model.User;

public interface OrderDao {

    Order completeOrder(Order order);

    List<Order> getOrderHistory(User user);
}
