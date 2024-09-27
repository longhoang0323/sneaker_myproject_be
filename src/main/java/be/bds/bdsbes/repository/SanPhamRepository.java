package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.payload.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("select new be.bds.bdsbes.payload.SanPhamResponse(s.id, s.ma, s.ten, s.hang.id, s.hang.ten, s.chatLieu.id, s.chatLieu.ten, s.trangThai, s.imageDefault) from SanPham s")
    Page<SanPhamResponse> getAll(Pageable pageable);
}
