package br.com.diogenes.maxclubcard.entrypoint.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus code;
    private final String informationCode;
    private final String resource;

    public CustomException(String msg, HttpStatus code, String informationCode, String resource) {
        super(msg);
        this.code = code;
        this.informationCode = informationCode;
        this.resource = resource;
    }

    public CustomException(HttpStatus code, String informationCode, String resource) {
        this.code = code;
        this.informationCode = informationCode;
        this.resource = resource;
    }

    public HttpStatus getCode() {
        return code;
    }
}
