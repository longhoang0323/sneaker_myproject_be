package be.bds.bdsbes.service.dto;


import be.bds.bdsbes.entities.ChatLieu;
import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.entities.SanPham;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanPhamDTO {

    private String ma;

    private String ten;

    private Long idHang;

    private Long idChatLieu;

    private int trangThai;

    private String imageDefault;

    private String qrCode;


    public SanPham dto(SanPham sanPham){
        sanPham.setTen(this.getTen());
        sanPham.setHang(Hang.builder().id(this.getIdHang()).build());
        sanPham.setChatLieu(ChatLieu.builder().id(this.getIdChatLieu()).build());
        sanPham.setTrangThai(0);
        sanPham.setImageDefault(this.getImageDefault());
        sanPham.setQrCode(this.getQrCode());
        return sanPham;
    }
}
