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
    /**
     * 고정 확장자 파일 시그니처
     */
    BAT("bat", "5b33"),
    COM("com", "4d5a"),
    CPL_1("cpl", "4d5a"),
    CPL_2("cpl", "cddc"),
    EXE("exe", "4d5a"),
    SCR("scr", "4d5a"),

    /**
     * 커스텀 확장자 파일 시그니처(A ~ Z까지 순서대로 나열)
     */
    ABC("abc", "6474"),
    ACB("acb", "0002"),
    ACG("acg", "c3ab"),
    ACM("acm", "4d5a"),
    ACS("acs", "c3ab"),
    ADF("adf", "4144"),
    ADP("adp", "6966"),
    ADX("adx", "3000"),
    AIN("ain", "2112"),
    AVG("avg", "4156"),
    AVI("avi", "4156"),
    AX("ax", "4d5a"),

    BIN("bin", "ff53"),
    BZ("bz", "425a"),
    BZ2("bz2", "425a"),
    BMP("bmp", "424d"),
    BTL("btl", "4254"),
    BAK("bak", "5b33"),

    CAB("cab", "4953"),
    CFG("cfg", "190d"),
    CLS("cls", "cafe"),
    CLASS("class", "cafe"),
    COD("cod", "4a4d"),
    CONF("conf", "2320"),
    CONFIG("config", "3c3f"),
    CSH("csh", "2321"),
    CTF("ctf", "4156"),
    CUR("cur", "0000"),

    DOC("doc", "0d44"),
    DOCX("docx", "504B"),
    DIB("dib", "424d"),
    DICT("dict", "3c4f"),
    DLL("dll", "4d5a"),
    DMS("dms", "4600"),
    DRV("drv", "4d5a"),
    DS("ds", "4d5a"),
    DS4("ds4", "4d47"),
    DTD("dtd", "3c21"),
    DMG("dmg", "7801"),

    ECF("ecf", "5b47"),
    EPS("eps", "2521"),
    ETL("etl", "000c"),
    EXP("exp", "4c01"),
    EVT("evt", "0300"),
    EXR("exr", "762f"),

    FM3("fm3", "0000"),
    FMT("fmt", "2000"),
    FLT("flt", "4d5a"),
    FLV("flv", "464c"),


    GIF("gif", "4749"),
    GID("gid", "3f5f"),
    GZ("gz", "1f8b"),

    HAP("hap", "9133"),
    HLP("hlp", "3f5f"),
    HTM("htm", "3c68"),
    HQX("hqx", "2854"),

    ICO("ico", "0000"),
    INI("ini", "5b2e"),
    ISO("iso", "0000"),
    IX("ix", "ff44"),
    IMG("img", "0001"),

    JAR("jar", "4a41"),
    JPG("jpg", "ffd8"),
    JPEG("jpeg", "ffd8"),

    LIB("lib", "213c"),
    LNK("lnk", "4c00"),
    LOG("log", "fffe"),
    LZMA("lzma", "5d00"),

    MANIFEST("manifest", "3c3f"),
    MDB("mdb", "0001"),
    MID("mid", "4d54"),
    MOV("mov", "6d64"),
    MP3("mp3", "4944"),
    MP4("mp4", "0000"),
    MPG("mpg", "0000"),
    MPEG("mpeg", "0000"),
    MSC("msc", "3c3f"),
    MUI("mui", "4d5a"),

    NLS("nls", "0d00"),
    NSF("nsf", "1a00"),

    OBJ("obj", "4c01"),
    OCX("ocx", "4d5a"),
    OLB("olb", "4d5a"),

    PDF("pdf", "2550"),
    PNG("png", "8950"),
    PST("pst", "2142"),
    PUB("pub", "d0cf"),
    PPT("ppt", "006e"),

    RA("ra", "2e72"),
    RAM("ram", "2e72"),
    RAR("rar", "5261"),
    RPM("rpm", "edab"),
    RTF("rtf", "7b5c"),

    SIT("sit", "5374"),
    SYS("sys", "4d5a"),
    SH("sh", "2321"),

    TAR("tar", "7573"),
    TGZ("tgz", "1f8b"),
    TIF("tif", "4d4d"),
    TIFF("tiff", "4949"),
    TLB("tlb", "4368"),

    UFA("ufa", "5546"),
    URL("url", "5b49"),

    VBS("vbs", "272a"),
    VBP("vbp", "5479"),
    VCF("vcf", "4245"),
    VDM("vdm", "4d5a"),

    W32("w32", "4d5a"),
    WAV("wav", "5249"),
    WIZ("wiz", "3000"),
    WAR("war", "504b"),
    WEBP("webp", "5249"),

    X32("x32", "4d5a"),
    XDR("xdr", "3c3f"),
    XHTML("xhtml", "3c3f"),
    XLA("xla", "d0cd"),
    XLS("xls", "0902"),
    XLT("xlt", "d0cf"),
    XML("xml", "fffe"),

    ZIP("zip", "054B"),
    ZOO("zoo", "5a4f"),

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
