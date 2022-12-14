package cinema.model;

//import java.util.UUID;
//private final UUID token = UUID.randomUUID();

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat implements Comparable<Seat> {

    private int row;
    private int column;
    private final int price;
    @JsonIgnore
    private final int seatId;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.seatId = (row * 10) + column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public int getSeatId() {
        return seatId;
    }

    @Override
    public int compareTo(Seat seat) {
        int id = seat.getSeatId();
        return this.seatId - id;
    }
}
