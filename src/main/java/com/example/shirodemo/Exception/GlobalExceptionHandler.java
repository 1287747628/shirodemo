package com.example.shirodemo.Exception;

import com.example.shirodemo.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author jocken
 * @date 2021/3/17
 */
@RestControllerAdvice("com.example.shirodemo.controller")
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response missingParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return Response.buildFailed("missing_param_error");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return Response.buildFailed("missing_body_error");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response parameterExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                log.error("invalid param:" + fieldError.getDefaultMessage());
            }
        }
        return Response.buildFailed("invalid_param_error");
    }

    /**
     * 默认异常处理器
     *
     * @param e Exception
     * @return Response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Response.buildFailed("unknown_error");
    }

}
