package cinema.service;

import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;

public interface ShoppingCartService {

    ShoppingCart getByUser(User user);

    void addSession(MovieSession movieSession, User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
