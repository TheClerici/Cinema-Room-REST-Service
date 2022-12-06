package cinema.controller;

import cinema.model.Seat;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {

    @Autowired
    private final CinemaService cinemaService;
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public ResponseEntity<?> readSeats() {
        return cinemaService.readSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) { return cinemaService.purchaseSeat(seat); }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) { return cinemaService.returnTicket(token); }

    @PostMapping("/stats")
    public ResponseEntity<?> cinemaStats(@RequestParam(required = false) Object password) {
        return cinemaService.cinemaStats(password); }
}
