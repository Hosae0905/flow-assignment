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

    public static UploadFile buildUploadFile(String fileName, String fileExtension) {
        return UploadFile.builder()
                .fileName(fileName)
                .extension(fileExtension)
                .build();
    }
}
