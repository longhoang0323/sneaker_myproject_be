package be.bds.bdsbes.resource;

import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.KhachHangResponse1;
import be.bds.bdsbes.service.dto.ChiTietSanPhamDTO;
import be.bds.bdsbes.service.iService.IKhachHangKhuyenMaiService;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/myproject/sneaker/khach-hang-khuyen-mai")
public class KhachHangKhuyenMaiController {

    @Autowired
    IKhachHangKhuyenMaiService iKhachHangKhuyenMaiService;

    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestParam(value = "idVoucher") Long idVoucher,
            @RequestBody List<KhachHangResponse1> khResponseList) {
        return ResponseUtil.wrap(iKhachHangKhuyenMaiService.saveAll(idVoucher, khResponseList));
    }

    @GetMapping("list-by-khach-hang")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "idKhachHang") Long idKhachHang) {
        try {
            return ResponseUtil.wrap(this.iKhachHangKhuyenMaiService.getAllByIDKhachHang(page, size, idKhachHang));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
