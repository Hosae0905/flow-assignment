package flow.assignment.extension.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 커스텀 확장자 추가 요청 DTO
 */
@Getter
@Schema(description = "Add Custom Extension Request")
public class PostAddExtensionReq {

    /**
     * 커스텀 확장자를 저장할 변수
     */
    @Schema(description = "추가할 커스텀 확장자", example = "aaa")
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    @Schema(description = "커스텀 확장자 옵션", example = "custom")
    private String option;
}
