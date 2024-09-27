package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.payload.MauSacResponse;
import be.bds.bdsbes.repository.MauSacRepository;
import be.bds.bdsbes.service.dto.MauSacDTO;
import be.bds.bdsbes.service.iService.IMauSacService;
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
@Service("mauSacService")
public class MauSacServiceImpl implements IMauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    public int getNumberOfRecords() {
        Long count = mauSacRepository.count();
        return count.intValue();
    }


    private String generateAutoCode() {
        int numberOfDigits = 2;

        int numberOfExistingRecords = getNumberOfRecords();

        String autoCode = "M" + String.format("%0" + numberOfDigits + "d", numberOfExistingRecords + 1);

        return autoCode;
    }

    @Override
    public PagedResponse<MauSacResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<MauSacResponse> entities = mauSacRepository.getAll(pageable);
        List<MauSacResponse> dtos = entities.toList();
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
    public MauSac create(MauSacDTO mauSacDTO) {
        MauSac mauSac = mauSacDTO.dto(new MauSac());
        mauSac.setMa(generateAutoCode());
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(Long id, MauSacDTO mauSacDTO) {
        if(mauSacRepository.findById(id).isPresent()){
            MauSac mauSac = mauSacRepository.findById(id).get();
            mauSac.setTen(mauSacDTO.getTen());
            return mauSacRepository.save(mauSac);
        }
        return null;
    }

    @Override
    public MauSac get(Long id) {
        if(mauSacRepository.findById(id).isPresent()){
            return mauSacRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public MauSac updateTrangThai(Long id, MauSacDTO mauSacDTO) {
        if(mauSacRepository.findById(id).isPresent()){
            MauSac mauSac = mauSacRepository.findById(id).get();
            mauSac.setTrangThai(mauSacDTO.getTrangThai());
            return mauSacRepository.save(mauSac);
        }
        return null;
    }
}
