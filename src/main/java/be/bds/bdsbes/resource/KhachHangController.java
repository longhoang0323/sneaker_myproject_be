package be.bds.bdsbes.resource;

import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.service.iService.IKhachHangService;
import be.bds.bdsbes.service.dto.KhachHangDTO;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rpc/bds/khach-hang")
public class KhachHangController {

    @Autowired
    IKhachHangService khachHangService;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.khachHangService.getKhachHang(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> getOne(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(khachHangService.getOne(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid KhachHangDTO khachHangDTO){
//        if(result.hasErrors()){
//            List<ObjectError> errorList = result.getAllErrors();
//            return ResponseEntity.badRequest().body(errorList);
//        }
//        return ResponseEntity.ok(khachHangService.createOrUpdate(khachHangDTO));
        try {
            return ResponseUtil.wrap(this.khachHangService.createOrUpdate(khachHangDTO));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id, @RequestBody @Valid KhachHangDTO khachHangDTO, BindingResult result){
//        if(result.hasErrors()){
//            List<ObjectError> errorList = result.getAllErrors();
//            return ResponseEntity.badRequest().body(errorList);
//        }
        return ResponseEntity.ok(khachHangService.update(khachHangDTO, id));
    }

    @GetMapping("find-by-cccd")
    public ResponseEntity<?> findByCCCD(@RequestParam(value = "cccd") String cccd){
        return ResponseEntity.ok(khachHangService.findIdByCCCD(cccd));
    }

    @GetMapping("get-khach-hang-by-user")
    public ResponseEntity<?> getKhachHangByUser(@RequestParam( value = "id") Long id){
        return ResponseEntity.ok(khachHangService.getKhachHangbyUser(id));
    }

    @PutMapping("update-customer")
    public ResponseEntity<?> updateCustomer(@RequestParam(value = "id") Long id, @RequestBody @Valid KhachHangDTO khachHangDTO, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            return ResponseEntity.badRequest().body(errorList);
        }
        return ResponseUtil.wrap(khachHangService.updateKH(khachHangDTO, id));
    }

    @GetMapping("get-khach-hang-by-cccd")
    public ResponseEntity<?> getKHbyCCCD(@RequestParam(value = "cccd") String cccd){
        return ResponseEntity.ok(khachHangService.getKHbyCccd(cccd));
    }

    @PutMapping("update-ghi-chu")
    public ResponseEntity<?> updateGhiChu(@RequestBody String ghiChu, @RequestParam(value = "id") Long id) throws MessagingException {
        return ResponseUtil.wrap(khachHangService.updateGhiChu(ghiChu, id));
    }

    @GetMapping("list-by-search")
    public ResponseEntity<?> getListBySearch(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "inputSearch", defaultValue = "") String inputSearch) {
        try {
            return ResponseUtil.wrap(this.khachHangService.getListBySearch(page, size, inputSearch));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("tinh-lai-giam-gia")
    public ResponseEntity<?> updateGhiChu2(@RequestBody String ghiChu, @RequestParam(value = "id") Long id) throws MessagingException{
        return ResponseUtil.wrap(khachHangService.updateGhiChu2(ghiChu, id));
    }

    @GetMapping("send-point-to-customer")
    public ResponseEntity<?> sendPointsToCustomer(@RequestParam(value = "id") Long id) throws MessagingException {
            return ResponseUtil.wrap(this.khachHangService.sendPointstoCustomer(id));
    }
}