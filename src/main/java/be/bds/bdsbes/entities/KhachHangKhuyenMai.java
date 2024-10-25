package be.bds.bdsbes.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "khach_hang_khuyen_mai")
public class KhachHangKhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
