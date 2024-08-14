package flow.assignment.file.model.entity;

import jakarta.persistence.*;
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

    @Column(name = "extension")
    private String extension;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "extension_option")
    private String option;

    public static Extension buildCustonExtension(String extension, String option) {
        return Extension.builder()
                .extension(extension)
                .status(true)
                .option(option)
                .build();
    }
}
