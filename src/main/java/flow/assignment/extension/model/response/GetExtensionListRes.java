package flow.assignment.extension.model.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 고정 확장자 조회 응답 DTO
 */
@Getter
@Builder
public class GetExtensionListRes {

    /**
     * 고정 확장자를 저장할 변수
     */
    private String extension;

    /**
     * 고정 확장자의 차단 여부를 저장할 변수
     */
    private Boolean status;

    public static GetExtensionListRes buildExtensionList(String extension, Boolean status) {
        return GetExtensionListRes.builder()
                .extension(extension)
                .status(status)
                .build();
    }
}
