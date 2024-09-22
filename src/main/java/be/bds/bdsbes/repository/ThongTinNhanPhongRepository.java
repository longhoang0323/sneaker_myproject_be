package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.ThongTinNhanPhong;
import be.bds.bdsbes.payload.ThongTinNhanPhongResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThongTinNhanPhongRepository extends JpaRepository<ThongTinNhanPhong, Long> {

    @Query("select new be.bds.bdsbes.payload.ThongTinNhanPhongResponse(tt.id, tt.idDatPhong.id, tt.cccd, tt.hoTen, tt.sdt, tt.ngaySinh, tt.gioiTinh, tt.trangThai, tt.ghiChu, tt.idDatPhong.checkIn) from ThongTinNhanPhong tt")
    List<ThongTinNhanPhongResponse> getList();

    @Query("select new be.bds.bdsbes.payload.ThongTinNhanPhongResponse(tt.id, tt.idDatPhong.id, tt.cccd, tt.hoTen, tt.sdt, tt.ngaySinh, tt.gioiTinh, tt.trangThai, tt.ghiChu, tt.idDatPhong.checkIn) from ThongTinNhanPhong tt where tt.idDatPhong.id = :id")
    ThongTinNhanPhongResponse getTTById(Long id);
}
