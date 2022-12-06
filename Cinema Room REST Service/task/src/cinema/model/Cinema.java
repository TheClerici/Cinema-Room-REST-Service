package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Cinema {

    private final Integer total_rows;
    private final Integer total_columns;
    private List<Seat> available_seats;
    @JsonIgnore
    private Map<String, Seat> purchasedTickets;
    @JsonIgnore
    private ArrayList<Integer> purchasedSeats;

    public Cinema(Integer total_rows, Integer total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        for (int i = 1; i <= total_rows ; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i, j);
                available_seats.add(seat);
            }
        }
        this.purchasedTickets = new TreeMap<>();
        this.purchasedSeats = new ArrayList<>();
    }

    public void buyTicket(Seat seat, Ticket ticket) {
        purchasedSeats.add(seat.getSeatId());
        available_seats.removeIf(p -> seat.getSeatId() == p.getSeatId());
        purchasedTickets.put(ticket.getToken(), ticket.getTicket());
    }

    public void returnTicket(Seat seat, String token) {
        purchasedTickets.remove(token);
        available_seats.add(seat);
        purchasedSeats.removeIf(p -> p == seat.getSeatId());
        Collections.sort(available_seats);
    }

    public Integer getTotal_rows() {
        return total_rows;
    }

    public Integer getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public Map<String, Seat> getPurchasedTickets() {
        return purchasedTickets;
    }

    public ArrayList<Integer> getPurchasedSeats() {
        return purchasedSeats;
    }
}
