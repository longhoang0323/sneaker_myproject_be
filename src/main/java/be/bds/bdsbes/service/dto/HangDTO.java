package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.Hang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HangDTO {

    private String ma;

    private String ten;

    private int trangThai;

    public Hang dto(Hang hang){
        hang.setTen(this.getTen());
        hang.setTrangThai(0);
        return hang;
    }
}
