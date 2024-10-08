package be.bds.bdsbes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendPasswordResetEmail(String to, String newPassword) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom(senderEmail);
        helper.setSubject("Password Reset");

        String emailContent = "<html><body>"
                + "<h2>Password Reset</h2>"
                + "<img src='https://designmodo.com/wp-content/uploads/2021/01/reset-password-preview.jpg' alt='Your Logo' />"
                + "<p>Your new password is: <strong>" + newPassword + "</strong></p>"
                + "<p>Click <a href=\"https://www.yourwebsite.com/login\">here</a> to log in.</p>"
                + "</body></html>";
        helper.setText(emailContent, true);


        javaMailSender.send(message);
    }

    public void sendEmailWithPoints(String to, String customerName, int points) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setFrom(senderEmail);
        helper.setSubject("Thông báo điểm tích lũy của bạn");

        String body = String.format(
                "<h1>Khách sạn Mebunbo kính chào %s,</h1>"
                        + "<p>Bạn hiện có <strong>%d điểm</strong> tích lũy.</p>"
                        + "<p>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi!</p>",
                customerName, points
        );

        helper.setText(body, true);
        javaMailSender.send(message);
    }

}
