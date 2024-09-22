package be.bds.bdsbes.resource;

import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.service.iService.IHoaDonService;
import be.bds.bdsbes.service.dto.HoaDonDTO;
import be.bds.bdsbes.service.impl.PdfGenerator;
import be.bds.bdsbes.service.impl.Test;
import be.bds.bdsbes.utils.ApiError;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ResponseUtil;
import be.bds.bdsbes.utils.StatusError;
import com.itextpdf.text.DocumentException;
import fr.opensagres.xdocreport.core.XDocReportException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rpc/bds/hoa-don")
public class HoaDonController {

    @Autowired
    IHoaDonService iHoaDonService;

    @Autowired
    PdfGenerator pdfGenerator;

    @Autowired
    Test test;

    @GetMapping("list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getHoaDon(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("list-by-customer")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "hoTen", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) String hoTen,
            @RequestParam(value = "sdt", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) String sdt) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getHoaDonByCustomer(page, size, hoTen, sdt));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody HoaDonDTO hoaDonDTO) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.create(hoaDonDTO));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("create-or-update")
    public ResponseEntity<?> createOrUpdate(@RequestBody HoaDonDTO hoaDonDTO) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.createOrUpdate(hoaDonDTO));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/generate-hoa-don")
    public void generatePDF(@RequestParam(value = "id") Long id, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGenerator.export(response, id);
    }

    @PutMapping("update-tong-tien")
    public ResponseEntity<?> updateTongTien(@RequestBody HoaDonDTO hoaDonDTO) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.updateTongTien(hoaDonDTO));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> getOne(@RequestParam(value = "id") Long id) {
        if (iHoaDonService.getOne(id) == null) {
            return ResponseEntity.badRequest().body("Không tồn tại");
        }
        return ResponseEntity.ok(iHoaDonService.getOne(id));
    }

    @PutMapping("update-status")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id, @RequestBody Integer trangThai) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.updateTrangThai(trangThai, id));
        } catch (ServiceException e) {
            ApiError apiError = new ApiError(String.valueOf(StatusError.Failed), e.getMessage());
            return ResponseUtil.wrap(apiError);
        }
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteHD() {
        return ResponseUtil.wrap(this.iHoaDonService.deleteHoaDon());
    }

    @GetMapping("list-by-search")
    public ResponseEntity<?> getListbySearch(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "input", defaultValue = "") String searchInput,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai,
            @RequestParam(value = "startDate", defaultValue = "") String startDate,
            @RequestParam(value = "endDate", defaultValue = "") String endDate) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getHoaDonBySearch(page, size, searchInput, trangThai, startDate, endDate));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("create-or-update-tai-quay")
    public ResponseEntity<?> createOrUpdateTaiQuay(@RequestBody HoaDonDTO hoaDonDTO) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.createTaiQuay(hoaDonDTO));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("doanh-thu-by-day")
    public ResponseEntity<?> doanhthubyday(@RequestParam(value = "checkIn") String checkIn,@RequestParam(value = "checkOut") String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedCheckIn = LocalDate.parse(checkIn, formatter);
        LocalDate parsedCheckOut = LocalDate.parse(checkOut, formatter);
        return ResponseUtil.wrap(this.iHoaDonService.getDoanhThuByDay(parsedCheckIn,parsedCheckOut));
    }

    @GetMapping("doanh-thu-by-to-day")
    public ResponseEntity<?> doanhThuByTime(@RequestParam(value = "year") int year,@RequestParam(value = "month") int month,@RequestParam(value = "day") int day) {
        return ResponseUtil.wrap(this.iHoaDonService.getDoanhThuByToDay(day, month, year));
    }

    @GetMapping("doanh-thu-by-month")
    public ResponseEntity<?> doanhThuByMonth(@RequestParam(value = "year") int year,@RequestParam(value = "month") int month) {
        return ResponseUtil.wrap(this.iHoaDonService.getDoanhThuByMonth(month, year));
    }

    @GetMapping("doanh-thu-by-year")
    public ResponseEntity<?> doanhThuByYear(@RequestParam(value = "year") int year) {
        return ResponseUtil.wrap(this.iHoaDonService.getDoanhThuByYear(year));
    }

    @GetMapping("all-doanh-thu")
    public ResponseEntity<?> getAllDoanhThu() {
        return ResponseUtil.wrap(this.iHoaDonService.getAllDoanhThu());
    }

    @PutMapping("tinh-tien-dich-vu")
    public ResponseEntity<?> tinhTienDichVu(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tongTien) throws ServiceException {
        return ResponseUtil.wrap(this.iHoaDonService.updateTienDichVu(tongTien, id));
    }


    @PostMapping("up-rank-customer")
    public ResponseEntity<?> updateRankCustomer(@RequestParam(value = "id") Long id, @RequestBody Long idTheThanhVien) {
         return ResponseUtil.wrap(
                 this.iHoaDonService.updateRankKhachHang(id , idTheThanhVien)
         );
    }

    @GetMapping("get-tong-tien-by-customer")
    public ResponseEntity<?> getTongTienByCustomer(@RequestParam(value = "id") Long id) {
        return ResponseUtil.wrap(
                this.iHoaDonService.getTongTienByKhachHang(id)
        );
    }

    @GetMapping("find-by-customer")
    public ResponseEntity<?> findByCustomer(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "id") Long id) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.findBillByCustomer(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @PostMapping("update-tien-coc")
    public ResponseEntity<?> updateTienCoc(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienCoc) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienCocbyId(tienCoc, LocalDateTime.now(), id)
        );
    }

    @PostMapping("update-tien-phong")
    public ResponseEntity<?> updateTienPhong(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienPhong) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienPhongbyId(tienPhong, id)
        );
    }

    @PostMapping("update-tien-phat")
    public ResponseEntity<?> updateTienPhat(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienPhat) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienPhatbyId(tienPhat, id)
        );
    }

    @PostMapping("update-tien-dich-vu")
    public ResponseEntity<?> updateTienDichVu(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienDichVu) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienDichVubyId(tienDichVu, id)
        );
    }

    @PostMapping("update-tien-tich-diem")
    public ResponseEntity<?> updateTienTichDiem(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienTichDiem) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienTichDiembyId(tienTichDiem, id)
        );
    }

    @GetMapping("list-by-trang-thai")
    public ResponseEntity<?> getListbyTrangThai(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "input", defaultValue = "") String searchInput,
            @RequestParam(value = "trangThai", defaultValue = "") int trangThai) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getListByTrangThai(page, size, trangThai));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("update-tien-hoan-lai")
    public ResponseEntity<?> updateTienHoanLai(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienHoanLai) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateTienHoanLaibyId(tienHoanLai, id)
        );
    }

    @GetMapping("list-xac-nhan")
    public ResponseEntity<?> getListXacNhan(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iHoaDonService.getListXacNhan(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("update-tien-hoa-don")
    public ResponseEntity<?> updateTienHoaDon(@RequestParam(value = "id") Long id,
                                              @RequestBody HoaDonDTO hoaDonDTO) {
        return ResponseUtil.wrap(
                this.iHoaDonService.updateHoaDonById(id, hoaDonDTO)
        );
    }
}
