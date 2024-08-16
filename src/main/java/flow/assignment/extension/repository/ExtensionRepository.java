package flow.assignment.extension.repository;

import flow.assignment.extension.model.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByExtension(String extension);

    List<Extension> findAllByOption(String option);
}
