package cinema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stats {
    private final int current_income;
    private final int number_of_available_seats;
    private final int number_of_purchased_tickets;

    public Stats(Map<String, Seat> purchasedTickets, ArrayList<Integer> purchasedSeats, List<Seat> available_seats) {
        if (purchasedSeats.size() != 0) {
            int income = 0;
            for (int i = 0; i < purchasedSeats.size(); i++) {
                List<Seat> toSum = purchasedTickets.values().stream().toList();
                income += toSum.get(i).getPrice();
            }
            this.current_income = income;
        } else {
            this.current_income = 0;
        }
        this.number_of_available_seats = available_seats.size();
        this.number_of_purchased_tickets = purchasedSeats.size();
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

}
