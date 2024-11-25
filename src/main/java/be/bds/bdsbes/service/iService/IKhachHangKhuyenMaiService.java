package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.KhachHangKhuyenMai;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangKhuyenMaiResponse;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.utils.dto.PagedResponse;

import java.util.List;

public interface IKhachHangKhuyenMaiService {

    List<KhachHangKhuyenMaiResponse> saveAll(Long idVoucher, List<KhachHangResponse1> listKh);

    PagedResponse<KhachHangKhuyenMaiResponse> getAllByIDKhachHang(int page, int size, Long idKhachHang) throws ServiceException;
}
