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
public class DatPhongMap {

    private Long id;

    private String ma;

    private Long idKhachHang;

    private String maKhachHang;

    private String hoTen;

    private String sdt;

    private LocalDateTime ngayDat;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Integer soNguoi;

    private String tienPhatDatPhong;

    private String ghiChu;

    private Integer trangThai;

    private String tenPhong;

    private BigDecimal tongGia;

    private BigDecimal tienCocDatPhong;

    private Long idDatPhong;

    private String tenLoaiPhong;

    private Long idLoaiPhong;

    private BigDecimal giaPhong;

    private String tang;

    private Long idHoaDon;

    private String maHD;

    private String cccd;

    private LocalDate ngaySinh;

    private LocalDateTime ngayTao;

    private LocalDateTime ngayThanhToan;

    private BigDecimal tongTien;

    private Integer trangThaiThanhToan;

    private BigDecimal tienCoc;

    private LocalDateTime thoiGianCoc;

    private BigDecimal tienPhong;

    private BigDecimal tienDichVu;

    private BigDecimal tienPhat;

    private BigDecimal tienTichDiem;

    private BigDecimal tienThanhToan;

    private BigDecimal tienHoanLai;

    private LocalDateTime thoiGianCheckOut;

    private String nguoiCheckIn;

    private String sdtCheckIn;

    private String giamGia;

}
