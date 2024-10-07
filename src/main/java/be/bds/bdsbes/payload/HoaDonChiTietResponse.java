package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietResponse {

    private Long id;

    private Long idHoaDon;

    private Long idChiTietSanPham;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private BigDecimal giamGia;

    private int soLuong;

    private String maSanPham;

    private String tenSanPham;

    private String tenHang;

    private String tenChatLieu;

    private String tenKichThuoc;

    private String tenMauSac;

    private int trangThai;

}
