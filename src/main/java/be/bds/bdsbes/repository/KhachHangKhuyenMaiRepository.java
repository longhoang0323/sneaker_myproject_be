package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.KhachHangKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("khachHangKhuyemMaiRepository")
public interface KhachHangKhuyenMaiRepository extends JpaRepository<KhachHangKhuyenMai, Long> {
}
