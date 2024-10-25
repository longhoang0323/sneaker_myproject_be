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
public class ChiTietSanPhamResponse {

    private Long id;

    private String ma;

    private Long idSanPham;

    private String tenSanPham;

    private Long idMauSac;

    private String maMauSac;

    private String tenMauSac;

    private Long idKichThuoc;

    private String tenKichThuoc;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private int soLuong;

    private String ghiChu;

    private int trangThai;

    private String image;
}
