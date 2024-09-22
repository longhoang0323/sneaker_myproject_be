package be.bds.bdsbes.repository;

import be.bds.bdsbes.entities.ChiTietDichVu;
import be.bds.bdsbes.service.dto.response.ChiTietDichVuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface ChiTietDichVuRepository extends JpaRepository<ChiTietDichVu, Long> {
    @Query(value = "select ctdv.id, ctdv.id_dich_vu, ctdv.id_dat_phong, ctdv.ghi_chu, ctdv.gia_dich_vu, ctdv.trang_thai from" +
            "chi_tiet_dich_vu ctdv left join dich_vu dv on ctdv.id_dich_vu = dv.id" +
            "chi_tiet_dich_vu left join dat_phong dp on ctdv.id_dat_phong = dp.id", nativeQuery = true)
    List<ChiTietDichVuResponse> getAllChiTietDichVu();

    @Query("select ctdv from ChiTietDichVu ctdv where ctdv.datPhong.id = :id")
    Page<ChiTietDichVu> getAllByDatPhong(Pageable pageable, Long id);

    @Query("select ctdv from ChiTietDichVu ctdv where ctdv.datPhong.id = :id")
    List<ChiTietDichVu> getListByDatPhong(Long id);

    @Query("select ctdv from ChiTietDichVu ctdv where ctdv.dichVu.id = :id and ctdv.datPhong.id = :iddp")
    ChiTietDichVu getChiTietDichVuByDichVu(Long id, Long iddp);

    @Query("select count (ctdv.id) from ChiTietDichVu ctdv where (cast(ctdv.thoiGianDat as date) between cast(:checkIn as date) and cast(:checkOut as date) )")
    int getCount(LocalDate checkIn, LocalDate checkOut);

    @Query("select count (ctdv.id) from ChiTietDichVu ctdv where (day(ctdv.thoiGianDat) = :day and month(ctdv.thoiGianDat) = :month and year(ctdv.thoiGianDat) = :year)")
    int getCountByToDay(int day, int month, int year);

    @Query("select count (ctdv.id) from ChiTietDichVu ctdv where (month(ctdv.thoiGianDat) = :month and year(ctdv.thoiGianDat) = :year)")
    int getCountByMonth(int month, int year);

    @Query("select count (ctdv.id) from ChiTietDichVu ctdv where (year(ctdv.thoiGianDat) = :year)")
    int getCountByYear(int year);
}