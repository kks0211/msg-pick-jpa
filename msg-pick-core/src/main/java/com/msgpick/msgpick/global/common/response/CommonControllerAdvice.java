package com.msgpick.msgpick.global.common.response;

import com.msgpick.msgpick.global.common.exception.BaseException;
import com.msgpick.msgpick.global.common.interceptor.CommonHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.MDC;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    private static final List<ErrorCode> SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST = Lists.newArrayList();

    /**
     * http status: 500 AND result: FAIL
     * 시스템 예외 상황. 집중 모니터링 대상
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> onException(HttpServletRequest request, Exception e) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
        log.error("eventId = {} ", eventId, e);

        HttpHeaders headers = setHeader(request);
        Object body = setBody(request, e);

        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * http status: 200 AND result: FAIL
     * 시스템은 이슈 없고, 비즈니스 로직 처리에서 에러가 발생함
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> onBaseException(HttpServletRequest request, BaseException e) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
        log.warn("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).getMessage());

        HttpHeaders headers = setHeader(request);
        Object body = setBody(request, e);

        final HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * 예상치 않은 Exception 중에서 모니터링 skip 이 가능한 Exception 을 처리할 때
     * ex) ClientAbortException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ClientAbortException.class})
    public ResponseEntity<?> skipException(HttpServletRequest request, Exception e) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
        log.warn("[skipException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).getMessage());

        HttpHeaders headers = setHeader(request);
        Object body = setBody(request, e);

        final HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * http status: 400 AND result: FAIL
     * request parameter 에러
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
        log.warn("[BaseException] eventId = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e).getMessage());

        HttpHeaders headers = setHeader(request);
        Object body = setBody(request, e);
        final HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(body, headers, status);
    }

    private HttpHeaders setHeader(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (isApiRequest(request)) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        } else {
            headers.setContentType(MediaType.TEXT_HTML);
        }

        return headers;
    }

    private Object setBody(HttpServletRequest request, Exception e) {
        Class<? extends Exception> aClass = e.getClass();

        if (isApiRequest(request)) {
            if (aClass == Exception.class || aClass == ClientAbortException.class) {
                return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
            } else if (aClass == BaseException.class) {
                BaseException baseException = (BaseException) e;
                return CommonResponse.fail(e.getMessage(), baseException.getErrorCode().name());
            } else if (aClass == AccessDeniedException.class) {
                return CommonResponse.fail(ErrorCode.ACCESS_DENIED);
            } else if (aClass == MethodArgumentNotValidException.class) {
                BindingResult bindingResult =  ((MethodArgumentNotValidException) e).getBindingResult();
                FieldError fe = bindingResult.getFieldError();

                if (fe != null) {
                    String message = fe.getDefaultMessage();
                    return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name());
                } else {
                    return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER.getErrorMsg(), ErrorCode.COMMON_INVALID_PARAMETER.name());
                }
            }
        } else {
            if (aClass == AccessDeniedException.class) {
                return "error/401";
            }

            return "error/500";
        }

        return null;
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String contentType = request.getHeader("Content-Type");

        if (contentType == null) {
            return false;
        }

        return contentType.contains("application/json");
    }

}
