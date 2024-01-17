package userstory3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userstory3.entity.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    // You can add custom query methods here if needed
}
