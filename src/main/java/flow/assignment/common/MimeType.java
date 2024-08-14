package flow.assignment.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    APPLICATION_JAR("application/java-archive", "jar")
    ;

    private String mimeType;
    private String extension;

    public static String isValidMimeType(String mimeType) {
        for (MimeType type : MimeType.values()) {
            if (type.getMimeType().equals(mimeType)) {
                return type.getExtension();
            }
        }
        return null;
    }
}
