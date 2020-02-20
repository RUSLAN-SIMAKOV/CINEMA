package cinema.dto;

import cinema.model.Ticket;
import java.util.List;

public class ShoppingCartDto {

    private List<String> tickets;

    private String userId;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
