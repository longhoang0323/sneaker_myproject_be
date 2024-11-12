package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.KhachHangKhuyenMai;
import be.bds.bdsbes.payload.KhachHangKhuyenMaiResponse;
import be.bds.bdsbes.payload.KhachHangResponse1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("khachHangKhuyemMaiRepository")
public interface KhachHangKhuyenMaiRepository extends JpaRepository<KhachHangKhuyenMai, Long> {

    @Query("select new be.bds.bdsbes.payload.KhachHangKhuyenMaiResponse(khkm.id, khkm.khachHang.id, khkm.khachHang.hoTen, khkm.voucher.id, " +
            "khkm.voucher.ma, khkm.voucher.moTa, khkm.voucher.giamGia, khkm.voucher.dieuKien, khkm.voucher.ngayBatDau, khkm.voucher.ngayKetThuc, khkm.voucher.loaiGiamGia, khkm.trangThai) from KhachHangKhuyenMai khkm " +
            "where khkm.khachHang.id = :idKhachHang and khkm.trangThai = 1")
    Page<KhachHangKhuyenMaiResponse> getAllByIdKhachHang(Pageable pageable, Long idKhachHang);
}
