package cinema.service.impl;

import javax.naming.AuthenticationException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.UserService;
import cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private static UserService userService;

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

        if (userService.findByEmail(email) == null) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            userService.add(user);
        }
        return userService.findByEmail(email);
    }
}
