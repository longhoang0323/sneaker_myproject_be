package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.DatPhong;
import be.bds.bdsbes.entities.Phong;
import be.bds.bdsbes.payload.CustomerUseRoom;
import be.bds.bdsbes.payload.DatPhongMap;
import be.bds.bdsbes.payload.DatPhongMapping;
import be.bds.bdsbes.service.dto.MonthlyBookingDTO;
import be.bds.bdsbes.service.dto.response.DatPhongResponse;
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

@Repository("datPhongRepository")
public interface DatPhongRepository extends JpaRepository<DatPhong, Long> {

    @Query("select new be.bds.bdsbes.service.dto.response.DatPhongResponse(d.id, d.ma, u.id, u.ma, u.sdt, u.hoTen" +
            ",d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, d.trangThai, d.phong.ma, d.tongGia, d.phong.id, d.phong.giaPhong, d.hoaDon.id) " +
            "from DatPhong d join d.khachHang u")
    List<DatPhongResponse> getAllDatPhong();

    @Query("select new be.bds.bdsbes.service.dto.response.DatPhongResponse(d.id, d.ma, u.id, u.ma, u.sdt, u.hoTen," +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, d.trangThai, d.phong.ma, d.tongGia, d.phong.id, d.phong.giaPhong, d.hoaDon.id) " +
            "from DatPhong d join d.khachHang u where d.id= :id")
    DatPhongResponse get(Long id);

    @Query("select d from DatPhong d where d.khachHang.id = :id")
    Page<DatPhong> getAllDatPhongByUser(Pageable pageable, Long id);

    @Query("SELECT CASE WHEN COUNT(dp) > 0 THEN true ELSE false END " +
            "FROM DatPhong dp " +
            "WHERE dp.phong.id = :idPhong and (dp.trangThai = 1 or dp.trangThai = 2 or dp.trangThai = 4 or dp.trangThai = 5) and (cast(dp.checkIn as date) = cast(:checkIn as date) " +
            "or (cast(dp.checkIn as date) < cast(:checkIn as date) and cast(dp.checkOut as date) > cast(:checkIn as date))" +
            "or (cast(dp.checkIn as date) >=" +
            " cast(:checkIn as date) and cast(dp.checkOut as date) <= cast(:checkOut as date)) " +
            "or (cast(dp.checkIn as date) > cast(:checkIn as date) and cast(dp.checkOut as date) > cast(:checkOut as date) and cast(dp.checkIn as date) < cast(:checkOut as date))  " +
            ")")
    Boolean validateCheckIn(@Param("idPhong") Long idPhong, @Param("checkIn") LocalDateTime checkIn, LocalDateTime checkOut);

    @Query("Select p from Phong  p inner join ChiTietPhong ctp on p.id = ctp.phong.id where p.trangThai = 1 and ctp.trangThai = 1 and  p.giaPhong = :giaPhong and p.id <> :id and p.id not in " +
            "(select d.phong.id from DatPhong d where (d.trangThai = 1 or d.trangThai = 2 or d.trangThai = 5) and ((cast(:checkIn as date) between cast(d.checkIn as date) and cast(d.checkOut as date)) or (cast(:checkOut as date) between cast(d.checkIn as date) and cast(d.checkOut as date)) " +
            "or (cast(d.checkIn as date) between cast(:checkIn as date) and cast(:checkOut as date)) or (cast(d.checkOut as date) between cast(:checkIn as date) and cast(:checkOut as date)) or cast(:checkIn as date) = cast(d.checkIn as date) or" +
            " cast(:checkOut as date) = cast(d.checkOut as date)))")
    Page<Phong> getPhongByUpperPrice(Pageable pageable, BigDecimal giaPhong, Long id, LocalDateTime checkIn, LocalDateTime checkOut);


    @Transactional
    @Modifying
    @Query("UPDATE DatPhong d SET d.trangThai = :trangThai WHERE d.id = :id")
    Integer updateTrangThaiById(int trangThai, Long id);

    @Query("select new be.bds.bdsbes.service.dto.response.DatPhongResponse(d.id, d.ma, u.id, u.ma, u.sdt, u.hoTen," +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, d.trangThai, d.phong.ma, d.tongGia, d.phong.id, d.phong.giaPhong, d.hoaDon.id) " +
            "from DatPhong d join d.khachHang u where d.id= :id")
    List<DatPhongResponse> getDatPhong(Long id);

    @Query("select p from DatPhong p where p.hoaDon.id = :id and (p.trangThai = 1 or p.trangThai = 2 or p.trangThai = 3 or p.trangThai = 5)")
    List<DatPhong> getDatPhongByHoaDon(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE DatPhong d SET d.phong.id = :idPhong, d.tongGia = :tongGia WHERE d.id = :id")
    Integer updateDatPhongById(Long idPhong, BigDecimal tongGia, Long id);

    @Query("select d from DatPhong d where d.khachHang.id = :id ")
    Page<DatPhong> getLichSuDatPhong(Pageable pageable, Long id);

    @Query("select d from DatPhong d join HoaDon h on d.hoaDon.id = h.id where h.trangThai = 1 and h.khachHang.id = :idKH and d.trangThai = 4")
    Page<DatPhong> getRoomByHoaDon(Pageable pageable, Long idKH);

    @Query("select p from DatPhong p where p.hoaDon.id = :id")
    Page<DatPhong> getPageDatPhongByHoaDon(Pageable pageable, Long id);

    @Query("select d from DatPhong d join HoaDon h on d.hoaDon.id = h.id where d.hoaDon.id = :id")
    List<DatPhong> getRoomByHoaDon0(Long id);

    @Query("select count (dp.phong.id) from DatPhong dp where dp.trangThai <> 0 and (cast(dp.ngayDat as date) between cast(:checkIn as date) and cast(:checkOut as date) )")
    int getSoPhongDaDat(LocalDate checkIn, LocalDate checkOut);

    @Query("select count (dp.phong.id) from DatPhong dp where dp.trangThai <> 0 and (day(dp.ngayDat) = :day and month(dp.ngayDat) = :month and year(dp.ngayDat) = :year)")
    int getSoPhongDaDatByToDay(int day, int month, int year);

    @Query("select count (dp.phong.id) from DatPhong dp where dp.trangThai <> 0 and (month(dp.ngayDat) = :month and year(dp.ngayDat) = :year)")
    int getSoPhongDaDatByMonth(int month, int year);

    @Query("select count (dp.phong.id) from DatPhong dp where dp.trangThai <> 0 and (year(dp.ngayDat) = :year)")
    int getSoPhongDaDatByYear(int year);

    @Query("select d from DatPhong d where d.khachHang.id = :id")
    Page<DatPhong> getPageDatPhongByKH(Pageable pageable, Long id);

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id order by p.ma asc")
    List<DatPhongMapping> getListDatPhong();

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id inner join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id order by d.ma asc")
    Page<DatPhongMapping> getPageDatPhong(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.DatPhongMap(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.id, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id where d.id = :id")
    DatPhongMap getPhongById(Long id);

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id" +
            " where d.phong.id = :id and " +
            "((cast(d.checkIn as date) >= cast(:checkIn as date) and cast(d.checkOut as date) <= cast(:checkOut as date)) or (cast(d.checkIn as date) <= cast(:checkIn as date) and cast(d.checkOut as date) >= cast(:checkOut as date)) or (cast(d.checkIn as date) <= cast(:checkIn as date) and cast(d.checkOut as date) >= cast(:checkIn as date)) or " +
            " (cast(d.checkIn as date) <= cast(:checkOut as date) and cast(d.checkOut as date) >= cast(:checkOut as date)))")
    List<DatPhongMapping> getListDatPhongByDate(Long id, LocalDate checkIn, LocalDate checkOut);

    @Transactional
    @Modifying
    @Query("UPDATE DatPhong d SET d.phong.id = :idPhong WHERE d.id = :id")
    Integer doiPhongById(Long idPhong, Long id);

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id " +
            "left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id where cast(d.checkOut as date) = cast(:checkOut as date) and (d.trangThai = 2 or d.trangThai = 5 or d.trangThai = 1) order by p.ma asc")
    List<DatPhongMapping> getListCheckOutToDay(LocalDate checkOut);

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id " +
            "left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id where cast(d.checkOut as date) = cast(:checkIn as date) and (d.trangThai <> 3 or d.trangThai <> 0) and p.id = :id")
    DatPhongMapping getRoomCheckInToDay(LocalDate checkIn, Long id);

    @Query("SELECT new be.bds.bdsbes.service.dto.MonthlyBookingDTO(MONTH(dp.ngayDat), YEAR(dp.ngayDat), COUNT(dp)) " +
            "FROM HoaDon hd " +
            "JOIN hd.datPhongs dp " +
            "GROUP BY YEAR(dp.ngayDat), MONTH(dp.ngayDat) " +
            "ORDER BY YEAR(dp.ngayDat), MONTH(dp.ngayDat)")
    List<MonthlyBookingDTO> findMonthlyBookings();

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id" +
            " where k.id = :id and cast(d.checkIn as date) = cast(:checkIn as date)")
    List<DatPhongMapping> getListDatPhongByKHAndCheckIn(LocalDate checkIn, Long id);

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id" +
            " where k.id = :id")
    List<DatPhongMapping> getListDatPhongByKH(Long id);

    @Query("select new be.bds.bdsbes.payload.CustomerUseRoom(p.id, p.ma, kh.hoTen, kh.sdt, kh.cccd, dp.trangThai, dp.checkIn, dp.checkOut) from DatPhong dp join KhachHang kh on dp.khachHang.id = kh.id join Phong p on dp.phong.id = p.id")
    List<CustomerUseRoom> getListCustomerUseRoom();

    @Query("select new be.bds.bdsbes.payload.DatPhongMapping(p.id, d.ma, k.id, k.ma, k.hoTen, k.sdt, " +
            "d.ngayDat, d.checkIn, d.checkOut, d.soNguoi, d.ghiChu, h.ghiChu, d.trangThai, p.ma, d.tongGia, d.tienCoc, d.id, p.loaiPhong.tenLoaiPhong, p.loaiPhong.giaTheoNgay, ctp.tang, h.id, h.ma, k.cccd, k.ngaySinh" +
            ", h.ngayTao, h.ngayThanhToan, h.tongTien, h.trangThai, h.tienCoc, h.thoiGianCoc, h.tienPhong, h.tienDichVu, h.tienPhat, h.tienTichDiem, h.tienThanhToan, h.tienHoanLai, d.thoiGianCheckOut, tt.hoTen, tt.sdt, k.ghiChu)" +
            " from DatPhong d inner join HoaDon h on d.hoaDon.id = h.id inner join KhachHang k on k.id = d.khachHang.id right join Phong p on p.id = d.phong.id inner join ChiTietPhong ctp on p.id = ctp.phong.id left join ThongTinNhanPhong tt on d.id = tt.idDatPhong.id" +
            " where d.phong.id = :id and (d.trangThai <> 3 and d.trangThai <> 0) and" +
            "((cast(d.checkIn as date) >= cast(:checkIn as date) and cast(d.checkOut as date) <= cast(:checkOut as date)) or (cast(d.checkIn as date) <= cast(:checkIn as date) and cast(d.checkOut as date) >= cast(:checkOut as date)) " +
            "or (cast(d.checkIn as date) <= cast(:checkIn as date) and cast(d.checkOut as date) > cast(:checkIn as date)) or " +
            " (cast(d.checkIn as date) < cast(:checkOut as date) and cast(d.checkOut as date) >= cast(:checkOut as date)))")
    List<DatPhongMapping> checkListDatPhongByDate(Long id, LocalDate checkIn, LocalDate checkOut);

    @Transactional
    @Modifying
    @Query("UPDATE DatPhong d SET d.hoaDon.id = :idHoaDon WHERE d.id = :id")
    Integer doiHoaDonById(Long idHoaDon, Long id);

    @Query("select d.id from DatPhong d where cast(d.checkOut as date) = cast(:checkOut as date) and d.phong.id = :id and (d.trangThai = 2 or d.trangThai = 5)")
    List<DatPhong> getCheckOutToDay(LocalDate checkOut, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE DatPhong d SET d.trangThai = 3, d.ghiChu = :ghiChu, d.thoiGianCheckOut = :thoiGianCheckOut WHERE d.id = :id")
    Integer checkOutMuon(String ghiChu, LocalDateTime thoiGianCheckOut, Long id);
}