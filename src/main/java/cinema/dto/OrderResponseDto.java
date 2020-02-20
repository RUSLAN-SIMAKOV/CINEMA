package cinema.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private List<String> tickets;
    private LocalDateTime orderDate;
    private String user;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
