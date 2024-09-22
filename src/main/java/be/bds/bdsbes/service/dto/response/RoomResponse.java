package be.bds.bdsbes.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String ma;
    private String tenLoaiPhong;
    private Integer soLuongNguoi;
    private BigDecimal giaTheoNgay;
    private Integer trangThai;
}
