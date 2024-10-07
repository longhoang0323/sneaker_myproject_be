package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.payload.HoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id")
    Page<HoaDonResponse> getAll(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.HoaDonResponse(h.id, h.ma, h.ngayTao, h.ngayThanhToan, h.hinhThucGiaoHang, " +
            "h.hinhThucThanhToan, h.tenNguoiNhan, h.sdtNguoiNhan, h.tenNguoiShip, h.sdtNguoiShip, h.diaChi, h.tongTien, " +
            "h.tienShip, h.tienGiamGia, h.tongThanhToan, h.trangThai, h.ghiChu, k.id, k.hoTen, u.id, u.email) from HoaDon h left join KhachHang k on h.khachHang.id = k.id " +
            "left join User u on h.user.id = u.id where h.id = :id")
    HoaDonResponse getOneById(Long id);
}
