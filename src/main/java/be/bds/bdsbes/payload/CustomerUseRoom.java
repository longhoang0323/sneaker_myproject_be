package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUseRoom {

    public Long idPhong;
    public String maPhong;
    public String tenKhachHang;
    public String sdt;
    public String cccd;
    public Integer trangThaiDatPhong;
    public LocalDateTime checkIn;
    public LocalDateTime checkOut;

}
