package flow.assignment.common.error.exception;

import flow.assignment.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExtensionException extends RuntimeException {
    private final ErrorCode errorCode;
    private String message;
}
