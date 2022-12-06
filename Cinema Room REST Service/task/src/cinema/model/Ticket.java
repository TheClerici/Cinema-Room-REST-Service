package cinema.model;

import java.util.UUID;

public class Ticket {
    private final String token;
    private Seat ticket;

    public Ticket(Seat ticket) {
        this.token = (UUID.randomUUID().toString());
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
