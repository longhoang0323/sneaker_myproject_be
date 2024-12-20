package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangResponse1 {

    private Long id;

    private String ma;

    private String hoTen;

    private LocalDate ngaySinh;

    private Boolean gioiTinh;

    private String diaChi;

    private String sdt;

    private int tichDiem;

    private Date thoiHan;

    private int trangThai;

}
