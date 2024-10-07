package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.entities.HoaDonChiTiet;
import be.bds.bdsbes.payload.HoaDonChiTietResponse;
import be.bds.bdsbes.repository.HoaDonChiTietRepository;
import be.bds.bdsbes.service.dto.HoaDonChiTietDTO;
import be.bds.bdsbes.service.iService.IHoaDonChiTietService;
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
@Service("hoaDonChiTietService")
public class HoaDonChiTietServiceImpl implements IHoaDonChiTietService {

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public PagedResponse<HoaDonChiTietResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<HoaDonChiTietResponse> entities = hoaDonChiTietRepository.getAll(pageable);
        List<HoaDonChiTietResponse> dtos = entities.toList();
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
    public HoaDonChiTiet create(HoaDonChiTietDTO hoaDonChiTietDTO) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(HoaDon.builder().id(hoaDonChiTietDTO.getIdHoaDon()).build());
        hoaDonChiTiet.setChiTietSanPham(ChiTietSanPham.builder().id(hoaDonChiTietDTO.getIdChiTietSanPham()).build());
        hoaDonChiTiet.setDonGia(hoaDonChiTietDTO.getDonGia());
        hoaDonChiTiet.setSoLuong(hoaDonChiTietDTO.getSoLuong());
        hoaDonChiTiet.setGiaBan(hoaDonChiTietDTO.getGiaBan());
        hoaDonChiTiet.setGiamGia(hoaDonChiTietDTO.getGiamGia());
        hoaDonChiTiet.setTrangThai(0);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTietResponse get(Long id) {
        return null;
    }
}
