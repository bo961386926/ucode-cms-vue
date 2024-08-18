package xin.altitude.cms.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.util.InvokeMethodUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Component
public class HoldExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(HoldExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handle(Exception e) {
        if (e instanceof HttpMessageNotReadableException) {
            //参数序列化异常
            log.warn("序列化转换错误：{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("参数不匹配，序列化转换错误");
        } else if (e instanceof MethodArgumentNotValidException) {
            //POST+BODY参数校验不通过
            log.warn("参数校验不过：{}", e.getMessage());
            List<ObjectError> errorList = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (ObjectError er : errorList) {
                i++;
                sb.append(er.getDefaultMessage());
                if (i < errorList.size()) sb.append("|");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
        } else if (e instanceof ConstraintViolationException) {
            //GET参数校验不通过
            log.warn("参数校验不过：{}", e.getMessage());
            Set<ConstraintViolation<?>> errorSet = ((ConstraintViolationException) e).getConstraintViolations();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (ConstraintViolation<?> er : errorSet) {
                i++;
                sb.append(er.getMessage());
                if (i < errorSet.size()) sb.append("|");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
        } else if (e instanceof BindException) {
            log.error("参数校验失败：{}", e.getMessage());
            List<ObjectError> errorList = ((BindException) e).getBindingResult().getAllErrors();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (ObjectError er : errorList) {
                i++;
                sb.append(er.getDefaultMessage());
                if (i < errorList.size()) sb.append("|");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
        } else if (e instanceof IllegalArgumentException) {
            //自定义断言
            log.warn("断言内容：{}", e.getMessage());
            InvokeMethodUtils.invokePath();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("自定义断言");
        } else {
            //其余未捕获异常
            log.error("未捕获异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("未捕获异常");
        }
    }
}
