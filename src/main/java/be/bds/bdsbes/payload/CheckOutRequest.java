package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutRequest {
    private Long id;
    private Long idPhong;

    private LocalDate checkIn;

    private LocalDate checkOut;

    @Override
    public String toString() {
        return "CheckOutRequest{" +
                "id=" + id +
                ", idPhong=" + idPhong +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
