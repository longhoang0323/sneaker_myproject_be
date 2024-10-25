package be.bds.bdsbes.entities;

import be.bds.bdsbes.domain.User;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = HoaDon.TABLE_NAME)
public class HoaDon {
    public static final String TABLE_NAME = "hoa_don";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_NGAYTAO_NAME = "ngay_tao";
    public static final String COLUMN_NGAYTHANHTOAN_NAME = "ngay_thanh_toan";
    public static final String COLUMN_TONGTIEN_NAME = "tong_tien";
    public static final String COLUMN_TRANGTHAI_NAME = "trang_thai";
    public static final String COLUMN_GHICHU_NAME = "ghi_chu";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "ma")
    private String ma;

    @Column(name = COLUMN_NGAYTAO_NAME)
    private LocalDateTime ngayTao;

    @Column(name = COLUMN_NGAYTHANHTOAN_NAME)
    private LocalDateTime ngayThanhToan;

    @Column(name = "hinh_thuc_giao_hang")
    private int hinhThucGiaoHang;

    @Column(name = "hinh_thuc_thanh_toan")
    private int hinhThucThanhToan;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "sdt_nguoi_nhan")
    private String sdtNguoiNhan;

    @Column(name = "ten_nguoi_ship")
    private String tenNguoiShip;

    @Column(name = "sdt_nguoi_ship")
    private String sdtNguoiShip;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = COLUMN_TONGTIEN_NAME, precision = 18)
    private BigDecimal tongTien;

    @Column(name = "tien_ship", precision = 18)
    private BigDecimal tienShip;

    @Column(name = "tien_giam_gia", precision = 18)
    private BigDecimal tienGiamGia;

    @Column(name = "tong_thanh_toan", precision = 18)
    private BigDecimal tongThanhToan;

    @Column(name = "tien_mat", precision = 18)
    private BigDecimal tienMat;

    @Column(name = "chuyen_khoan", precision = 18)
    private BigDecimal chuyenKhoan;

    @Column(name = COLUMN_TRANGTHAI_NAME)
    private Integer trangThai;

    @Nationalized
    @Lob
    @Column(name = COLUMN_GHICHU_NAME)
    private String ghiChu;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @OneToMany(mappedBy = "hoaDon")
    private Set<HoaDonChiTiet> hoaDonChiTiets = new LinkedHashSet<>();

}