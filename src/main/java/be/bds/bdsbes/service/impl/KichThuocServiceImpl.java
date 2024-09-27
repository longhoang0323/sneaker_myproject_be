package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.payload.KichThuocResponse;
import be.bds.bdsbes.repository.KichThuocRepository;
import be.bds.bdsbes.service.dto.KichThuocDTO;
import be.bds.bdsbes.service.iService.IKichThuocService;
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
@Service("kichThuocService")
public class KichThuocServiceImpl implements IKichThuocService {

    @Autowired
    KichThuocRepository kichThuocRepository;

    public int getNumberOfRecords() {
        Long count = kichThuocRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        String autoCode = "SIZE" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);

        return autoCode;
    }

    @Override
    public PagedResponse<KichThuocResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<KichThuocResponse> entities = kichThuocRepository.getAll(pageable);
        List<KichThuocResponse> dtos = entities.toList();
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
    public KichThuoc create(KichThuocDTO kichThuocDTO) {
        KichThuoc kichThuoc = kichThuocDTO.dto(new KichThuoc());
        kichThuoc.setMa(generateAutoCode());
        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public KichThuoc update(Long id, KichThuocDTO kichThuocDTO) {
        if(kichThuocRepository.findById(id).isPresent()){
            KichThuoc kichThuoc = kichThuocRepository.findById(id).get();
            kichThuoc.setTen(kichThuocDTO.getTen());
            return kichThuocRepository.save(kichThuoc);
        }
        return null;
    }

    @Override
    public KichThuoc get(Long id) {
        if(kichThuocRepository.findById(id).isPresent()){
            return kichThuocRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public KichThuoc updateTrangThai(Long id, KichThuocDTO kichThuocDTO) {
        if(kichThuocRepository.findById(id).isPresent()){
            KichThuoc kichThuoc = kichThuocRepository.findById(id).get();
            kichThuoc.setTrangThai(kichThuocDTO.getTrangThai());
            return kichThuocRepository.save(kichThuoc);
        }
        return null;
    }
}
