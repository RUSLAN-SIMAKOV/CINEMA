package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.model.User;
import cinema.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User add(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } catch (DataProcessingException e) {
            throw new RuntimeException("Can not find user by email", e);
        }
    }
}
