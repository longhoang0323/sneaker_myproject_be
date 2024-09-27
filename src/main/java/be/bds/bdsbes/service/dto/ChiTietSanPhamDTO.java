package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.entities.SanPham;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class ChiTietSanPhamDTO {

    private String ma;

    private Long idSanPham;

    private Long idMauSac;

    private Long idKichThuoc;

    private BigDecimal donGia;

    private int soLuong;

    private String ghiChu;

    private int trangThai;

    private String image;

    public ChiTietSanPham dto(ChiTietSanPham chiTietSanPham){
        chiTietSanPham.setSanPham(SanPham.builder().id(this.getIdSanPham()).build());
        chiTietSanPham.setMauSac(MauSac.builder().id(this.getIdMauSac()).build());
        chiTietSanPham.setKichThuoc(KichThuoc.builder().id(this.getIdKichThuoc()).build());
        chiTietSanPham.setDonGia(this.getDonGia());
        chiTietSanPham.setSoLuong(this.getSoLuong());
        chiTietSanPham.setGhiChu(this.getGhiChu());
        chiTietSanPham.setTrangThai(0);
        chiTietSanPham.setImage(this.getImage());
        return chiTietSanPham;
    }
}
