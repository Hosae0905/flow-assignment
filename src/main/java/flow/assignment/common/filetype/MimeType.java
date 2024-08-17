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
    /**
     * Audio 형식의 MimeType
     */
    AUDIO_AAC("audio/aac", "aac"),
    AUDIO_MIDI("audio/midi", "midi"),
    AUDIO_MP3("audio/mpeg", "mp3"),
    AUDIO_OGG("audio/ogg", "ogg"),
    AUDIO_WAV("audio/x-wav", "wav"),
    AUDIO_WEBA("audio/webm", "weba"),

    /**
     * Video 형식의 MimeType
     */
    VIDEO_AVI("video/x-msvideo", "avi"),
    VIDEO_MP4("video/mp4", "mp4"),
    VIDEO_MPEG("video/mpeg", "mpeg"),
    VIDEO_OGV("video/ogg", "ogv"),
    VIDEO_WEBM("video/webm", "webm"),
    VIDEO_3GPP("video/3gpp", "3gp"),
    VIDEO_3GPP2("video/3gpp2", "3g2"),

    /**
     * Text 형식의 MimeType
     */
    TEXT_PLAIN("text/plain", "txt"),
    TEXT_HTML("text/html", "html"),
    TEXT_CSS("text/css", "css"),
    TEXT_CSV("text/csv", "csv"),
    TEXT_JAVASCRIPT("text/javascript", "js"),
    TEXT_CALENDAR("text/calendar", "ics"),

    /**
     * Image 형식의 MimeType
     */
    IMAGE_JPEG("image/jpeg", "jpeg"),
    IMAGE_PNG("image/png", "png"),
    IMAGE_GIF("image/gif", "gif"),
    IMAGE_BMP("image/bmp", "bmp"),
    IMAGE_ICON("image/vnd.microsoft.icon", "ico"),
    IMAGE_TIFF("image/tiff", "tiff"),
    IMAGE_SVG("image/svg+xml", "svg"),
    IMAGE_AVIF("image/avif", "avif"),

    /**
     * Application 형식의 MimeType
     */
    APPLICATION_JSON("application/json", "json"),
    APPLICATION_XML("application/xml", "xml"),
    APPLICATION_PDF("application/pdf", "pdf"),
    APPLICATION_ZIP("application/zip", "zip"),
    APPLICATION_SEVEN_ZIP("application/x-7z-compressed", "7z"),
    APPLICATION_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"),
    APPLICATION_DOC("application/msword", "doc"),
    APPLICATION_XLS("application/vnd.ms-excel", "xls"),
    APPLICATION_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"),
    APPLICATION_PPT("application/vnd.ms-powerpoint", "ppt"),
    APPLICATION_PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx"),
    APPLICATION_BZIP("application/x-bzip", "bz"),
    APPLICATION_BZIP2("application/x-bzip2", "bz2"),
    APPLICATION_CSH("application/x-csh", "csh"),
    APPLICATION_ARC("application/x-freearc", "arc"),
    APPLICATION_SH("application/x-sh", "sh"),
    APPLICATION_TAR("application/x-tar", "tar"),
    APPLICATION_CDA("application/x-cdf", "cda"),
    APPLICATION_JAR("application/java-archive", "jar"),
    APPLICATION_EBOOK("application/vnd.amazon.ebook", "azw"),
    APPLICATION_EOT("application/vnd.ms-fontobject", "eot"),
    APPLICATION_EPUB("application/epub+zip", "epub"),
    APPLICATION_GZIP("application/gzip", "gz"),
    APPLICATION_OCTET_STREAM("application/octet-stream", "bin"),
    APPLICATION_ABIWORD("application/x-abiword", "abw"),
    APPLICATION_FLASH("application/x-shockwave-flash", "swf");
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
