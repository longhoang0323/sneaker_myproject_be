package be.bds.bdsbes.resource;

import be.bds.bdsbes.service.dto.GioHangChiTietDTO;
import be.bds.bdsbes.service.iService.IGioHangChiTietService;
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
@RequestMapping("/myproject/sneaker/gio-hang")
public class GioHangChiTietController {

    @Autowired
    IGioHangChiTietService iGioHangChiTietService;

    @GetMapping("list-by-user")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "idUser") Long idUser) {
        try {
            return ResponseUtil.wrap(this.iGioHangChiTietService.getAllByUser(page, size, idUser));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("count-cart-by-user")
    public ResponseEntity<?> getList(@RequestParam(value = "idUser") Long idUser) {
            return ResponseEntity.ok(iGioHangChiTietService.getCountInCart(idUser));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(
            @RequestBody @Valid GioHangChiTietDTO gioHangChiTietDTO, BindingResult bindingResult) {
        return ResponseEntity.ok(iGioHangChiTietService.create(gioHangChiTietDTO));
    }
}
