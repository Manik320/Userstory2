package userstory3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import userstory3.entity.Feedback;
import userstory3.entity.Template;
import userstory3.service.TemplateService;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/{templateId}")
    public String showTemplate(@PathVariable Long templateId, Model model) {
        Template template = templateService.getTemplateById(templateId);
        model.addAttribute("template", template);
        return "template_details";
    }

    @GetMapping("/feedback/add/{templateId}")
    public String showFeedbackForm(@PathVariable Long templateId, Model model) {
        Template template = templateService.getTemplateById(templateId);
        model.addAttribute("template", template);
        model.addAttribute("feedback", new Feedback());
        return "feedback_form";
    }

    @PostMapping("/feedback/add/{templateId}")
    public String saveFeedback(@PathVariable Long templateId, @ModelAttribute("feedback") Feedback feedback) {
        templateService.saveFeedback(templateId, feedback);
        return "redirect:/templates/" + templateId;
    }
       
}

