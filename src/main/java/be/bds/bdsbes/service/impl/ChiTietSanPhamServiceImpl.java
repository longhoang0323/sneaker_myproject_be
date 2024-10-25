package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.ChiTietSanPham;
import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.entities.SanPham;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.ChiTietSanPhamResponse;
import be.bds.bdsbes.repository.ChiTietSanPhamRepository;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.service.iService.IChiTietSanPhamService;
import be.bds.bdsbes.utils.ServiceExceptionBuilderUtil;
import be.bds.bdsbes.utils.ValidationErrorUtil;
import be.bds.bdsbes.utils.dto.PagedResponse;
import be.bds.bdsbes.utils.dto.ValidationErrorResponse;
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
    public PagedResponse<ChiTietSanPhamResponse> getAllBySanPham(int page, int size, Long id) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<ChiTietSanPhamResponse> entities = chiTietSanPhamRepository.getAllBySP(pageable, id);
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
    public ChiTietSanPham create(ChiTietSanPhamDTO chiTietSanPhamDTO) throws ServiceException {
        List<ChiTietSanPham> list = chiTietSanPhamRepository.findAll();
        for (ChiTietSanPham ct: list) {
            if(ct.getSanPham().getId().equals(chiTietSanPhamDTO.getIdSanPham()) &&
               ct.getMauSac().getId().equals(chiTietSanPhamDTO.getIdMauSac()) &&
               ct.getKichThuoc().getId().equals(chiTietSanPhamDTO.getIdKichThuoc())){
                throw ServiceExceptionBuilderUtil.newBuilder()
                        .addError(new ValidationErrorResponse("idSanPham", ValidationErrorUtil.IdSanPham))
                        .build();
            }
        }
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
            chiTietSanPham.setGiaBan(chiTietSanPhamDTO.getGiaBan());
            chiTietSanPham.setSoLuong(chiTietSanPhamDTO.getSoLuong());
            chiTietSanPham.setGhiChu(chiTietSanPhamDTO.getGhiChu());
            chiTietSanPham.setImage(chiTietSanPhamDTO.getImage());
            return chiTietSanPhamRepository.save(chiTietSanPham);
        }
        return null;
    }

    @Override
    public ChiTietSanPhamResponse get(Long id) {
        return chiTietSanPhamRepository.getCTSPById(id);
    }

    @Override
    public int updateTrangThai(Long id, int trangThai) {
        this.chiTietSanPhamRepository.updateTrangThaiById(trangThai, id);
        return 1;
    }

    @Override
    public ChiTietSanPhamResponse getByColorAndSize(Long idMauSac, Long idKichThuoc, Long idSanPham) throws ServiceException {
        ChiTietSanPhamResponse chiTietSanPhamResponse = chiTietSanPhamRepository.getCTSPByColorAndSize(idMauSac, idKichThuoc, idSanPham);
        System.out.println(chiTietSanPhamResponse);
        if(chiTietSanPhamResponse == null){
            throw ServiceExceptionBuilderUtil.newBuilder()
                    .addError(new ValidationErrorResponse("checkIdChiTietSanPham", ValidationErrorUtil.checkIdChiTietSanPham))
                    .build();
        }
        return chiTietSanPhamResponse;
    }

    @Override
    public PagedResponse<ChiTietSanPhamResponse> getAllBySanPhamAndColor(int page, int size, Long id, Long idMauSac) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<ChiTietSanPhamResponse> entities = chiTietSanPhamRepository.getAllBySPAndColor(pageable, id, idMauSac);
        List<ChiTietSanPhamResponse> dtos = entities.toList();
        return new PagedResponse<>(
                dtos,
                page,
                size,
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isLast(),
                entities.getSort().toString()
        );    }
}
