package flow.assignment.extension.model.request;

import lombok.Getter;

/**
 * 고정 확장자 차단 요청 DTO
 */
@Getter
public class PostExtensionCheckedReq {
    /**
     * 고정 확장자를 저장할 변수
     */
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    private String option;
}
