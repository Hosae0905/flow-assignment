package flow.assignment.common.error.exception;

import flow.assignment.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterceptorException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}
