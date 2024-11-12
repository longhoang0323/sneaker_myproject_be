package be.bds.bdsbes.resource;

import be.bds.bdsbes.service.iService.IHoaDonService;
import be.bds.bdsbes.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/myproject/sneaker/pay-ment")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    IHoaDonService iHoaDonService;

    //cách 2
    @GetMapping("/vn-pay")
    public ResponseEntity<?> pay(HttpServletRequest request,
                                 @RequestParam(value = "maHD") String maHD) {
        return ResponseEntity.ok(paymentService.createVnPayPayment(request, maHD));
    }

    @GetMapping("/vn-pay-callback")
    public String payCallbackHandler(HttpServletRequest request,
                                     @RequestParam(value = "id") Long id) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            System.out.println("Thanh toán thành công");
            this.iHoaDonService.updateTrangThaiThanhToan(id);
            return new PaymentDTO.VNPayResponse("00", "Success", "").toString();
        } else {
            return "Faled";
        }
    }

}