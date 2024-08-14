package flow.assignment.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_EXTENSION(HttpStatus.NOT_FOUND, "EXTENSION_ERROR_001", "확장자를 찾을 수 없습니다."),
    NOT_MATCH_OPTION(HttpStatus.BAD_REQUEST, "EXTENSION_ERROR_002", "확장자 옵션이 잘못되었습니다."),
    CONFLICT_EXTENSION(HttpStatus.CONFLICT, "EXTENSION_ERROR_003", "중복된 확장자는 추가할 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR_001", "알 수 없는 오류가 발생하였습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
