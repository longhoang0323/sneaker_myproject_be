package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.domain.User;
import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.entities.GioHangChiTiet;
import be.bds.bdsbes.payload.GioHangChiTietResponse;
import be.bds.bdsbes.repository.GioHangChiTietRepository;
import be.bds.bdsbes.service.dto.GioHangChiTietDTO;
import be.bds.bdsbes.service.iService.IGioHangChiTietService;
import be.bds.bdsbes.utils.dto.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GioHangChiTietServiceImpl implements IGioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public PagedResponse<GioHangChiTietResponse> getAllByUser(int page, int size, Long idUser) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<GioHangChiTietResponse> entities = gioHangChiTietRepository.getAllByUser(pageable, idUser);
        List<GioHangChiTietResponse> dtos = entities.toList();
        return new PagedResponse<>(
                dtos,
                page,
                size,
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isLast(),
                entities.getSort().toString()
        );
    }

    @Override
    public int getCountInCart(Long idUser) {
        List<GioHangChiTiet> list = gioHangChiTietRepository.getAllListByUser(idUser);
        return list.size();
    }

    @Override
    public GioHangChiTiet create(GioHangChiTietDTO gioHangChiTietDTO) {
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setChiTietSanPham(ChiTietSanPham.builder().id(gioHangChiTietDTO.getIdChiTietSanPham()).build());
        gioHangChiTiet.setUser(User.builder().id(gioHangChiTietDTO.getIdUser()).build());
        gioHangChiTiet.setDonGia(gioHangChiTietDTO.getDonGia());
        gioHangChiTiet.setGiaBan(gioHangChiTietDTO.getGiaBan());
        gioHangChiTiet.setSoLuong(gioHangChiTietDTO.getSoLuong());
        gioHangChiTiet.setGiamGia(gioHangChiTietDTO.getGiamGia());
        gioHangChiTiet.setTrangThai(1);
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet update(Long id, GioHangChiTietDTO gioHangChiTietDTO) {
        return null;
    }

    @Override
    public GioHangChiTietResponse getOne(Long id) {
        return gioHangChiTietRepository.getOneById(id);
    }

}
