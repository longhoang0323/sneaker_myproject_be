package be.bds.bdsbes.resource;

import be.bds.bdsbes.service.dto.SanPhamDTO;
import be.bds.bdsbes.service.iService.ISanPhamService;
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
@RequestMapping("/myproject/sneaker/san-pham")
public class SanPhamController {

    @Autowired
    ISanPhamService iSanPhamService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iSanPhamService.getAll(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> get(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(iSanPhamService.get(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody @Valid SanPhamDTO sanPhamDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iSanPhamService.create(sanPhamDTO));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id,
                                    @RequestBody @Valid SanPhamDTO sanPhamDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iSanPhamService.update(id, sanPhamDTO));
    }

    @PutMapping("update-status")
    public ResponseEntity<?> updateStatus(@RequestParam(value = "id") Long id,
                                          @RequestBody SanPhamDTO sanPhamDTO) {
        return ResponseEntity.ok(iSanPhamService.updateTrangThai(id, sanPhamDTO));
    }
}