package be.bds.bdsbes.service.dto;


import be.bds.bdsbes.entities.MauSac;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MauSacDTO {

    private String ma;

    private String ten;

    private int trangThai;

    public MauSac dto(MauSac mauSac){
        mauSac.setTen(this.getTen());
        mauSac.setTrangThai(0);
        return mauSac;
    }
}
