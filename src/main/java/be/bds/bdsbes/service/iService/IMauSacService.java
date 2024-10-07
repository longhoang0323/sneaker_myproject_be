package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.MauSac;
import be.bds.bdsbes.payload.MauSacResponse;
import be.bds.bdsbes.service.dto.MauSacDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IMauSacService {

    PagedResponse<MauSacResponse> getAll(int page, int size);

    MauSac create(MauSacDTO mauSacDTO);

    MauSac update(Long id, MauSacDTO mauSacDTO);

    MauSac get(Long id);

    MauSac updateTrangThai(Long id, MauSacDTO mauSacDTO);
}
