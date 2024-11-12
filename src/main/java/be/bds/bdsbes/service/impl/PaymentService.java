package be.bds.bdsbes.service.impl;

import be.bds.bdsbes.config.VNPAYConfig;
import be.bds.bdsbes.config.VNPayUtil;
import be.bds.bdsbes.resource.PaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {

    public static String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    public static String tmnCode = "D6F170M5";

    public static String secretKey = "ZNUOMDUQVCLUQQT7HU4HN6LH53623ZZ5";

    private String getSignature(Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder();
        params.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("vnp_SecureHash") && entry.getValue() != null)
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        String data = sb.toString();
        data = data.substring(0, data.length() - 1); // Remove last "&"

        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        mac.init(secretKeySpec);
        byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sbHmac = new StringBuilder();
        for (byte b : hmacData) {
            sbHmac.append(String.format("%02x", b));
        }
        return sbHmac.toString();
    }

    //cách 2
    private final VNPAYConfig vnPayConfig;
    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, String maHD) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(maHD);
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }
}
