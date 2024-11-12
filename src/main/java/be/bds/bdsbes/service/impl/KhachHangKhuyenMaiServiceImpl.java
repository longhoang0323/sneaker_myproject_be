package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.KhachHang;
import be.bds.bdsbes.entities.KhachHangKhuyenMai;
import be.bds.bdsbes.entities.Voucher;
import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangKhuyenMaiResponse;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.repository.KhachHangKhuyenMaiRepository;
import be.bds.bdsbes.service.iService.IKhachHangKhuyenMaiService;
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
public class KhachHangKhuyenMaiServiceImpl implements IKhachHangKhuyenMaiService {

    @Autowired
    KhachHangKhuyenMaiRepository khachHangKhuyenMaiRepository;

    @Override
    public List<KhachHangKhuyenMai> saveAll(Long idVoucher, List<KhachHangResponse1> listKh) {
        for(KhachHangResponse1 kh: listKh){
            KhachHangKhuyenMai khachHangKhuyenMai = new KhachHangKhuyenMai();
            khachHangKhuyenMai.setKhachHang(KhachHang.builder().id(kh.getId()).build());
            khachHangKhuyenMai.setVoucher(Voucher.builder().id(idVoucher).build());
            khachHangKhuyenMai.setTrangThai(1);
            this.khachHangKhuyenMaiRepository.save(khachHangKhuyenMai);
        }
        return khachHangKhuyenMaiRepository.findAll();
    }

    @Override
    public PagedResponse<KhachHangKhuyenMaiResponse> getAllByIDKhachHang(int page, int size, Long idKhachHang) throws ServiceException {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.ASC, "id");
        Page<KhachHangKhuyenMaiResponse> entities = khachHangKhuyenMaiRepository.getAllByIdKhachHang(pageable, idKhachHang);
        List<KhachHangKhuyenMaiResponse> dtos = entities.toList();

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
}
