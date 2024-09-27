package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {

    @Query("select new be.bds.bdsbes.payload.ChiTietSanPhamResponse" +
            "(ct.id, ct.ma, ct.sanPham.id, ct.sanPham.ten, ct.mauSac.id, ct.mauSac.ten, ct.kichThuoc.id, ct.kichThuoc.ten, ct.donGia, ct.soLuong, ct.ghiChu, ct.trangThai, ct.image) from ChiTietSanPham ct")
    Page<ChiTietSanPhamResponse> getAll(Pageable pageable);
}
