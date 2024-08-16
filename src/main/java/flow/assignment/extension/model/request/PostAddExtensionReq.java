package flow.assignment.extension.model.request;

import lombok.Getter;

/**
 * 커스텀 확장자 추가 요청 DTO
 */
@Getter
public class PostAddExtensionReq {

    /**
     * 커스텀 확장자를 저장할 변수
     */
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    private String option;
}
