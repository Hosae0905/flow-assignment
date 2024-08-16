package flow.assignment.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 공통적인 성공 응답에 관한 BaseResponse 클래스
 * @param <T>
 */
@AllArgsConstructor
@Getter
@Setter
public class BaseResponse<T> {
    private String code;
    private Boolean isSuccess;
    private String message;
    private T result;

    /**
     * 성공 응답 객체를 생성하는 메서드
     * @param code
     * @param isSuccess
     * @param message
     * @param result
     * @return <T>BaseResponse<T>
     */
    public static <T>BaseResponse<T> successResponse(String code, Boolean isSuccess, String message, T result) {
        return new BaseResponse<>(code, isSuccess, message, result);
    }
}
