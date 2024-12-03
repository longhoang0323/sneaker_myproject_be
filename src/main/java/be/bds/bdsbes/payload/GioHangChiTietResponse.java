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
public class GioHangChiTietResponse {

    private Long id;

    private Long idChiTietSanPham;

    private String tenSanPham;

    private String tenMauSac;

    private String tenKichThuoc;

    private String imageSanPham;

    private Long idUser;

    private String nameUser;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private int soLuong;

    private BigDecimal giamGia;

    private Integer trangThai;

    private int trangThaiChon;
}
