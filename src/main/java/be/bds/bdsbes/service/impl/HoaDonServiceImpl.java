package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.domain.User;
import be.bds.bdsbes.entities.HoaDon;
import be.bds.bdsbes.payload.HangResponse;
import be.bds.bdsbes.payload.HoaDonResponse;
import be.bds.bdsbes.repository.HoaDonRepository;
import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.service.iService.IHoaDonService;
import be.bds.bdsbes.utils.dto.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service("hoaDonService")
public class HoaDonServiceImpl implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public PagedResponse<HoaDonResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.Direction.DESC, "id");
        Page<HoaDonResponse> entities = hoaDonRepository.getAll(pageable);
        List<HoaDonResponse> dtos = entities.toList();
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
    public HoaDon createTaiQuay(HoaDonDTO hoaDonDTO) {
        LocalDateTime time = LocalDateTime.now();
        String maHd = "HD" + String.valueOf(time.getYear()).substring(2) +
                time.getMonthValue() + time.getDayOfMonth() + time.getHour() +
                time.getMinute() + time.getSecond();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMa(maHd);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setUser(User.builder().id(hoaDonDTO.getIdUser()).build());
        hoaDon.setTrangThai(0);
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDonResponse get(Long id) {
        return hoaDonRepository.getOneById(id);
    }
}
