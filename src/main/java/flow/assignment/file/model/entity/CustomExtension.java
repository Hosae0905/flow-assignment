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
public class CustomExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomExtensionIdx;
    private String extension;

    public static CustomExtension buildCustomExtension(String extension) {
        return CustomExtension.builder()
                .extension(extension)
                .build();
    }
}
