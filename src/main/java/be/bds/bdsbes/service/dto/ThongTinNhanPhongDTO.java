package be.bds.bdsbes.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class ThongTinNhanPhongDTO {

    private Long idDatPhong;

    @NotNull(message = "not null")
    private String cccdCheckIn;

    @NotNull(message = "not null")
    private String hoTenCheckIn;

    @NotNull(message = "not null")
    private String sdtCheckIn;

    @NotNull(message = "not null")
    private LocalDate ngaySinhCheckIn;

    @NotNull(message = "not null")
    private Integer gioiTinhCheckIn;

    private Integer trangThaiCheckIn;

    private String ghiChuCheckIn;

    private int soNguoi;
}
