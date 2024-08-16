package flow.assignment.common.error.exception;

import flow.assignment.common.error.ErrorCode;
import flow.assignment.common.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역으로 예외를 처리할 수 있는 Advice
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 확장자 관련 예외를 처리하는 메서드
     * @param e
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(ExtensionException.class)
    public ResponseEntity<ErrorResponse> handleExtensionException(ExtensionException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    /**
     * Exception 예외를 처리하는 메서드
     * @param e
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    /**
     * 인터셉터 관련 예외를 처리할 메서드
     * @param e
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(InterceptorException.class)
    public ResponseEntity<ErrorResponse> handleInterceptorException(InterceptorException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
