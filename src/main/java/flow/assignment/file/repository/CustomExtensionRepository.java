package flow.assignment.file.repository;

import flow.assignment.file.model.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    Optional<CustomExtension> findByExtension(String extension);
}
