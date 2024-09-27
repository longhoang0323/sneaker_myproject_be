package be.bds.bdsbes.resource;


import be.bds.bdsbes.service.dto.KichThuocDTO;
import be.bds.bdsbes.service.iService.IKichThuocService;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/myproject/sneaker/kich-thuoc")
public class KichThuocController {

    @Autowired
    IKichThuocService iKichThuocService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iKichThuocService.getAll(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> get(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(iKichThuocService.get(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody @Valid KichThuocDTO kichThuocDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iKichThuocService.create(kichThuocDTO));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id,
                                    @RequestBody @Valid KichThuocDTO kichThuocDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iKichThuocService.update(id, kichThuocDTO));
    }

    @PutMapping("update-status")
    public ResponseEntity<?> updateStatus(@RequestParam(value = "id") Long id,
                                          @RequestBody KichThuocDTO kichThuocDTO) {
        return ResponseEntity.ok(iKichThuocService.updateTrangThai(id, kichThuocDTO));
    }
}
