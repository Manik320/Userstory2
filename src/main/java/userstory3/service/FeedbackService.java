package userstory3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userstory3.entity.Feedback;
import userstory3.entity.Template;
import userstory3.repository.FeedbackRepository;
import userstory3.repository.TemplateRepository;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
       
    private final TemplateRepository templateRepository;
    
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, TemplateRepository templateRepository) {
        this.feedbackRepository = feedbackRepository;
        this.templateRepository = templateRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }


    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    

   
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + feedbackId));
    }

    public void saveFeedback(Feedback feedback, Long templateId) {
        // Check if the template with the given templateId exists
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found with id: " + templateId));

        // Set the template_id before saving
        feedback.setTemplateId(templateId);

        // Save the feedback
        feedbackRepository.save(feedback);
    }
}
