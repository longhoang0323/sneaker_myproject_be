package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.LoaiPhong;
import be.bds.bdsbes.entities.Phong;
import be.bds.bdsbes.payload.LoaiPhongResponse1;
import be.bds.bdsbes.service.dto.response.LoaiPhongResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Long> {

    @Query(value = "select new be.bds.bdsbes.payload.LoaiPhongResponse1(l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio) from LoaiPhong l")
    List<LoaiPhongResponse1> singleListRoomType();

    @Query("select new be.bds.bdsbes.payload.LoaiPhongResponse1(l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio) from LoaiPhong l where l.id = :id")
    LoaiPhongResponse1 get(Long id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE LoaiPhong p SET p.trangThai = :trangThai WHERE p.id = :id")
//    Integer updateTrangThaiById(int trangThai, Long id);

    @Query("select count(p.id) from LoaiPhong l inner join Phong p on l.id = p.loaiPhong.id where count (p.id) >= :soPhong")
    int getSoPhongByLoaiPhong(int soPhong);

    @Query(value = "select new be.bds.bdsbes.payload.LoaiPhongResponse1(l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio) from LoaiPhong l inner join Phong p on l.id = p.loaiPhong.id group by l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio" +
            " having cast(count(p.id) as int) >= :soPhong" +
            " and (l.soNguoi = :soNguoi or l.soNguoi = (:soNguoi + 1) or l.soNguoi = (:soNguoi - 1))")
    List<LoaiPhongResponse1> listLoaiPhongBySearch1(int soPhong, int soNguoi);

    @Query(value = "select new be.bds.bdsbes.payload.LoaiPhongResponse1(l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio) from LoaiPhong l inner join Phong p on l.id = p.loaiPhong.id group by l.id, l.maLoaiPhong, l.tenLoaiPhong, l.soNguoi, l.tienIch, l.ghiChu, l.giaTheoNgay, l.giaTheoGio" +
            " having cast(count(p.id) as int) >= :soPhong" +
            " and (l.soNguoi = :soNguoi or l.soNguoi = (:soNguoi + 1))")
    List<LoaiPhongResponse1> listLoaiPhongBySearch2(int soPhong, int soNguoi);

    @Query("SELECT COUNT(p.id) " +
            "FROM Phong p " +
            "INNER JOIN p.loaiPhong lp " +
            "WHERE p.trangThai = 1 " +
            "AND p.id NOT IN (" +
            "    SELECT dp.phong.id " +
            "    FROM DatPhong dp " +
            "    WHERE dp.trangThai IN (1, 2) " +
            "    AND (" +
            "        (:checkIn >= dp.checkIn AND :checkIn < dp.checkOut) " +
            "        OR (:checkOut > dp.checkIn AND :checkOut <= dp.checkOut) " +
            "        OR (:checkIn <= dp.checkIn AND :checkOut >= dp.checkOut)" +
            "    )" +
            ") " +
            "AND lp.id = :idLoaiPhong")
    int getCountRoomByCheckDate(
            @Param("checkIn") LocalDateTime checkIn,
            @Param("checkOut") LocalDateTime checkOut,
            @Param("idLoaiPhong") Long idLoaiPhong
    );

//    @Query(value = "SELECT TOP 1 p.id " +
//            "FROM phong p " +
//            "JOIN loai_phong lp ON p.id_loai_phong = lp.id " +
//            "LEFT JOIN dat_phong dp ON p.id = dp.id_phong " +
//            "WHERE lp.ten_loai_phong LIKE CONCAT('%', :tenLoaiPhong, '%') " +
//            "AND (dp.id IS NULL " +
//            "OR (CAST(:startDate AS DATE) IS NULL OR CAST(:endDate AS DATE) IS NULL " +
//            "OR NOT (CAST(:startDate AS DATE) < dp.check_out AND CAST(:endDate AS DATE) > dp.check_in) " +
//            "))",
//            nativeQuery = true)
//    Long findTopIdByTenLoaiPhongAndDateRange(
//            @Param("tenLoaiPhong") String tenLoaiPhong,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate
//    );

    @Query(value = "SELECT TOP 1 p.id " +
            "FROM phong p " +
            "JOIN loai_phong lp ON p.id_loai_phong = lp.id " +
            "LEFT JOIN dat_phong dp ON p.id = dp.id_phong AND dp.trang_thai = 0 " +
            "WHERE lp.ten_loai_phong LIKE CONCAT('%', :tenLoaiPhong, '%') " +
            "AND (dp.id IS NULL " +
            "OR (CAST(:startDate AS DATE) IS NULL OR CAST(:endDate AS DATE) IS NULL " +
            "OR NOT (CAST(:startDate AS DATE) < dp.check_out AND CAST(:endDate AS DATE) > dp.check_in) " +
            "))",
            nativeQuery = true)
    Long findTopIdByTenLoaiPhongAndDateRange(
            @Param("tenLoaiPhong") String tenLoaiPhong,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );


}