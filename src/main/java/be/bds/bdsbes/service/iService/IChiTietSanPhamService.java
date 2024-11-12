package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IChiTietSanPhamService {

    PagedResponse<ChiTietSanPhamResponse> getAll(int page, int size);

    PagedResponse<ChiTietSanPhamResponse> getAllBySanPham(int page, int size, Long id);

    ChiTietSanPham create(ChiTietSanPhamDTO chiTietSanPhamDTO) throws ServiceException;

    ChiTietSanPham update(Long id, ChiTietSanPhamDTO chiTietSanPhamDTO);

    ChiTietSanPhamResponse get(Long id);

    int updateTrangThai(Long id, int trangThai);

    ChiTietSanPhamResponse getByColorAndSize(Long idMauSac, Long idKichThuoc, Long idSanPham) throws ServiceException;

    PagedResponse<ChiTietSanPhamResponse> getAllBySanPhamAndColor(int page, int size, Long id, Long idMauSac);

    ChiTietSanPhamResponse getByMaCTSP(String ma);

}
