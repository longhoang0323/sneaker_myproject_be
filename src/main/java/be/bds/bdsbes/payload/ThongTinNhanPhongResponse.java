package be.bds.bdsbes.payload;

import be.bds.bdsbes.entities.DatPhong;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongTinNhanPhongResponse {

    private Long id;

    private Long idDatPhong;

    private String cccd;

    private String hoTen;

    private String sdt;

    private LocalDate ngaySinh;

    private Integer gioiTinh;

    private Integer trangThai;

    private String ghiChu;

    private LocalDateTime ngayNhanPhong;

}
