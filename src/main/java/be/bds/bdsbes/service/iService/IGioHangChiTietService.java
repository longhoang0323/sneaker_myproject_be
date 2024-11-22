package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.GioHangChiTiet;
import be.bds.bdsbes.payload.GioHangChiTietResponse;
import be.bds.bdsbes.service.dto.GioHangChiTietDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IGioHangChiTietService {

    PagedResponse<GioHangChiTietResponse> getAllByUser(int page, int size, Long idUser);

    int getCountInCart(Long idUser);

    GioHangChiTiet create(GioHangChiTietDTO gioHangChiTietDTO);

    GioHangChiTiet update(Long id, GioHangChiTietDTO gioHangChiTietDTO);

    GioHangChiTietResponse getOne(Long id);
}
