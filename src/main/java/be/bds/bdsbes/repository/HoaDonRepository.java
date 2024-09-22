package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.entities.Sale;
import be.bds.bdsbes.payload.HoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k " +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getList(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where (p.ghiChu like concat('%', :searchInput, '%') or " +
            "p.khachHang.hoTen like concat('%', :searchInput, '%') or p.khachHang.cccd like concat('%', :searchInput, '%') or p.khachHang.sdt like concat('%', :searchInput, '%') or p.ma like concat('%', :searchInput, '%')) and (p.trangThai = :trangThai) and (cast(p.ngayThanhToan as date) between cast(:startDate as date) and cast(:endDate as date))" +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getListBySearchAndTrangThaiAndDate(Pageable pageable, String searchInput, int trangThai, LocalDate startDate, LocalDate endDate);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where (p.ghiChu like concat('%', :searchInput, '%') or " +
            "p.khachHang.hoTen like concat('%', :searchInput, '%') or p.khachHang.cccd like concat('%', :searchInput, '%') or p.khachHang.sdt like concat('%', :searchInput, '%') or p.ma like concat('%', :searchInput, '%')) and (cast(p.ngayThanhToan as date) between cast(:startDate as date) and cast(:endDate as date))" +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getListByDate(Pageable pageable, String searchInput, LocalDate startDate, LocalDate endDate);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where (p.ghiChu like concat('%', :searchInput, '%') or " +
            "p.khachHang.hoTen like concat('%', :searchInput, '%') or p.khachHang.cccd like concat('%', :searchInput, '%') or p.khachHang.sdt like concat('%', :searchInput, '%') or p.ma like concat('%', :searchInput, '%')) and (p.trangThai = :trangThai)" +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getListBySearchAndTrangThai(Pageable pageable, String searchInput, int trangThai);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where (p.ghiChu like concat('%', :searchInput, '%') or " +
            "p.khachHang.hoTen like concat('%', :searchInput, '%') or p.khachHang.cccd like concat('%', :searchInput, '%') or p.khachHang.sdt like concat('%', :searchInput, '%') or p.ma like concat('%', :searchInput, '%')) " +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getListBySearch(Pageable pageable, String searchInput);

    @Query("select h from HoaDon h join DatPhong d on h.id = d.hoaDon.id join KhachHang k on k.id = h.khachHang.id where k.hoTen = :hoTen and k.sdt = :sdt")
    Page<HoaDon> getListByCustumer(Pageable pageable, String hoTen, String sdt);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k " +
            "where k.id = :idKhachHang and cast(p.ngayTao as date) = cast(:ngayTao as date) and (p.trangThai = 1 or p.trangThai = 3)")
    HoaDonResponse getHoaDon(Long idKhachHang, LocalDate ngayTao);

    @Query("select h.id from HoaDon h where h.khachHang.id = :idKH and cast(h.ngayTao as date) = cast(:ngayTao as date) and h.trangThai = 1")
    Long getId(Long idKH, LocalDate ngayTao);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k " +
            "where p.id = :id")
    HoaDonResponse get(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.trangThai = :trangThai WHERE h.id = :id")
    Integer updateTrangThaiById(int trangThai, Long id);

    @Query(value = "SELECT * FROM hoa_don v WHERE v.trang_thai = :status", nativeQuery = true)
    List<HoaDon> findByStatus(@Param("status") Integer status);

    @Query(value = "SELECT * FROM hoa_don v WHERE v.ngay_tao < :date AND v.trang_thai = :status", nativeQuery = true)
    List<HoaDon> findByExpiryDateBeforeAndStatus(@Param("date") LocalDate date, @Param("status") Integer status);

    @Query("select h.id from HoaDon h where h.khachHang.id = :idKH and cast(h.ngayTao as date) = cast(:ngayTao as date) and (h.trangThai = 1 or h.trangThai = 3)")
    Long getIdTaiQuay(Long idKH, LocalDate ngayTao);
    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k " +
            "where k.id = :idKhachHang and cast(p.ngayTao as date) = cast(:ngayTao as date) and (p.trangThai = 3 or p.trangThai = 1)")
    HoaDonResponse getHoaDonTaiQuay(Long idKhachHang, LocalDate ngayTao);

    @Query("select sum(h.tienThanhToan) from HoaDon h where h.trangThai = 5 and (h.ngayThanhToan between cast(:checkIn as date) and cast(:checkOut as date) )")
    BigDecimal getDoanhThuByDay(LocalDate checkIn, LocalDate checkOut);

    @Query("select sum(h.tienThanhToan) from HoaDon h where h.trangThai = 5 and (month(h.ngayThanhToan) = :month and year(h.ngayThanhToan) = :year)")
    BigDecimal getDoanhThuByMonth(int month, int year);

    @Query("select sum(h.tienThanhToan) from HoaDon h where h.trangThai = 5 and (year(h.ngayThanhToan) = :year)")
    BigDecimal getDoanhThuByYear(int year);

    @Query("select sum(h.tienThanhToan) from HoaDon h where h.trangThai = 5 and (day(h.ngayThanhToan) = :day and month(h.ngayThanhToan) = :month and year(h.ngayThanhToan) = :year)")
    BigDecimal getDoanhThuByToDay(int day, int month, int year);

    @Query("select sum(h.tienThanhToan) from HoaDon h where h.trangThai = 5")
    BigDecimal getAllDoanhThu();

    @Query ("select sum(h.tienThanhToan) from HoaDon h join KhachHang k on h.khachHang.id = k.id group by k.id, h.trangThai having k.id = :id and h.trangThai = 5")
    BigDecimal getTongTienByKhachHang(Long id);

    @Query("select h from HoaDon h where h.khachHang.id = :id")
    Page<HoaDon> findByKhachHang(Pageable pageable, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienCoc = :tienCoc, h.thoiGianCoc = :thoiGianCoc WHERE h.id = :id")
    Integer updateTienCocById(BigDecimal tienCoc, LocalDateTime thoiGianCoc, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tongTien = :tongTien WHERE h.id = :id")
    Integer updateTongTienById(BigDecimal tongTien, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienPhong = :tienPhong WHERE h.id = :id")
    Integer updateTienPhongById(BigDecimal tienPhong, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienPhat = :tienPhat WHERE h.id = :id")
    Integer updateTienPhatById(BigDecimal tienPhat, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienDichVu = :tienDichVu WHERE h.id = :id")
    Integer updateTienDichVuById(BigDecimal tienDichVu, Long id);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where p.trangThai = :trangThai " +
            "group by k.id, p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai")
    Page<HoaDonResponse> getListByTrangThai(Pageable pageable, int trangThai);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienTichDiem = :tienTichDiem, h.tienThanhToan = :tienThanhToan , h.trangThai = 5, h.ngayThanhToan = :ngayThanhToan WHERE h.id = :id")
    Integer updateTienTichDiemById(BigDecimal tienTichDiem, BigDecimal tienThanhToan, LocalDateTime ngayThanhToan, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE HoaDon h SET h.tienHoanLai = :tienHoanLai WHERE h.id = :id")
    Integer updateTienHoanLaiById(BigDecimal tienHoanLai, Long id);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(p.id, p.ma, p.ngayTao, p.ngayThanhToan, p.tongTien, p.trangThai, p.ghiChu, k.id, k.hoTen, p.tienCoc, p.thoiGianCoc, p.tienPhong, p.tienDichVu, p.tienPhat, p.tienTichDiem, p.tienThanhToan, p.tienHoanLai) from HoaDon p inner join p.khachHang k where p.trangThai = 2 or p.trangThai = 6")
    Page<HoaDonResponse> getListXacNhan(Pageable pageable);

}