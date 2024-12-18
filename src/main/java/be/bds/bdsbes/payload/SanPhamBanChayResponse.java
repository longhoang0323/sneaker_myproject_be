package be.bds.bdsbes.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamBanChayResponse {

    private Long id;

    private String tenSanPham;

    private String mauSac;

    private long count;

    private long soLuong;
}
