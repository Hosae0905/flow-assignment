package flow.assignment.extension.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetExtensionListRes {
    private String extension;
    private Boolean status;

    public static GetExtensionListRes buildExtensionList(String extension, Boolean status) {
        return GetExtensionListRes.builder()
                .extension(extension)
                .status(status)
                .build();
    }
}
