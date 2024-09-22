package be.bds.bdsbes.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyBookingDTO {

    private int month;
    private int year;
    private long total;
}
