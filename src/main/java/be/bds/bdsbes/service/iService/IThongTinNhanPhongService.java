package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.ThongTinNhanPhong;
import be.bds.bdsbes.payload.ThongTinNhanPhongResponse;
import be.bds.bdsbes.service.dto.ThongTinNhanPhongDTO;

import java.util.List;

public interface IThongTinNhanPhongService {

    ThongTinNhanPhong create(ThongTinNhanPhongDTO thongTinNhanPhongDTO);

    ThongTinNhanPhongResponse getTTById(Long idDatPhong);

    List<ThongTinNhanPhongResponse> getList();
}
