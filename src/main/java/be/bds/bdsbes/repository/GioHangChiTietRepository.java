package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.GioHangChiTiet;
import be.bds.bdsbes.payload.GioHangChiTietResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gioHangRepository")
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    @Query("select new be.bds.bdsbes.payload.GioHangChiTietResponse(gh.id, gh.chiTietSanPham.id, gh.chiTietSanPham.sanPham.ten, gh.chiTietSanPham.mauSac.ten, gh.chiTietSanPham.kichThuoc.ten, " +
            "gh.chiTietSanPham.image, gh.user.id, gh.user.name, gh.donGia, gh.giaBan, gh.soLuong, gh.giamGia, gh.trangThai) from GioHangChiTiet gh where gh.user.id = :id")
    Page<GioHangChiTietResponse> getAllByUser(Pageable pageable, Long id);

    @Query("select gh from GioHangChiTiet gh where gh.user.id = :idUser")
    List<GioHangChiTiet> getAllListByUser(Long idUser);

    @Query("select new be.bds.bdsbes.payload.GioHangChiTietResponse(gh.id, gh.chiTietSanPham.id, gh.chiTietSanPham.sanPham.ten, gh.chiTietSanPham.mauSac.ten, gh.chiTietSanPham.kichThuoc.ten, " +
            "gh.chiTietSanPham.image, gh.user.id, gh.user.name, gh.donGia, gh.giaBan, gh.soLuong, gh.giamGia, gh.trangThai) from GioHangChiTiet gh where gh.id = :id")
    GioHangChiTietResponse getOneById(Long id);

}
