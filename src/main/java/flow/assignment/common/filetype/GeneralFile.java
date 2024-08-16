package flow.assignment.common.filetype;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private static final Map<String, GeneralFile> GENERAL_FILE_MAP =
            Arrays.stream(GeneralFile.values())
                    .collect(Collectors.toMap(GeneralFile::getExtension, Function.identity()));

    public static Boolean isEqualsGeneralFile(String extension) {
        GeneralFile generalFile = GENERAL_FILE_MAP.get(extension);
        if (generalFile != null) {
            return true;
        } else {
            return null;
        }
    }

}
