package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.DatPhong;
import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.entities.KhachHang;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class HoaDonDTO {

    private Long id;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayThanhToan;

    private BigDecimal tongTien;

    private Integer trangThai;

    private String ghiChu;

    private Long idKhachHang;

    private BigDecimal tienCoc;

    private LocalDateTime thoiGianCoc;

    private BigDecimal tienPhong;

    private BigDecimal tienDichVu;

    private BigDecimal tienPhat;

    private BigDecimal tienTichDiem;

    private BigDecimal tienThanhToan;

    private BigDecimal tienHoanLai;


    public HoaDon dto(HoaDon hoaDon){
        LocalDateTime localDateTime = LocalDateTime.now();
        hoaDon.setNgayTao(localDateTime);
        hoaDon.setNgayThanhToan(this.getNgayThanhToan());
        hoaDon.setTongTien(this.getTongTien());
        hoaDon.setTrangThai(this.getTrangThai());
        hoaDon.setGhiChu(this.getGhiChu());
        hoaDon.setKhachHang(KhachHang.builder().id(this.getIdKhachHang()).build());
        hoaDon.setTienCoc(this.getTienCoc());
        hoaDon.setThoiGianCoc(this.getThoiGianCoc());
        hoaDon.setTienPhong(this.getTienPhong());
        hoaDon.setTienTichDiem(this.getTienTichDiem());
        hoaDon.setTienHoanLai(this.getTienHoanLai());
        return hoaDon;
    }
}
