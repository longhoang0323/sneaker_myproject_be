package be.bds.bdsbes.resource;

import be.bds.bdsbes.exception.ServiceException;
import be.bds.bdsbes.payload.CheckOutRequest;
import be.bds.bdsbes.repository.DatPhongRepository;
import be.bds.bdsbes.service.dto.DatPhongDTO;
import be.bds.bdsbes.service.dto.MonthlyBookingDTO;
import be.bds.bdsbes.service.iService.IDatPhongService;
import be.bds.bdsbes.service.impl.PdfGenerator;
import be.bds.bdsbes.utils.ApiError;
import be.bds.bdsbes.utils.AppConstantsUtil;
import be.bds.bdsbes.utils.ResponseUtil;
import be.bds.bdsbes.utils.StatusError;
import be.bds.bdsbes.utils.dto.ServiceException1;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/rpc/bds/dat-phong")
public class DatPhongController {
    @Autowired
    private DatPhongRepository datPhongRepository;

    @Autowired
    IDatPhongService iDatPhongService;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getRoomOrder(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("detail")
    public ResponseEntity<?> getOne(@RequestParam(value = "id") Long id) {
        if (iDatPhongService.getOne(id) == null) {
            return ResponseEntity.badRequest().body("Không tồn tại");
        }
        return ResponseEntity.ok(iDatPhongService.getOne(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody DatPhongDTO datPhongDTO) {
        try {
            Boolean response = iDatPhongService.create(datPhongDTO);
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }

    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id, @Valid @RequestBody DatPhongDTO datPhongDTO, BindingResult result) {
        if (iDatPhongService.update(datPhongDTO, id) == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy");
        }
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok().body(iDatPhongService.update(datPhongDTO, id));
        }
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(@RequestParam(value = "id") Long id, HttpServletResponse response) throws IOException, DocumentException, ParseException {
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGenerator.export(response, id);
    }

    @GetMapping("/generateQR")
    public byte[] generateQRCode(@RequestParam("data") String data) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @GetMapping("/list-room-order-by-user")
    public ResponseEntity<?> getListByUser(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "id", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) Long id) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getRoomOderByUser(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/lich-su-dat-phong")
    public ResponseEntity<?> getLichSuDatPhong(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "id", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) Long id
    ) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getLichSuDatPhong(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list-room-order-by-upper-price")
    public ResponseEntity<?> getListRoomByUpperPrice(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "giaPhong", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) BigDecimal giaPhong,
            @RequestParam(value = "checkIn", defaultValue = "") String checkIn,
            @RequestParam(value = "checkOut", defaultValue = "") String checkOut,
            @RequestParam(value = "id") Long id
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDateTime parsedCheckIn = LocalDate.parse(checkIn, formatter).atStartOfDay();
//            LocalDateTime parsedCheckOut = LocalDate.parse(checkOut, formatter).atStartOfDay();
            return ResponseUtil.wrap(this.iDatPhongService.getPhongByUpperPrice(page, size, giaPhong, id, LocalDateTime.parse(checkIn), LocalDateTime.parse(checkOut)));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("update-status")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.updateTrangThai(id));
        } catch (ServiceException e) {
            ApiError apiError = new ApiError(String.valueOf(StatusError.Failed), e.getMessage());
            return ResponseUtil.wrap(apiError);
        }
    }

    @PutMapping("update-stt")
    public ResponseEntity<?> updateStatus(@RequestParam(value = "id") Long id, @RequestBody Integer trangThai) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.updateStatus(trangThai, id));
        } catch (ServiceException e) {
            ApiError apiError = new ApiError(String.valueOf(StatusError.Failed), e.getMessage());
            return ResponseUtil.wrap(apiError);
        }
    }


    @GetMapping("/generate-bill")
    public ResponseEntity<?> generateInvoice(@RequestParam(value = "id") Long id) {
        try {
            pdfGenerator.exportPdf(id);
            FileInputStream pdfInputStream = new FileInputStream("src/main/resources/template/output/datphong.pdf");
            // Trả về tệp PDF dưới dạng InputStreamResource
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=datphong.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfInputStream));
        } catch (Exception e) {
            e.printStackTrace();
            // Trả về lỗi nếu có vấn đề trong quá trình tạo hóa đơn
            return ResponseEntity.status(500).body(null);
        }
    }

//    @PutMapping("update-id-hoa-don")
//    public ResponseEntity<?> updateHoaDonByDatPhong(@RequestParam (value = "id") Long id){
//
//    }

//    @GetMapping("/generate-bill")
//    public void generateInvoice(@RequestParam(value = "id") Long id) {
//        this.pdfGenerator.exportPdf2(id);
//    }

    @PutMapping("update-dat-phong")
    public ResponseEntity<?> updateDatPhongById(
            @RequestParam(value = "id") Long id, @Valid @RequestBody DatPhongDTO datPhongDTO
    ) {
        try {
            Integer response = iDatPhongService.updateDatPhong(id, datPhongDTO);
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @GetMapping("/list-room-of-bill")
    public ResponseEntity<?> getListOrderOfBill(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "userId", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) Long userId) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getRoomOfBill(page, size, userId));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list-order-of-bill")
    public ResponseEntity<?> getDatPhongByHoaDon(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "id", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) Long id) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getDatPhongByHoaDon(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("create-list-room-order")
    public ResponseEntity<?> createListRoom(@Valid @RequestBody List<DatPhongDTO> datPhongDTOList) {
        try {
            Boolean response = iDatPhongService.createListRoom(datPhongDTOList);
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @GetMapping("so-phong-da-dat")
    public ResponseEntity<?> sophongdadat(@RequestParam(value = "checkIn") String checkIn, @RequestParam(value = "checkOut") String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedCheckIn = LocalDate.parse(checkIn, formatter);
        LocalDate parsedCheckOut = LocalDate.parse(checkOut, formatter);
        return ResponseUtil.wrap(this.iDatPhongService.getSoPhongDaDat(parsedCheckIn, parsedCheckOut));
    }

    @GetMapping("so-phong-da-dat-by-day")
    public ResponseEntity<?> soPhongDaDatByDay(@RequestParam(value = "year") int year, @RequestParam(value = "month") int month, @RequestParam(value = "day") int day) {
        return ResponseUtil.wrap(this.iDatPhongService.getSoPhongDaDatByToDay(day, month, year));
    }

    @GetMapping("so-phong-da-dat-by-month")
    public ResponseEntity<?> soPhongDaDatByMonth(@RequestParam(value = "year") int year, @RequestParam(value = "month") int month) {
        return ResponseUtil.wrap(this.iDatPhongService.getSoPhongDaDatByMonth(month, year));
    }

    @GetMapping("so-phong-da-dat-by-year")
    public ResponseEntity<?> soPhongDaDatByYear(@RequestParam(value = "year") int year) {
        return ResponseUtil.wrap(this.iDatPhongService.getSoPhongDaDatByYear(year));
    }

    @PostMapping("dat-phong-tai-quay")
    public ResponseEntity<?> datPhongTaiQuay(@Valid @RequestBody DatPhongDTO datPhongDTO) {
        try {
            Boolean response = iDatPhongService.datPhongTaiQuay(datPhongDTO);
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @GetMapping("/list-order-of-customer")
    public ResponseEntity<?> getDatPhongByKH(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "id", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) Long id) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getDatPhongByKH(page, size, id));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list-mapper")
    public ResponseEntity<?> getListMapping(
            @RequestParam(value = "page", defaultValue = AppConstantsUtil.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstantsUtil.DEFAULT_PAGE_SIZE) int size
    ) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.getListDatPhongMapping(page, size));
        } catch (Exception ex) {
            log.error(this.getClass().getName(), ex);
            return ResponseUtil.generateErrorResponse(ex);
        }
    }

    @GetMapping("/list-map-by-hoa-don")
    public ResponseEntity<?> getListMappingByHD(@RequestParam(name = "id") Long id, @RequestParam(name = "idHD") Long idHD) {
        return ResponseEntity.ok(this.iDatPhongService.getListMappingByHD(id, idHD));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> get(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(this.iDatPhongService.getPhongById(id));
    }

    @GetMapping("/list-map-by-search")
    public ResponseEntity<?> getListMappingBySearch
            (@RequestParam(name = "id") Long id,
             @RequestParam(value = "checkIn", defaultValue = "") String checkIn,
             @RequestParam(value = "checkOut", defaultValue = "") String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ResponseEntity.ok(this.iDatPhongService.getListMappingByDate(id, LocalDate.parse(checkIn, formatter), LocalDate.parse(checkOut, formatter)));
    }

    @PutMapping("doi-phong-by-id")
    public ResponseEntity<?> doiPhongById(
            @RequestBody Long idPhong,
            @RequestParam(value = "id") Long id
    ) {
        try {
            Integer response = iDatPhongService.doiPhongById(idPhong, id);
            return ResponseUtil.wrap(response);
        } catch (ServiceException e) {
            log.error(this.getClass().getName(), e);
            return ResponseUtil.generateErrorResponse(e);
        }
    }

    @GetMapping("/list-check-out-today")
    public ResponseEntity<?> getListCheckOutToDay(
            @RequestParam(value = "checkOut", defaultValue = "") String checkOut
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.now());
        return ResponseEntity.ok(this.iDatPhongService.getListCheckOutToday(LocalDate.now()));
    }

    @GetMapping("/get-room-check-in-today")
    public ResponseEntity<?> getRoomCheckInToday(@RequestParam(name = "id") Long id,
                                                 @RequestParam(value = "checkIn", defaultValue = "") String checkIn) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ResponseEntity.ok(this.iDatPhongService.getRoomCheckInToday(LocalDate.parse(checkIn, formatter), id));
    }

    @GetMapping("/monthly-bookings")
    public ResponseEntity<?> getMonthlyBookings() {
        List<MonthlyBookingDTO> dtoList = iDatPhongService.getMonthlyBookings();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/list-room-books")
    public ResponseEntity<?> getListRoomBook(@RequestBody Map<String, String> payload) {
        String cccd = payload.get("cccd");
        return ResponseEntity.ok(this.iDatPhongService.listRoomBooks(cccd));
    }

    @GetMapping("/list-by-customer-and-check-in")
    public ResponseEntity<?> getListMappingByKHAndCheckIn
            (@RequestParam(value = "checkIn", defaultValue = "") String checkIn,
             @RequestParam(value = "id", defaultValue = "") Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(checkIn == null || checkIn.isEmpty()){
            return ResponseEntity.ok(this.iDatPhongService.getListMappingByKHAndCheckIn(null, id));
        }
        return ResponseEntity.ok(this.iDatPhongService.getListMappingByKHAndCheckIn(LocalDate.parse(checkIn, formatter), id));
    }

    @PutMapping("/update-checkout")
    public ResponseEntity<?> updateCheckout(@RequestBody CheckOutRequest request) {
        LocalDate checkIn = request.getCheckIn();
        LocalDate checkOut = request.getCheckOut();
        Long id = request.getId();
        Long idPhong = request.getIdPhong();

        try {
            Boolean response = iDatPhongService.updateCheckout(checkIn, checkOut, id, idPhong);
            return ResponseEntity.ok(response);
        } catch (ServiceException1 e) {
            return ResponseUtil.unwrap(e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customer-use-room")
    public ResponseEntity<?> getCustomerUseRoom() {
        return ResponseUtil.wrap(this.iDatPhongService.getListCustomerUseRoom());
    }

    @GetMapping("/check-list-to-book")
    public ResponseEntity<?> checkListToBook
            (@RequestParam(name = "id") Long id,
             @RequestParam(value = "checkIn", defaultValue = "") String checkIn,
             @RequestParam(value = "checkOut", defaultValue = "") String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ResponseEntity.ok(this.iDatPhongService.checkListMappingByDate(id, LocalDate.parse(checkIn, formatter), LocalDate.parse(checkOut, formatter)));
    }

    @GetMapping("update-hoa-don-by-id")
    public ResponseEntity<?> updateHoaDonById(
            @RequestParam(name = "id") String id
    ) {
            Integer response = iDatPhongService.updateIdHoaDonByDatPhong(Long.valueOf(id));
            return ResponseUtil.wrap(response);
    }

    @GetMapping("get-check-out-to-day")
    public ResponseEntity<?> updateHoaDonById(
            @RequestParam(name = "id") Long id
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ResponseEntity.ok(this.iDatPhongService.getCheckOutToDay(LocalDate.now(), id));
    }

    @PutMapping("check-out-muon")
    public ResponseEntity<?> checkOutMuon(@RequestParam(value = "id") Long id, @RequestBody String ghiChu) {
        try {
            return ResponseUtil.wrap(this.iDatPhongService.checkOutMuon(ghiChu, id));
        } catch (ServiceException e) {
            ApiError apiError = new ApiError(String.valueOf(StatusError.Failed), e.getMessage());
            return ResponseUtil.wrap(apiError);
        }
    }

    @PutMapping("update-tien-phong-by-id")
    public ResponseEntity<?> updateTienPhong(@RequestParam(value = "id") Long id, @RequestBody BigDecimal tienPhong) {
        return ResponseUtil.wrap(this.iDatPhongService.updateTienDatPhongById(tienPhong, id));
    }
}
