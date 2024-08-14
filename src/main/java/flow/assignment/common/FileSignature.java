package flow.assignment.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileSignature {
    PNG_SIGNATURE("png", "89504e47"),
    JPG_SIGNATURE("jpg", "ffd8ffe0"),
    JPEG_SIGNATURE("jpeg", "ffd8ffe0"),
    BAT_SIGNATURE("bat", "5B332f31"),
    MP4_SIGNATURE("mp4", "00000018"),
    IMG_SIGNATURE("img", "00010008"),
    PPT_SIGNATURE("ppt", "006e1ef0"),
    DOC_SIGNATURE("doc", "0d444d43"),
    PDF_SIGNATURE("pdf", "25504446"),
    GIF_SIGNATURE("gif", "47494638"),
    MP3_SIGNATURE("mp3", "494433"),
    JAR_SIGNATURE("jar", "4a415243"),
    EXE_SIGNATURE("exe", "4d5a"),
    ZIP_SIGNATURE("zip", "054B0304"),
    DOCX_SIGNATURE("docx", "504B0304"),
    TAR_SIGNATURE("tar", "75737461"),
    ;

    private String extension;
    private String fileSignature;

    public static Boolean isEqualsSignature(String extension, String signature) {
        for (FileSignature fileSignature : FileSignature.values()) {
            if (fileSignature.getExtension().equals(extension)) {
                if (fileSignature.getFileSignature().equals(signature)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return null;
    }
}
