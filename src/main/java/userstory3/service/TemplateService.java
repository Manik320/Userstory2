package userstory3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userstory3.entity.Feedback;
import userstory3.entity.Template;


import java.util.Arrays;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private FeedbackService feedbackService;

    
    // Sample template data with names, images, and URLs
    private static final List<Template> templates = Arrays.asList(
            new Template(1l,"Home", "https:media.gettyimages.com/id/1402031331/vector/isometric-hr-manager-reviewing-web-page-job-postings-with-cv-recruitment-and-job-search.jpg?s=2048x2048&w=gi&k=20&c=pUQ8GjaIut93uKndKyrOWIP2cRKX3qnmyAUW8t8J_GU=", "/"),
            new Template(2l,"Registration", "https://img.freepik.com/free-photo/registration-application-paper-form-concept_53876-167141.jpg?w=740&t=st=1703662244~exp=1703662844~hmac=021bcb85353e937f457a2507c5a633b7e52c47b34b43cc0215f6207228205354", "/register"),
            new Template(3l,"Login", "https://img.freepik.com/free-vector/login-concept-illustration_114360-739.jpg?w=740&t=st=1703662327~exp=1703662927~hmac=03cf51d6820f5a8f85b993f120d7d25f59b5c3f6377b9563075d6a513995be65", "/login")
    );

    public List<Template> getAllTemplates() {
        return templates;
    }

   


    public void saveFeedback(Feedback feedback) {
        feedbackService.saveFeedback(feedback);
    }

    public void saveFeedback(Long templateId, Feedback feedback) {
        Template template = getTemplateById(templateId);
        feedback.setTemplate(template);
        feedbackService.saveFeedback(feedback);

        // Update template's average rating and total ratings
        double newAverageRating = ((template.getAverageRating() * template.getTotalRatings()) + feedback.getRating()) / (template.getTotalRatings() + 1);
        template.setAverageRating(newAverageRating);
        template.setTotalRatings(template.getTotalRatings() + 1);

        // This part is placeholder for actual persistence (e.g., save to database)
        // For simplicity, we are not persisting templates to a database in this example
    }

    public Template getTemplateById(Long templateId) {
        return templates.stream()
                .filter(template -> template.getId().equals(templateId))
                .findFirst()
                .orElse(null);
    }


	
}
