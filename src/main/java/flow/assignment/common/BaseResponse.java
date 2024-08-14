package flow.assignment.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BaseResponse<T> {
    private String code;
    private Boolean isSuccess;
    private String message;
    private T result;

    public static <T>BaseResponse<T> successResponse(String code, Boolean isSuccess, String message, T result) {
        return new BaseResponse<>(code, isSuccess, message, result);
    }
}
