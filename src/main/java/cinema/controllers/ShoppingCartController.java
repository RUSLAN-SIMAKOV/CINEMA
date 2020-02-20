package cinema.controllers;

import static java.util.stream.Collectors.toList;
import cinema.dto.ShoppingCartDto;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.service.MovieSessionService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shoppingcarts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieSessionService movieSessionService;

    @PostMapping(value = "/addmoviesession")
    private void addShoppingCart(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "moviesessionId") String moviesessionId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartService.addSession(movieSessionService.getByMovieSessionId(Long.parseLong(moviesessionId)),
                userService.findByEmail(email));
    }

    @GetMapping(value = "/byuser")
    private ShoppingCartDto getAllMovieSessions(@RequestParam(name = "email") String email) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.findByEmail(email));
        shoppingCartDto.setTickets(shoppingCart.getTickets().stream()
                .map(Ticket::toString)
                .collect(toList()));
        shoppingCartDto.setUserId("" + userService.findByEmail(email).getId());
        return shoppingCartDto;
    }
}
