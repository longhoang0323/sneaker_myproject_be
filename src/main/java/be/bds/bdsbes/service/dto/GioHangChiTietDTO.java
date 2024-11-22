package be.bds.bdsbes.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GioHangChiTietDTO {

    private Long idChiTietSanPham;

    private Long idUser;

    private BigDecimal donGia;

    private BigDecimal giaBan;

    private int soLuong;

    private BigDecimal giamGia;

    private Integer trangThai;

}
