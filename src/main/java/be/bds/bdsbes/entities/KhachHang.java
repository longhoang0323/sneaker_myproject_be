package be.bds.bdsbes.entities;

import be.bds.bdsbes.domain.User;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = KhachHang.TABLE_NAME)
public class KhachHang {
    public static final String TABLE_NAME = "khach_hang";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_MA_NAME = "ma";
    public static final String COLUMN_HOTEN_NAME = "ho_ten";
    public static final String COLUMN_NGAYSINH_NAME = "ngay_sinh";
    public static final String COLUMN_GIOITINH_NAME = "gioi_tinh";
    public static final String COLUMN_DIACHI_NAME = "dia_chi";
    public static final String COLUMN_SDT_NAME = "sdt";
    public static final String COLUMN_GHICHU_NAME = "ghi_chu";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = COLUMN_MA_NAME, length = 20)
    private String ma;

    @Size(max = 50)
    @Nationalized
    @Column(name = COLUMN_HOTEN_NAME, length = 50)
    private String hoTen;

    @Column(name = COLUMN_NGAYSINH_NAME)
    private LocalDate ngaySinh;

    @Column(name = COLUMN_GIOITINH_NAME)
    private Boolean gioiTinh;

    @Size(max = 100)
    @Nationalized
    @Column(name = COLUMN_DIACHI_NAME, length = 100)
    private String diaChi;

    @Size(max = 11)
    @Column(name = COLUMN_SDT_NAME, length = 11)
    private String sdt;

    @Column(name = "tich_diem")
    private int tichDiem;

    @Column(name = "thoi_han")
    private Date thoiHan;

    @Column(name = "trang_thai")
    private int trangThai;

    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "khachHang")
    private Set<User> users = new LinkedHashSet<>();

}