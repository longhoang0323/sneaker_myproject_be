package be.bds.bdsbes.resource;


import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.service.iService.IChiTietSanPhamService;
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
@RequestMapping("/myproject/sneaker/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    IChiTietSanPhamService iChiTietSanPhamService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iChiTietSanPhamService.getAll(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> get(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(iChiTietSanPhamService.get(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody @Valid ChiTietSanPhamDTO chiTietSanPhamDTO) {
        try {
            return ResponseUtil.wrap(iChiTietSanPhamService.create(chiTietSanPhamDTO));
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id,
                                    @RequestBody @Valid ChiTietSanPhamDTO chiTietSanPhamDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iChiTietSanPhamService.update(id, chiTietSanPhamDTO));
    }

    @PutMapping("update-status")
    public ResponseEntity<?> updateStatus(@RequestParam(value = "id") Long id,
                                          @RequestBody int trangThai) {
        return ResponseEntity.ok(iChiTietSanPhamService.updateTrangThai(id, trangThai));
    }

    @GetMapping("list-by-san-pham")
    public ResponseEntity<?> getListBySanPham(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "idSanPham") Long id) {
        try {
            return ResponseUtil.wrap(this.iChiTietSanPhamService.getAllBySanPham(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("get-one-by-color-and-size")
    public ResponseEntity<?> details(@RequestParam(value = "idMauSac") Long idMauSac,
                                     @RequestParam(value = "idKichThuoc") Long idKichThuoc,
                                     @RequestParam(value = "idSanPham") Long idSanPham){
        try{
        return ResponseEntity.ok(iChiTietSanPhamService.getByColorAndSize(idMauSac, idKichThuoc, idSanPham));
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @GetMapping("list-by-san-pham-and-color")
    public ResponseEntity<?> getListBySanPhamAndColor(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "idSanPham") Long id,
            @RequestParam(value = "idMauSac") Long idMauSac) {
        try {
            return ResponseUtil.wrap(this.iChiTietSanPhamService.getAllBySanPhamAndColor(page, size, id, idMauSac));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("get-one-by-ma")
    public ResponseEntity<?> getOneByMa(@RequestParam(value = "ma") String ma) {
        return ResponseEntity.ok(iChiTietSanPhamService.getByMaCTSP(ma));
    }

    @PutMapping("update-so-luong")
    public ResponseEntity<?> updateSoLuong(@RequestParam(value = "id") Long id,
                                          @RequestBody int soLuong) {
        return ResponseEntity.ok(iChiTietSanPhamService.updateSoLuong(id, soLuong));
    }
}
