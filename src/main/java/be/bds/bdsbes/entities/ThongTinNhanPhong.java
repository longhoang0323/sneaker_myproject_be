package be.bds.bdsbes.entities;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = ThongTinNhanPhong.TABLE_NAME)
public class ThongTinNhanPhong {
    public static final String TABLE_NAME = "thong_tin_nhan_phong";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_CCCD_NAME = "cccd";
    public static final String COLUMN_HOTEN_NAME = "ho_ten";
    public static final String COLUMN_SDT_NAME = "sdt";
    public static final String COLUMN_NGAYSINH_NAME = "ngay_sinh";
    public static final String COLUMN_GIOITINH_NAME = "gioi_tinh";
    public static final String COLUMN_TRANGTHAI_NAME = "trang_thai";
    public static final String COLUMN_GHICHU_NAME = "ghi_chu";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dat_phong")
    private DatPhong idDatPhong;

    @Size(max = 12)
    @Column(name = COLUMN_CCCD_NAME, length = 12)
    private String cccd;

    @Size(max = 125)
    @Nationalized
    @Column(name = COLUMN_HOTEN_NAME, length = 125)
    private String hoTen;

    @Size(max = 20)
    @Column(name = COLUMN_SDT_NAME, length = 20)
    private String sdt;

    @Column(name = COLUMN_NGAYSINH_NAME)
    private LocalDate ngaySinh;

    @Column(name = COLUMN_GIOITINH_NAME)
    private Integer gioiTinh;

    @Column(name = COLUMN_TRANGTHAI_NAME)
    private Integer trangThai;

    @Size(max = 255)
    @Nationalized
    @Column(name = COLUMN_GHICHU_NAME)
    private String ghiChu;

}