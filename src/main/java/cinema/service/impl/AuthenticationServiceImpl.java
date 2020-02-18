package cinema.service.impl;

import javax.naming.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import cinema.util.HashUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

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
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.add(user);
        user = userService.findByEmail(email);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
