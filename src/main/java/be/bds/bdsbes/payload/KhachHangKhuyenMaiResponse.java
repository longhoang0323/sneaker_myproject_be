package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangKhuyenMaiResponse {

    private Long id;

    private Long idKhachHang;

    private String tenKhachHang;

    private Long idVoucher;

    private String maVoucher;

    private String moTaVoucher;

    private BigDecimal giaTriVoucher;

    private BigDecimal dieuKienVoucher;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private Integer loaiGiamGia;

    private Integer trangThai;

    private Integer trangThaiVoucher;

}
