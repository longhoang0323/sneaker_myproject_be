package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.entities.DatPhong;
import be.bds.bdsbes.entities.ThongTinNhanPhong;
import be.bds.bdsbes.payload.ThongTinNhanPhongResponse;
import be.bds.bdsbes.repository.DatPhongRepository;
import be.bds.bdsbes.repository.ThongTinNhanPhongRepository;
import be.bds.bdsbes.service.dto.ThongTinNhanPhongDTO;
import be.bds.bdsbes.service.iService.IThongTinNhanPhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThongTinNhanPhongServiceImpl implements IThongTinNhanPhongService {

    @Autowired
    private ThongTinNhanPhongRepository thongTinNhanPhongRepository;

    @Autowired
    private DatPhongRepository datPhongRepository;

    @Override
    public ThongTinNhanPhong create(ThongTinNhanPhongDTO thongTinNhanPhongDTO) {
        ThongTinNhanPhong thongTinNhanPhong = new ThongTinNhanPhong();
        thongTinNhanPhong.setIdDatPhong(DatPhong.builder().id(thongTinNhanPhongDTO.getIdDatPhong()).build());
        thongTinNhanPhong.setCccd(thongTinNhanPhongDTO.getCccdCheckIn());
        thongTinNhanPhong.setHoTen(thongTinNhanPhongDTO.getHoTenCheckIn());
        thongTinNhanPhong.setSdt(thongTinNhanPhongDTO.getSdtCheckIn());
        thongTinNhanPhong.setNgaySinh(thongTinNhanPhongDTO.getNgaySinhCheckIn());
        thongTinNhanPhong.setGioiTinh(thongTinNhanPhongDTO.getGioiTinhCheckIn());
        thongTinNhanPhong.setTrangThai(1);
        thongTinNhanPhong.setGhiChu(LocalDateTime.now().toString());
        if(datPhongRepository.findById(thongTinNhanPhongDTO.getIdDatPhong()).isPresent()){
            DatPhong datPhong = datPhongRepository.findById(thongTinNhanPhongDTO.getIdDatPhong()).get();
            datPhong.setSoNguoi(thongTinNhanPhongDTO.getSoNguoi());
            datPhong.setTrangThai(2);
            datPhongRepository.save(datPhong);
        }
        return thongTinNhanPhongRepository.save(thongTinNhanPhong);
    }

    @Override
    public ThongTinNhanPhongResponse getTTById(Long idDatPhong) {
        return thongTinNhanPhongRepository.getTTById(idDatPhong);
    }

    @Override
    public List<ThongTinNhanPhongResponse> getList() {
        return thongTinNhanPhongRepository.getList();
    }
}
