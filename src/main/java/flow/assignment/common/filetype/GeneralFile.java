package flow.assignment.common.filetype;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 일반 텍스트 파일에 대한 Enum 클래스
 */
@Getter
@AllArgsConstructor
public enum GeneralFile {

    FILE_JS("js"),
    FILE_CSS("css"),
    FILE_TXT("txt"),
    FILE_MD("md"),
    FILE_HTML("html"),
    FILE_JSON("json"),
    FILE_XML("xml"),
    FILE_CSV("csv"),
    FILE_LOG("log"),
    FILE_INI("ini"),
    ;


    private final String extension;

    /**
     * 모든 일반 텍스트 파일의 확장자를 Map에 담아서 변수에 저장
     */
    private static final Map<String, GeneralFile> GENERAL_FILE_MAP =
            Arrays.stream(GeneralFile.values())
                    .collect(Collectors.toMap(GeneralFile::getExtension, Function.identity()));

    /**
     * 매개변수로 받은 확장자가 일반 텍스트 파일에 해당하는지 확인하는 메서드
     * @param extension
     * @return Boolean
     */
    public static Boolean isEqualsGeneralFile(String extension) {
        GeneralFile generalFile = GENERAL_FILE_MAP.get(extension);
        if (generalFile != null) {
            return true;
        } else {
            return null;
        }
    }

}
