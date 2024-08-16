package flow.assignment.extension.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCustomExtensionListRes {
    private String extension;

    public static GetCustomExtensionListRes buildCustomExtensionList(String extension) {
        return GetCustomExtensionListRes.builder()
                .extension(extension)
                .build();
    }
}
