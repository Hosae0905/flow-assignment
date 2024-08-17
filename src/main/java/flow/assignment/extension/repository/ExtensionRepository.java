package flow.assignment.extension.repository;

import flow.assignment.extension.model.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 확장자 관련 Repository
 * 확장자 조회, 확장자 목록 조회
 */
@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    /**
     * 확장자를 DB에서 조회하는 메서드
     * @param extension
     * @return Optional<Extension>
     */
    Optional<Extension> findByExtension(String extension);

    /**
     * 확장자 옵션으로 확장자 목록을 조회하는 메서드
     * @param option
     * @return List<Extension>
     */
    List<Extension> findAllByOption(String option);
}
