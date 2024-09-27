package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IChiTietSanPhamService {

    PagedResponse<ChiTietSanPhamResponse> getAll(int page, int size);

    ChiTietSanPham create(ChiTietSanPhamDTO chiTietSanPhamDTO);

    ChiTietSanPham update(Long id, ChiTietSanPhamDTO chiTietSanPhamDTO);

    ChiTietSanPham get(Long id);

    ChiTietSanPham updateTrangThai(Long id, ChiTietSanPhamDTO chiTietSanPhamDTO);
}
