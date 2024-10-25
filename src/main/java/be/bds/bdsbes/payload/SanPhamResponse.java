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
public class SanPhamResponse {

    private Long id;

    private String ma;

    private String ten;

    private Long idHang;

    private String tenHang;

    private Long idChatLieu;

    private String tenChatLieu;

    private int trangThai;

    private String imageDefault;

    private String qrCode;

    private BigDecimal giaThapNhat;

    private BigDecimal giaCaoNhat;

}
