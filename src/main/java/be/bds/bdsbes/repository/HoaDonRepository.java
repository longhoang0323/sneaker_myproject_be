package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.payload.HoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository("hoaDonRepository")
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id")
    Page<HoaDonResponse> getAll(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon")
    Page<HoaDonResponse> getAllByLoaiHD(Pageable pageable, Integer loaiHoaDon);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%'))")
    Page<HoaDonResponse> getAllBySearch(Pageable pageable, Integer loaiHoaDon, String searchInput);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%'))" +
            "and h.trangThai = :trangThai")
    Page<HoaDonResponse> getAllBySearchAndTrangThaiTT(Pageable pageable, Integer loaiHoaDon, String searchInput, Integer trangThai);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%'))" +
            "and h.trangThaiGiaoHang = :trangThaiGiaoHang")
    Page<HoaDonResponse> getAllBySearchAndTrangThaiGH(Pageable pageable, Integer loaiHoaDon, String searchInput, Integer trangThaiGiaoHang);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%') " +
            ") and h.hinhThucGiaoHang = :hinhThucGiaoHang")
    Page<HoaDonResponse> getAllBySearchAndHinhThucGH(Pageable pageable, Integer loaiHoaDon, String searchInput, Integer hinhThucGiaoHang);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%') " +
            ") and (cast(h.ngayTao as date) >= cast(:startDate as date) and cast(h.ngayTao as date) <= cast(:endDate as date))")
    Page<HoaDonResponse> getAllBySearchAndDate(Pageable pageable, Integer loaiHoaDon, String searchInput, LocalDate startDate, LocalDate endDate);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%') " +
            ") and (h.trangThaiGiaoHang = :trangThaiGiaoHang or :trangThaiGiaoHang = 10) and (h.hinhThucGiaoHang = :hinhThucGiaoHang or :hinhThucGiaoHang = 10) " +
            "and (h.trangThai = :trangThai or :trangThai = 10)")
    Page<HoaDonResponse> getAllBySearchAndAllTrangThai(Pageable pageable, Integer loaiHoaDon, String searchInput, Integer trangThaiGiaoHang, Integer hinhThucGiaoHang, Integer trangThai);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.loaiHoaDon = :loaiHoaDon and (h.ma like concat('%', :searchInput, '%') or h.tenNguoiNhan like concat('%', :searchInput, '%') or k.hoTen like concat('%', :searchInput, '%') " +
            ") and ((cast(h.ngayTao as date) >= cast(:startDate as date) and cast(h.ngayTao as date) <= cast(:endDate as date)) or :startDate = null or :endDate = null) and (h.trangThaiGiaoHang = :trangThaiGiaoHang or :trangThaiGiaoHang = 10) and (h.hinhThucGiaoHang = :hinhThucGiaoHang or :hinhThucGiaoHang = 10) " +
            "and (h.trangThai = :trangThai or :trangThai = 10)")
    Page<HoaDonResponse> getAllBySearchAndAll(Pageable pageable, Integer loaiHoaDon, String searchInput, LocalDate startDate, LocalDate endDate, Integer trangThaiGiaoHang, Integer hinhThucGiaoHang, Integer trangThai);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.id = :id")
    HoaDonResponse getOneById(Long id);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.ma = :ma")
    HoaDonResponse getOneByMa(String ma);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.tienMat, h.chuyenKhoan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email, v.id, v.ma, v.giamGia, h.loaiHoaDon, h.trangThaiGiaoHang) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id left join Voucher v on h.voucher.id = v.id where h.user.id = :idUser")
    Page<HoaDonResponse> getAllByUser(Pageable pageable, Long idUser);

    @Query("select sum(h.tongThanhToan) from HoaDon h where h.trangThai = 1 and (h.loaiHoaDon = :loaiHoaDon or :loaiHoaDon = 10) " +
            "and ((cast(h.ngayThanhToan as date) >= cast(:startDate as date) and cast(h.ngayThanhToan as date) <= cast(:endDate as date)) or :startDate = null or :endDate = null) " +
            "and (cast(h.ngayThanhToan as date ) = cast(:dayInput as date) or :dayInput = null)")
    BigDecimal getSumTongThanhToan(Integer loaiHoaDon, LocalDate startDate, LocalDate endDate, LocalDate dayInput);

    @Query("select count (h.id) from HoaDon h where (h.trangThai = :trangThai or :trangThai = 10) " +
            "and ((cast(h.ngayThanhToan as date) >= cast(:startDate as date) and cast(h.ngayThanhToan as date) <= cast(:endDate as date)) or :startDate = null or :endDate = null) " +
            "and (cast(h.ngayThanhToan as date ) = cast(:dayInput as date) or :dayInput = null)")
    int getCountHoaDonByTrangThai(Integer trangThai, LocalDate startDate, LocalDate endDate, LocalDate dayInput);
}
