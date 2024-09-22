package be.bds.bdsbes.utils.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException1 extends RuntimeException {
    private final HttpStatus status;

    public ServiceException1(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
