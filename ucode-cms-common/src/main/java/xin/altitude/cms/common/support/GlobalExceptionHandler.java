package xin.altitude.cms.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xin.altitude.cms.common.entity.RestResult;
import xin.altitude.cms.common.util.JacksonUtils;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        String errorMessage = "A NullPointerException occurred: " + ex.getMessage();
        log.error(errorMessage);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String errorMessage = "An IllegalArgumentException occurred: " + ex.getMessage();
        log.error(errorMessage);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 处理其他异常...
}
