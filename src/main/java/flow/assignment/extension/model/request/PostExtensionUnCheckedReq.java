package flow.assignment.extension.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 고정 확장자 차단 해제 요청 DTO
 */
@Getter
@Schema(description = "Extension UnCheck Request")
public class PostExtensionUnCheckedReq {
    /**
     * 고정 확장자를 저장할 변수
     */
    @Schema(description = "차단을 해제할 고정 확장자", example = "bat")
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    @Schema(description = "고정 확장자 옵션", example = "default")
    private String option;
}
