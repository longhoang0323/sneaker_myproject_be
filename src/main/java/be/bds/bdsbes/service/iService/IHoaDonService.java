package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.payload.HoaDonResponse;
import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IHoaDonService {

    PagedResponse<HoaDonResponse> getAll(int page, int size);

    HoaDon createTaiQuay(HoaDonDTO hoaDonDTO);

    HoaDon datHangOnline(HoaDonDTO hoaDonDTO);

    HoaDonResponse thanhToanHoaDon(Long id, HoaDonDTO hoaDonDTO);

    HoaDonResponse get(Long id);
}
