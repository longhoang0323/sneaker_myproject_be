package be.bds.bdsbes.entities;

import be.bds.bdsbes.domain.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = GioHangChiTiet.TABLE_NAME)
public class GioHangChiTiet {

    public static final String TABLE_NAME = "gio_hang_chi_tiet";
    public static final String COLUMN_ID_NAME = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ctsp")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "giam_gia")
    private BigDecimal giamGia;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
