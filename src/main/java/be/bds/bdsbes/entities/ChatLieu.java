package be.bds.bdsbes.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = ChatLieu.TABLE_NAME)
public class ChatLieu {

    public static final String TABLE_NAME = "chat_lieu";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_MA_NAME = "ma";
    public static final String COLUMN_TEN_NAME = "ten";
    public static final String COLUMN_TRANGTHAI_NAME = "trang_thai";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @Column(name = COLUMN_MA_NAME)
    private String ma;

    @Column(name = COLUMN_TEN_NAME)
    private String ten;

    @Column(name = COLUMN_TRANGTHAI_NAME)
    private int trangThai;
}
