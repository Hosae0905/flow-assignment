package flow.assignment.file.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;
    private String fileName;
    private String extension;
    private String folderPath;

    public static UploadFile buildUploadFile(String fileName, String fileExtension, String folderPath) {
        return UploadFile.builder()
                .fileName(fileName)
                .extension(fileExtension)
                .folderPath(folderPath)
                .build();
    }
}
