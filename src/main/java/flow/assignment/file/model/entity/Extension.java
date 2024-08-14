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
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extensionIdx;
    private String extension;
    private Boolean status;
    private String option;

    public static Extension buildCustonExtension(String extension, String option) {
        return Extension.builder()
                .extension(extension)
                .status(true)
                .option(option)
                .build();
    }
}
