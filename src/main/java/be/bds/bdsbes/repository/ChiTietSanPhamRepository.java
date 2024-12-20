package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct")
    Page<ChiTietSanPhamResponse> getAll(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct where ct.id = :id")
    ChiTietSanPhamResponse getCTSPById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE ChiTietSanPham ct SET ct.trangThai = :trangThai WHERE ct.id = :id")
    Integer updateTrangThaiById(int trangThai, Long id);

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct where ct.sanPham.id = :id")
    Page<ChiTietSanPhamResponse> getAllBySP(Pageable pageable, Long id);

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct " +
            "where ct.mauSac.id = :idMauSac and ct.kichThuoc.id = :idKichThuoc and ct.sanPham.id = :idSanPham")
    ChiTietSanPhamResponse getCTSPByColorAndSize(Long idMauSac, Long idKichThuoc, Long idSanPham);

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct where ct.sanPham.id = :id and ct.mauSac.id = :idMauSac")
    Page<ChiTietSanPhamResponse> getAllBySPAndColor(Pageable pageable, Long id, Long idMauSac);

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ma, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.giaBan, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct where ct.ma = :ma")
    ChiTietSanPhamResponse getCTSPByMaCTSP(String ma);

    @Transactional
    @Modifying
    @Query("UPDATE ChiTietSanPham ct SET ct.soLuong = (ct.soLuong - :soLuong) WHERE ct.id = :id")
    Integer updateSoLuongById(int soLuong, Long id);
}
