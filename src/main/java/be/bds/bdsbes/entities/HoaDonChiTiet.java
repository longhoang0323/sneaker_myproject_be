package be.bds.bdsbes.entities;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = HoaDonChiTiet.TABLE_NAME)
public class HoaDonChiTiet {
    public static final String TABLE_NAME = "hoa_don_chi_tiet";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_TRANGTHAI_NAME = "trang_thai";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ctsp")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "giam_gia")
    private BigDecimal giamGia;

    @Column(name = COLUMN_TRANGTHAI_NAME)
    private int trangThai;

}