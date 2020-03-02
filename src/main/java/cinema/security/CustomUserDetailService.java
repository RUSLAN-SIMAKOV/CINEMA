package cinema.security;

import cinema.model.User;
import cinema.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        User userByEmail = userService.findByEmail(userEmail);

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (userByEmail != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(userEmail);
            builder.password(userByEmail.getPassword());
            builder.roles("USER");
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
