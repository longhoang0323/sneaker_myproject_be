package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.service.dto.KhachHangDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;
import java.util.List;

public interface IKhachHangService {

    List<KhachHang> getList();

    KhachHangResponse1 getOne(Long id);

    KhachHangResponse1 getKhachBanLe(int trangThai);

    KhachHang create(KhachHangDTO khachHangDTO);

    KhachHang update(KhachHangDTO khachHangDTO, Long id);

    PagedResponse<KhachHangResponse1> getKhachHang(int page, int size) throws ServiceException;

    KhachHangResponse1 getKhachHangbyUser(Long id);

    Boolean updateKH(KhachHangDTO khachHangDTO, Long id);

    PagedResponse<KhachHangResponse1> getListBySearch(int page, int size, String inputSearch) throws ServiceException;
}
