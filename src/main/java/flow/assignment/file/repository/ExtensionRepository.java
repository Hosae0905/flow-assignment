package flow.assignment.file.repository;

import flow.assignment.file.model.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByExtension(String extension);
}
