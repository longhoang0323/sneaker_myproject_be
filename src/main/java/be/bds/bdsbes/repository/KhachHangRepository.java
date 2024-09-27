package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.payload.KhachHangResponse1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    @Query("select kh.id from KhachHang kh join User u on kh.id = u.khachHang.id where u.id = ?1")
    Long findByI(Long id);

    @Query("select new be.bds.bdsbes.payload.KhachHangResponse1(k.id, k.ma, k.hoTen, k.ngaySinh, k.gioiTinh, k.diaChi, k.sdt, k.tichDiem, k.thoiHan) from KhachHang k where k.id = ?1")
    KhachHangResponse1 get(Long id);

    @Query("select new be.bds.bdsbes.payload.KhachHangResponse1(k.id, k.ma, k.hoTen, k.ngaySinh, k.gioiTinh, k.diaChi, k.sdt, k.tichDiem, k.thoiHan) from KhachHang k join User u on k.id = u.khachHang.id where u.id = :id")
    KhachHangResponse1 getKhachHangByUser(Long id);

    @Query("select new be.bds.bdsbes.payload.KhachHangResponse1(k.id, k.ma, k.hoTen, k.ngaySinh, k.gioiTinh, k.diaChi, k.sdt, k.tichDiem, k.thoiHan) from KhachHang k where k.ma like concat('%', :searchInput, '%')" +
            " or k.hoTen like concat('%', :searchInput, '%') or k.sdt like concat('%', :searchInput, '%')")
    Page<KhachHangResponse1> getListBySearch(Pageable pageable, String searchInput);

}