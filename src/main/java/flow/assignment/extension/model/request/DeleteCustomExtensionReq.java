package flow.assignment.extension.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 커스텀 확장자 삭제 요청 DTO
 */
@Getter
@Schema(description = "Delete Custom Extension Request")
public class DeleteCustomExtensionReq {

    /**
     * 커스텀 확장자를 저장할 변수
     */
    @Schema(description = "삭제할 확장자", example = "커스텀 확장자 목록을 조회하여 하나를 선택해주세요")
    private String extension;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    @Schema(description = "삭제할 확장자의 옵션", example = "custom")
    private String option;
}
