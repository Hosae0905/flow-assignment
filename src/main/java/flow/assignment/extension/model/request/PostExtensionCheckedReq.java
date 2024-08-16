package flow.assignment.extension.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 고정 확장자 차단 요청 DTO
 */
@Getter
@Schema(description = "Extension Check Request")
public class PostExtensionCheckedReq {
    /**
     * 고정 확장자를 저장할 변수
     */
    @Schema(description = "차단할 고정 확장자", example = "bat")
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    @Schema(description = "고정 확장자 옵션", example = "default")
    private String option;
}
