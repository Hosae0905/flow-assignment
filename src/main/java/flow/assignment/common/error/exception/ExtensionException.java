package flow.assignment.common.error.exception;

import flow.assignment.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RuntimeException 클래스를 상속받아서 정의한 확장자 예외
 */
@Getter
@AllArgsConstructor
public class ExtensionException extends RuntimeException {
    private final ErrorCode errorCode;
    private String message;
}
