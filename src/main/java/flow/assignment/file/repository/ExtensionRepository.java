package flow.assignment.file.repository;

import flow.assignment.file.model.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Extension findByExtension(String extension);
}
