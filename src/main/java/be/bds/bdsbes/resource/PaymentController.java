package be.bds.bdsbes.resource;

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

    @GetMapping("/pay")
    public String pay(@RequestParam("amount") String amount, HttpServletResponse response) throws Exception {
        long amountReturn = Integer.parseInt(amount) * 100L;
        return paymentService.createPaymentUrl(amountReturn);
    }

//    @GetMapping("/pay")
//    public String createPayment(@RequestParam("amount") int amount) {
//        String amountReturn = String.valueOf((amount * 100L));
//        return paymentService.createPaymentRequest(amountReturn);
//    }

    @GetMapping("/return")
    public String returnUrl(@RequestParam Map<String, String> params) {
        // Kiểm tra mã kết quả trả về từ VNPAY
        String vnpResponseCode = params.get("vnp_ResponseCode");
        String orderId = params.get("vnp_OrderId");

        // Kiểm tra xem mã kết quả có thành công không
        if ("00".equals(vnpResponseCode)) {
            // Lấy thông tin chi tiết từ cơ sở dữ liệu
            return "Thanh toán thành công! Mã đơn hàng: LONGDEPTRAI" + orderId;
        } else {
            // Cập nhật trạng thái đơn hàng thành công
            return "Thanh toán thất bại! Mã kết quả: " + vnpResponseCode;
        }
    }

    //cách 2
    @GetMapping("/vn-pay")
    public ResponseEntity<?> pay(HttpServletRequest request) {
        return ResponseEntity.ok(paymentService.createVnPayPayment(request));
    }

    @GetMapping("/vn-pay-callback")
    public String payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return new PaymentDTO.VNPayResponse("00", "Success", "").toString();
        } else {
            return "Faled";
        }
    }

}