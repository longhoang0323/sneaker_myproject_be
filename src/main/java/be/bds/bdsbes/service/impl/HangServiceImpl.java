package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.payload.HangResponse;
import be.bds.bdsbes.repository.HangRepository;
import be.bds.bdsbes.service.dto.HangDTO;
import be.bds.bdsbes.service.iService.IHangService;
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
@Service("hangService")
public class HangServiceImpl implements IHangService {

    @Autowired
    HangRepository hangRepository;

    public int getNumberOfRecords() {
        Long count = hangRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        String autoCode = "H" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);

        return autoCode;
    }

    @Override
    public PagedResponse<HangResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<HangResponse> entities = hangRepository.getAll(pageable);
        List<HangResponse> dtos = entities.toList();
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
    public Hang create(HangDTO hangDTO) {
        Hang hang = hangDTO.dto(new Hang());
        hang.setMa(generateAutoCode());
        return hangRepository.save(hang);
    }

    @Override
    public Hang update(Long id,HangDTO hangDTO) {
        if(hangRepository.findById(id).isPresent()){
            Hang hang = hangRepository.findById(id).get();
            hang.setTen(hangDTO.getTen());
            return hangRepository.save(hang);
        }
        return null;
    }

    @Override
    public Hang get(Long id) {
        if(hangRepository.findById(id).isPresent()){
            return hangRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Hang updateTrangThai(Long id, HangDTO hangDTO) {
        if(hangRepository.findById(id).isPresent()){
            Hang hang = hangRepository.findById(id).get();
            hang.setTrangThai(hangDTO.getTrangThai());
            return hangRepository.save(hang);
        }
        return null;
    }
}
