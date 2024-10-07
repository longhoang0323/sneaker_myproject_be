package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.ChatLieu;
import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.payload.SanPhamResponse;
import be.bds.bdsbes.repository.SanPhamRepository;
import be.bds.bdsbes.service.dto.SanPhamDTO;
import be.bds.bdsbes.service.iService.ISanPhamService;
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
@Service("sanPhamService")
public class SanPhamServiceImpl implements ISanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    public int getNumberOfRecords() {
        Long count = sanPhamRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        return "SP" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);
    }

    @Override
    public PagedResponse<SanPhamResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<SanPhamResponse> entities = sanPhamRepository.getAll(pageable);
        List<SanPhamResponse> dtos = entities.toList();
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
    public SanPham create(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = sanPhamDTO.dto(new SanPham());
        sanPham.setMa(generateAutoCode());
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(Long id, SanPhamDTO sanPhamDTO) {
        if(sanPhamRepository.findById(id).isPresent()){
            SanPham sanPham = sanPhamRepository.findById(id).get();
            sanPham.setTen(sanPhamDTO.getTen());
            sanPham.setHang(Hang.builder().id(sanPhamDTO.getIdHang()).build());
            sanPham.setChatLieu(ChatLieu.builder().id(sanPhamDTO.getIdChatLieu()).build());
            return sanPhamRepository.save(sanPham);
        }
        return null;
    }

    @Override
    public SanPhamResponse get(Long id) {
        return sanPhamRepository.getOneById(id);
    }

    @Override
    public int updateTrangThai(Long id, int trangThai) {
        this.sanPhamRepository.updateTrangThaiById(trangThai, id);
        return 1;
    }

    @Override
    public int updateImageDefault(Long id, String imageDefault) {
        this.sanPhamRepository.updateImageDefaultById(imageDefault, id);
        return 1;
    }
}
