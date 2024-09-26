package be.bds.bdsbes.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;


@Getter
@Setter
public class FeedbackDTO {

    private Long idUser;
    private Long idSanPham;
    private String noiDung;
    private Integer trangThai;
}
