package cinema.service.impl;

import javax.naming.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private static UserService userService;

    @Inject
    private static ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDataBase = userService.findByEmail(email);
        if (userFromDataBase.getPassword()
                .equals(HashUtil.hashPassword(password, userFromDataBase.getSalt()))) {
            return userFromDataBase;
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) throws AuthenticationException {

        if (userService.findByEmail(email) == null) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            userService.add(user);
            user = userService.findByEmail(email);
            shoppingCartService.registerNewShoppingCart(user);
            return user;
        }
        throw new AuthenticationException("User with this email already exist");
    }
}
