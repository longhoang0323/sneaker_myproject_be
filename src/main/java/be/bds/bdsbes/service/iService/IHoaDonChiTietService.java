package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.HoaDonChiTiet;
import be.bds.bdsbes.payload.HoaDonChiTietResponse;
import be.bds.bdsbes.service.dto.HoaDonChiTietDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IHoaDonChiTietService {

    PagedResponse<HoaDonChiTietResponse> getAll(int page, int size);

    PagedResponse<HoaDonChiTietResponse> getAllByIdHoaDon(int page, int size, Long idHoaDon);

    HoaDonChiTiet create(HoaDonChiTietDTO hoaDonChiTietDTO);

    HoaDonChiTiet createOnline(HoaDonChiTietDTO hoaDonChiTietDTO);

    HoaDonChiTietResponse get(Long id);
}
