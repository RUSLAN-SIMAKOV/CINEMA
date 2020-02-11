package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.User;
import cinema.service.UserService;
import cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
    user.setSalt(HashUtil.getSalt());
    user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
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
