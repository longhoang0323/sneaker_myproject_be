package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.Hang;
import be.bds.bdsbes.payload.HangResponse;
import be.bds.bdsbes.service.dto.HangDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IHangService {

    PagedResponse<HangResponse> getAll(int page, int size);

    Hang create(HangDTO hangDTO);

    Hang update(Long id, HangDTO hangDTO);

    Hang get(Long id);

    Hang updateTrangThai(Long id, HangDTO hangDTO);
}
