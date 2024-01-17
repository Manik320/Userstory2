package userstory3.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import userstory3.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
   
}
