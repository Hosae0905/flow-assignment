package flow.assignment.extension.model.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 커스텀 확장자 조회 응답 DTO
 */
@Getter
@Builder
public class GetCustomExtensionListRes {

    /**
     * 커스텀 확장자를 저장할 변수
     */
    private String extension;

    public static GetCustomExtensionListRes buildCustomExtensionList(String extension) {
        return GetCustomExtensionListRes.builder()
                .extension(extension)
                .build();
    }
}
