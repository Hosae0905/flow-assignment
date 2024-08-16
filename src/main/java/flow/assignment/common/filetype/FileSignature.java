package flow.assignment.common.filetype;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 파일의 시그니처 정보를 담을 Enum 클래스
 */
@Getter
@AllArgsConstructor
public enum FileSignature {
    PNG_SIGNATURE("png", "8950"),
    JPG_SIGNATURE("jpg", "ffd8"),
    JPEG_SIGNATURE("jpeg", "ffd8"),
    BAT_SIGNATURE("bat", "5B33"),
    MP4_SIGNATURE("mp4", "0000"),
    IMG_SIGNATURE("img", "0001"),
    PPT_SIGNATURE("ppt", "006e"),
    DOC_SIGNATURE("doc", "0d44"),
    PDF_SIGNATURE("pdf", "2550"),
    GIF_SIGNATURE("gif", "4749"),
    MP3_SIGNATURE("mp3", "4944"),
    JAR_SIGNATURE("jar", "4a41"),
    EXE_SIGNATURE("exe", "4d5a"),
    ZIP_SIGNATURE("zip", "054B"),
    DOCX_SIGNATURE("docx", "504B"),
    TAR_SIGNATURE("tar", "7573"),
    ;

    private final String extension;
    private final String fileSignature;

    /**
     * 모든 파일의 시그니처 값을 Map에 담아서 변수에 저장
     */
    private static final Map<String, FileSignature> SIGNATURE_MAP =
            Arrays.stream(FileSignature.values())
                    .collect(Collectors.toMap(FileSignature::getExtension, Function.identity()));

    /**
     * 매개변수로 받은 확장자에 대한 시그니처 정보가 일치하는지 확인하는 메서드
     * @param extension
     * @param signature
     * @return Boolean
     */
    public static Boolean isEqualsSignature(String extension, String signature) {
        String fileSignature = SIGNATURE_MAP.get(extension).getFileSignature();
        if (fileSignature.equals(signature)) {
            return true;
        } else {
            return false;
        }
    }
}
