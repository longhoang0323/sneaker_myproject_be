package be.bds.bdsbes.resource;


import be.bds.bdsbes.service.dto.HoaDonChiTietDTO;
import be.bds.bdsbes.service.iService.IHoaDonChiTietService;
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
@RequestMapping("/myproject/sneaker/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    IHoaDonChiTietService iHoaDonChiTietService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iHoaDonChiTietService.getAll(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @PostMapping("create-new-hdct")
    public ResponseEntity<?> createNewHDCT(
            @RequestBody @Valid HoaDonChiTietDTO hoaDonChiTietDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iHoaDonChiTietService.create(hoaDonChiTietDTO));
    }
}
