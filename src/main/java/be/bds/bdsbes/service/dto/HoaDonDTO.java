package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.HoaDonChiTiet;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
public class HoaDonDTO {

    private String ma;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayThanhToan;

    private int hinhThucGiaoHang;

    private int hinhThucThanhToan;

    private String tenNguoiNhan;

    private String sdtNguoiNhan;

    private String tenNguoiShip;

    private String sdtNguoiShip;

    private String diaChi;

    private BigDecimal tongTien;

    private BigDecimal tienShip;

    private BigDecimal tienGiamGia;

    private BigDecimal tongThanhToan;

    private BigDecimal tienMat;

    private BigDecimal chuyenKhoan;

    private Integer trangThai;

    private String ghiChu;

    private Long idKhachHang;

    private Long idUser;

    private Long idVoucher;
}
