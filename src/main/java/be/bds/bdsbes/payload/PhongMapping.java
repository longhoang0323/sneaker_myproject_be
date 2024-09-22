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
public class PhongMapping {

    private Long id;

    private String ma;

    private Integer trangThai;

    private Long idLoaiPhong;

    private String tenLoaiPhong;

    private BigDecimal giaTheoNgay;

    private BigDecimal giaTheoGio;

    private int soNguoi;

    private String tienIch;

    private String ghiChu;
}
