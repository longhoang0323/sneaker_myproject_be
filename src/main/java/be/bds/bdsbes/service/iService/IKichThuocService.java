package be.bds.bdsbes.service.iService;

import be.bds.bdsbes.entities.KichThuoc;
import be.bds.bdsbes.payload.KichThuocResponse;
import be.bds.bdsbes.service.dto.KichThuocDTO;
import be.bds.bdsbes.utils.dto.PagedResponse;

public interface IKichThuocService {

    PagedResponse<KichThuocResponse> getAll(int page, int size);

    KichThuoc create(KichThuocDTO kichThuocDTO);

    KichThuoc update(Long id, KichThuocDTO kichThuocDTO);

    KichThuoc get(Long id);

    KichThuoc updateTrangThai(Long id, KichThuocDTO kichThuocDTO);
}
