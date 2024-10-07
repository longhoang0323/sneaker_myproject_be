package be.bds.bdsbes.service.dto;

import be.bds.bdsbes.entities.HoaDonChiTiet;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HoaDonChiTietDTO {

    private Long idChiTietSanPham;

    private Long idHoaDon;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private int soLuong;

    private BigDecimal giamGia;

    private int trangThai;
}
