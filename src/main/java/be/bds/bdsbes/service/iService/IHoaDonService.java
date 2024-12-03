package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.payload.HoaDonResponse;
import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

import java.time.LocalDate;

public interface IHoaDonService {

    PagedResponse<HoaDonResponse> getAll(int page, int size);

    PagedResponse<HoaDonResponse> getAllByUser(int page, int size, Long idUser);

    PagedResponse<HoaDonResponse> getAllByLoaiHoaDon(int page, int size, Integer loaiHoaDon);

    PagedResponse<HoaDonResponse> getAllBySearchAndAll(int page, int size, Integer loaiHoaDon, String searchInput, String startDate, String endDate, Integer trangThaiGiaoHang, Integer hinhThucGiaoHang, Integer trangThai);

    HoaDon createTaiQuay(HoaDonDTO hoaDonDTO);

    HoaDon datHangOnline(HoaDonDTO hoaDonDTO);

    HoaDonResponse thanhToanHoaDon(Long id, HoaDonDTO hoaDonDTO);

    HoaDonResponse get(Long id);

    HoaDonResponse getOneByMa(String ma);

    Integer updateTrangThaiThanhToan(Long id);


    Integer updateTrangThaiGiaoHang(Long id, HoaDonDTO hoaDonDTO);

    Double getSumTongThanhToan(String startDate, String endDate, String dayInput);

    Double getCountHoaDonByTrangThai(Integer trangThai, String startDate, String endDate, String dayInput);
}
