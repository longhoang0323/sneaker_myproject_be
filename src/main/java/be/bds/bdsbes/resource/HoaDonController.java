package be.bds.bdsbes.resource;

import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.service.iService.IHoaDonService;
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
@RequestMapping("/myproject/sneaker/hoa-don")
public class HoaDonController {

    @Autowired
    IHoaDonService iHoaDonService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getAll(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("list-by-loai-hd")
    public ResponseEntity<?> getListByLoaiHD(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "loaiHoaDon") int loaiHoaDon) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getAllByLoaiHoaDon(page, size, loaiHoaDon));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> details(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(iHoaDonService.get(id));
    }

    @PostMapping("create-new-bill")
    public ResponseEntity<?> createNewBill(
            @RequestBody @Valid HoaDonDTO hoaDonDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iHoaDonService.createTaiQuay(hoaDonDTO));
    }

    @PostMapping("create-bill-online")
    public ResponseEntity<?> createBillOnline(
            @RequestBody @Valid HoaDonDTO hoaDonDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iHoaDonService.datHangOnline(hoaDonDTO));
    }

    @PutMapping("thanh-toan-tai-quay")
    public ResponseEntity<?> thanhToanTaiQuay(@RequestParam(value = "id") Long id,
                                              @RequestBody @Valid HoaDonDTO hoaDonDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iHoaDonService.thanhToanHoaDon(id, hoaDonDTO));
    }

    @GetMapping("get-by-ma")
    public ResponseEntity<?> getOneByMa(@RequestParam(value = "ma") String ma) {
        return ResponseEntity.ok(iHoaDonService.getOneByMa(ma));
    }

    @PutMapping("update-giao-hang")
    public ResponseEntity<?> updateTrangThaiGiaoHang(@RequestParam(value = "id") Long id,
                                              @RequestBody @Valid HoaDonDTO hoaDonDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iHoaDonService.updateTrangThaiGiaoHang(id, hoaDonDTO));
    }
}
