package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.payload.SanPhamResponse;
import be.bds.bdsbes.service.dto.SanPhamDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface ISanPhamService {

    PagedResponse<SanPhamResponse> getAll(int page, int size);

    SanPham create(SanPhamDTO sanPhamDTO);

    SanPham update(Long id, SanPhamDTO sanPhamDTO);

    SanPhamResponse get(Long id);

    int updateTrangThai(Long id, int trangThai);

    int updateImageDefault(Long id, String imageDefault);
}
