package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.entities.KichThuoc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KichThuocDTO {

    private String ma;

    private String ten;

    private int trangThai;

    public KichThuoc dto(KichThuoc kichThuoc){
        kichThuoc.setTen(this.getTen());
        kichThuoc.setTrangThai(0);
        return kichThuoc;
    }
}
