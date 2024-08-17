package flow.assignment.common.error.exception;

import flow.assignment.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RuntimeException 클래스를 상속받아서 정의한 인터셉터 예외
 */
@Getter
@AllArgsConstructor
public class InterceptorException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}
