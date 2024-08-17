package flow.assignment.file.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 업로드한 파일의 정보를 저장할 엔티티
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UploadFile {
    /**
     * 업로드 파일의 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;

    /**
     * 업로드 파일의 이름
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 업로드 파일의 확장자
     */
    @Column(name = "extension")
    private String extension;

    /**
     * 업로드 경로
     */
    @Column(name = "folder_path")
    private String folderPath;

    /**
     * UploadFile 엔티티 객체를 생성하는 메서드
     * @param fileName
     * @param fileExtension
     * @param folderPath
     * @return UploadFile
     */
    public static UploadFile buildUploadFile(String fileName, String fileExtension, String folderPath) {
        return UploadFile.builder()
                .fileName(fileName)
                .extension(fileExtension)
                .folderPath(folderPath)
                .build();
    }
}
