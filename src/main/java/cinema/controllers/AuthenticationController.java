package cinema.controllers;

import cinema.dto.UserDto;
import cinema.service.AuthenticationService;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    private void login(@RequestBody UserDto userDto) throws AuthenticationException {
        authenticationService.login(userDto.getEmail(), userDto.getPassword());
    }

    @PostMapping(value = "/register")
    private void register(@RequestBody UserDto userDto) throws AuthenticationException {
        authenticationService.register(userDto.getEmail(), userDto.getPassword());
    }

}
