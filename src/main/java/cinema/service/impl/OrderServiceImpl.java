package cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import cinema.dao.OrderDao;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(tickets);
        shoppingCartService.clear(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
