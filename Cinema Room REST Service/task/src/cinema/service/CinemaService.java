package cinema.service;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.Stats;
import cinema.model.Ticket;
import cinema.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CinemaService {
    private final Cinema cinema = new Cinema(9, 9);

    public ResponseEntity<?> readSeats() {
        return ResponseEntity.ok(cinema);
    }

    public ResponseEntity<?> purchaseSeat(Seat seat) {

        if (seat.getRow() < 1 || seat.getRow() > 9)
            return ResponseHandler.generateResponse("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);

        if (seat.getColumn() < 1 || seat.getColumn()  > 9)
            return ResponseHandler.generateResponse("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);

        if (cinema.getPurchasedSeats().contains(seat.getSeatId()))
            return ResponseHandler.generateResponse("The ticket has been already purchased!", HttpStatus.BAD_REQUEST);

        Ticket ticket = new Ticket(seat);
        cinema.buyTicket(seat, ticket);

        return ResponseEntity.ok(ticket);
    }

    public ResponseEntity<?> returnTicket(String token) {
        token = token.split(":")[1];
        token = token.substring(1, token.length() - 2);

        if (!cinema.getPurchasedTickets().containsKey(token)) {
            return ResponseHandler.generateResponse("Wrong token!", HttpStatus.BAD_REQUEST);
        }

        Seat seat = cinema.getPurchasedTickets().get(token);
        cinema.returnTicket(seat, token);
        return ResponseEntity.ok(Map.of("returned_ticket", seat));
    }

    public ResponseEntity<?> cinemaStats(Object password) {

        if (password == null) {
            return ResponseHandler.generateResponse("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }

        if (!password.equals("super_secret")) {
            return ResponseHandler.generateResponse("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }

        Stats stats = new Stats(cinema.getPurchasedTickets(), cinema.getPurchasedSeats(), cinema.getAvailable_seats());
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}

/*
        int current_income = 0;
        if (cinema.getPurchasedTickets().size() >= 1) {
            for (int i = 1; i <= cinema.getPurchasedSeats().size() ; i++) {
                List<Seat> toSum = cinema.getPurchasedTickets().values().stream().toList();
                current_income += toSum.get(i).getPrice();
            }
        }
        int number_of_available_seats = cinema.getAvailable_seats().size();
        int number_of_purchased_tickets = cinema.getPurchasedSeats().size();

        Map<String, Integer> statistic = new HashMap<>();
        statistic.put("current_income", current_income);
        statistic.put("number_of_available_seats", number_of_available_seats);
        statistic.put("number_of_purchased_tickets", number_of_purchased_tickets);
*/