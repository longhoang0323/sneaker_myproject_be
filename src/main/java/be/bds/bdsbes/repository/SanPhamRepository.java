package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.payload.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("select new be.bds.bdsbes.payload.SanPhamResponse(s.id, s.ma, s.ten, s.hang.id, s.hang.ten, s.chatLieu.id, s.chatLieu.ten, s.trangThai, s.imageDefault, s.qrCode, min(ctsp.giaBan), max (ctsp.giaBan)) from SanPham s left join ChiTietSanPham ctsp on s.id = ctsp.sanPham.id group by " +
            "s.id, s.ma, s.ten, s.hang.id, s.hang.ten, s.chatLieu.id, s.chatLieu.ten, s.trangThai, s.imageDefault, s.qrCode")
    Page<SanPhamResponse> getAll(Pageable pageable);

    @Query("select new be.bds.bdsbes.payload.SanPhamResponse(s.id, s.ma, s.ten, s.hang.id, s.hang.ten, s.chatLieu.id, s.chatLieu.ten, s.trangThai, s.imageDefault, s.qrCode, min(ctsp.giaBan), max (ctsp.giaBan)) from SanPham s left join ChiTietSanPham ctsp on s.id = ctsp.sanPham.id where s.id =:id group by " +
            "s.id, s.ma, s.ten, s.hang.id, s.hang.ten, s.chatLieu.id, s.chatLieu.ten, s.trangThai, s.imageDefault, s.qrCode")
    SanPhamResponse getOneById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE SanPham s SET s.trangThai = :trangThai WHERE s.id = :id")
    Integer updateTrangThaiById(int trangThai, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE SanPham s SET s.imageDefault = :imageDefault WHERE s.id = :id")
    Integer updateImageDefaultById(String imageDefault, Long id);
}
