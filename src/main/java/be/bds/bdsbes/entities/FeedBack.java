package be.bds.bdsbes.entities;

import be.bds.bdsbes.domain.User;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = FeedBack.TABLE_NAME)
public class FeedBack {
    public static final String TABLE_NAME = "feed_back";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_NOIDUNG_NAME = "noi_dung";
    public static final String COLUMN_TRANGTHAI_NAME = "trang_thai";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @Nationalized
    @Lob
    @Column(name = COLUMN_NOIDUNG_NAME)
    private String noiDung;

    @Column(name = COLUMN_TRANGTHAI_NAME)
    private Integer trangThai;

}