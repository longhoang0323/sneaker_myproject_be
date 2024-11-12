package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonResponse {

    private Long id;

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

    private String tenKhachHang;

    private Long idUser;

    private String userEmail;

    private Long idVoucher;

    private String maVoucher;

    private BigDecimal giaTriVoucher;

    private Integer loaiHoaDon;

    private Integer trangThaiGiaoHang;

}
