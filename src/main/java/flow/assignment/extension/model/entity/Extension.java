package flow.assignment.extension.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 확장자 데이터를 저장할 엔티티
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Extension {
    /**
     * 확장자 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extensionIdx;

    /**
     * 확장자 이름을 저장할 변수
     */
    @Column(name = "extension")
    private String extension;

    /**
     * 확장자 차단 여부를 확인할 변수
     */
    @Column(name = "status")
    private Boolean status;

    /**
     * 확장자의 옵션을 저장할 변수
     */
    @Column(name = "extension_option")
    private String option;

    /**
     * 커스텀 확장자 엔티티 객체를 생성하는 메서드
     * @param extension
     * @param option
     * @return Extension
     */
    public static Extension buildCustonExtension(String extension, String option) {
        return Extension.builder()
                .extension(extension)
                .status(true)
                .option(option)
                .build();
    }
}
