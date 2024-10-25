package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.HoaDonChiTiet;
import be.bds.bdsbes.payload.HoaDonChiTietResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("hoaDonChiTietRepository")
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {

    @Query("select new be.bds.bdsbes.payload.HoaDonChiTietResponse" +
            "(hdct.id, hdct.hoaDon.id, hdct.chiTietSanPham.id, hdct.donGia, hdct.giaBan, hdct.giamGia, " +
            "hdct.soLuong, hdct.chiTietSanPham.sanPham.ma, hdct.chiTietSanPham.sanPham.ten, " +
            "hdct.chiTietSanPham.sanPham.hang.ten, hdct.chiTietSanPham.sanPham.chatLieu.ten, " +
            "hdct.chiTietSanPham.kichThuoc.ten, hdct.chiTietSanPham.mauSac.ten, hdct.trangThai, hdct.chiTietSanPham.image) from HoaDonChiTiet hdct")
    Page<HoaDonChiTietResponse> getAll(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.HoaDonChiTietResponse" +
            "(hdct.id, hdct.hoaDon.id, hdct.chiTietSanPham.id, hdct.donGia, hdct.giaBan, hdct.giamGia, " +
            "hdct.soLuong, hdct.chiTietSanPham.sanPham.ma, hdct.chiTietSanPham.sanPham.ten, " +
            "hdct.chiTietSanPham.sanPham.hang.ten, hdct.chiTietSanPham.sanPham.chatLieu.ten, " +
            "hdct.chiTietSanPham.kichThuoc.ten, hdct.chiTietSanPham.mauSac.ten, hdct.trangThai, hdct.chiTietSanPham.image) from HoaDonChiTiet hdct where hdct.hoaDon.id = :idHoaDon")
    Page<HoaDonChiTietResponse> getAllByIdHoaDon(Pageable pageable, Long idHoaDon);

    @Query("select new be.bds.bdsbes.payload.HoaDonChiTietResponse" +
            "(hdct.id, hdct.hoaDon.id, hdct.chiTietSanPham.id, hdct.donGia, hdct.giaBan, hdct.giamGia, " +
            "hdct.soLuong, hdct.chiTietSanPham.sanPham.ma, hdct.chiTietSanPham.sanPham.ten, " +
            "hdct.chiTietSanPham.sanPham.hang.ten, hdct.chiTietSanPham.sanPham.chatLieu.ten, " +
            "hdct.chiTietSanPham.kichThuoc.ten, hdct.chiTietSanPham.mauSac.ten, hdct.trangThai, hdct.chiTietSanPham.image) from HoaDonChiTiet hdct where hdct.id = :id")
    HoaDonChiTietResponse getOneHDCT(Long id);
}
