package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.HoaDonChiTiet;
import be.bds.bdsbes.payload.HoaDonChiTietResponse;
import be.bds.bdsbes.service.dto.HoaDonChiTietDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IHoaDonChiTietService {

    PagedResponse<HoaDonChiTietResponse> getAll(int page, int size);

    HoaDonChiTiet create(HoaDonChiTietDTO hoaDonChiTietDTO);

    HoaDonChiTietResponse get(Long id);
}
