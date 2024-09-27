package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import be.bds.bdsbes.repository.ChiTietSanPhamRepository;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.service.iService.IChiTietSanPhamService;
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
@Service("chiTietSanPhamService")
public class ChiTietSanPhamServiceImpl implements IChiTietSanPhamService {

    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepository;

    public int getNumberOfRecords() {
        Long count = chiTietSanPhamRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        String autoCode = "CT" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);

        return autoCode;
    }


    @Override
    public PagedResponse<ChiTietSanPhamResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<ChiTietSanPhamResponse> entities = chiTietSanPhamRepository.getAll(pageable);
        List<ChiTietSanPhamResponse> dtos = entities.toList();
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
    public ChiTietSanPham create(ChiTietSanPhamDTO chiTietSanPhamDTO) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamDTO.dto(new ChiTietSanPham());
        chiTietSanPham.setMa(generateAutoCode());
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham update(Long id, ChiTietSanPhamDTO chiTietSanPhamDTO) {
        if(chiTietSanPhamRepository.findById(id).isPresent()){
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).get();
            chiTietSanPham.setSanPham(SanPham.builder().id(chiTietSanPhamDTO.getIdSanPham()).build());
            chiTietSanPham.setMauSac(MauSac.builder().id(chiTietSanPhamDTO.getIdMauSac()).build());
            chiTietSanPham.setKichThuoc(KichThuoc.builder().id(chiTietSanPhamDTO.getIdKichThuoc()).build());
            chiTietSanPham.setDonGia(chiTietSanPhamDTO.getDonGia());
            chiTietSanPham.setSoLuong(chiTietSanPhamDTO.getSoLuong());
            chiTietSanPham.setGhiChu(chiTietSanPhamDTO.getGhiChu());
            chiTietSanPham.setImage(chiTietSanPhamDTO.getImage());
            return chiTietSanPhamRepository.save(chiTietSanPham);
        }
        return null;
    }

    @Override
    public ChiTietSanPham get(Long id) {
        if(chiTietSanPhamRepository.findById(id).isPresent()){
            return chiTietSanPhamRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ChiTietSanPham updateTrangThai(Long id, ChiTietSanPhamDTO chiTietSanPhamDTO) {
        if(chiTietSanPhamRepository.findById(id).isPresent()){
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(id).get();
            chiTietSanPham.setTrangThai(chiTietSanPhamDTO.getTrangThai());
            return chiTietSanPhamRepository.save(chiTietSanPham);
        }
        return null;
    }
}