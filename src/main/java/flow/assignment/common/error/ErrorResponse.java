package flow.assignment.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통적인 예외 응답에 관한 ErrorResponse 클래스
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;
}
