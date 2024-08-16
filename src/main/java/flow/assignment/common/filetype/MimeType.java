package flow.assignment.common.filetype;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MimeTpye 정보를 담은 Enum 클래스
 */
@AllArgsConstructor
@Getter
public enum MimeType {
    AUDIO_AAC("audio/aac", "aac"),
    APPLICATION_X_ABIWORD("application/x-abiword", "abw"),
    APPLICATION_X_FREEARC("application/x-freearc", "arc"),
    IMAGE_AVIF("image/avif", "avif"),
    VIDEO_X_MSVIDEO("video/x-msvideo", "avi"),
    APPLICATION_VND_AMAZON_EBOOK("application/vnd.amazon.ebook", "azw"),
    APPLICATION_OCTET_STREAM("application/octet-stream", "bin"),
    IMAGE_BMP("image/bmp", "bmp"),
    APPLICATION_X_BZIP("application/x-bzip", "bz"),
    APPLICATION_X_BZIP2("application/x-bzip2", "bz2"),
    APPLICATION_X_CDF("application/x-cdf", "cda"),
    APPLICATION_X_CSH("application/x-csh", "csh"),
    TEXT_CSS("text/css", "css"),
    TEXT_CSV("text/csv", "csv"),
    APPLICATION_MSWORD("application/msword", "doc"),
    APPLICATION_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"),
    APPLICATION_EOT("application/vnd.ms-fontobject", "eot"),
    APPLICATION_EPUB("application/epub+zip", "epub"),
    APPLICATION_GZIP("application/gzip", "gz"),
    APPLICATION_GIF("application/gif", "gif"),
    TEXT_HTML("text/html", "html"),
    IMAGE_ICON("image/vnd.microsoft.icon", "ico"),
    TEXT_CALENDAR("text/calendar", "ics"),
    APPLICATION_JAR("application/java-archive", "jar"),
    TEXT_JAVASCRIPT("text/javascript", "js"),
    ;

    private final String mimeType;
    private final String extension;

    /**
     * 모든 MimeType 값을 Map에 담아서 변수에 저장
     */
    public static final Map<String, MimeType> MIME_TYPE_MAP =
            Arrays.stream(MimeType.values())
                    .collect(Collectors.toMap(MimeType::getExtension, Function.identity()));

    /**
     * 매개변수로 받은 MimeType이 Enum 클래스에 있는지 확인하는 메서드
     * @param mimeType
     * @return String
     */
    public static String isValidMimeType(String mimeType) {
        MimeType resultMimeType = MIME_TYPE_MAP.get(mimeType);
        if (resultMimeType != null) {
            return resultMimeType.getExtension();
        } else {
            return null;
        }
    }
}
