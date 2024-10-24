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
@Table(name = "san_pham_khuyen_mai")
public class SanPhamKhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_ctsp")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_dot_giam_gia")
    private DotGiamGia dotGiamGia;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "trang_thai")
    private int trangThai;
}
