package xin.altitude.cms.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xin.altitude.cms.common.entity.RestResult;

import java.util.ArrayList;
import java.util.List;

//@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(NullPointerException ex, WebRequest request) {
        String errorMessage = "A NullPointerException occurred: " + ex.getMessage();
        log.warn("全局捕获异常：{}", errorMessage);
        printExceptionPath(ex);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(IllegalArgumentException ex, WebRequest request) {
        String errorMessage = "An IllegalArgumentException occurred: " + ex.getMessage();
        log.warn("全局捕获异常：{}", errorMessage);
        printExceptionPath(ex);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * POST+Body 请求参数序列化异常
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException ex, WebRequest request) {
        String errorMessage = "请求参数序列化异常: " + ex.getMessage();
        log.warn("全局捕获异常：{}", errorMessage);
        printExceptionPath(ex);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * POST+BODY参数校验不通过
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest request) {
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (ObjectError er : errorList) {
            i++;
            sb.append(er.getDefaultMessage());
            if (i < errorList.size()) sb.append("|");
        }
        String errorMessage = "参数校验不通过: " + sb;
        log.warn("全局捕获异常：{}", errorMessage);
        printExceptionPath(ex);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * 参数绑定异常
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(BindException ex, WebRequest request) {
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (ObjectError er : errorList) {
            i++;
            sb.append(er.getDefaultMessage());
            if (i < errorList.size()) sb.append("|");
        }
        String errorMessage = "参数绑定异常: " + sb;
        log.warn("全局捕获异常：{}", errorMessage);
        printExceptionPath(ex);
        RestResult restResult = RestResult.error(errorMessage);
        return handleExceptionInternal(ex, restResult, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private void printExceptionPath(Exception e){
        StackTraceElement[] elements = e.getStackTrace();
        List<String> ss = new ArrayList<>();
        for (StackTraceElement element : elements) {
            ss.add(String.format("%s#%s:%s",element.getClassName(),element.getMethodName(),element.getLineNumber()));
        }
        log.warn("异常发生路径：{}",String.join(" -> ",ss));
    }
}
