package flow.assignment.file.repository;

import flow.assignment.file.model.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 파일 관련 Repository
 */
public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
