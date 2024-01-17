package userstory3.entity;

import javax.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String comments;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "template_id")
    private Template template;


    // Constructors, getters, and setters...

    public Feedback(Long id, int rating, String comments, Template template) {
		super();
		this.id = id;
		this.rating = rating;
		this.comments = comments;
		this.template = template;
	}

//	public Feedback(int rating, String comments, Template template) {
//        this.rating = rating;
//        this.comments = comments;
//        this.template = template;
//    }

    public Feedback() {
		// TODO Auto-generated constructor stub
	}

	public void setUser(User loggedInUser) {
        // Implement if you have a User field
    }

    public void setTemplateId(Long templateId) {
        // Implement if you want to set templateId directly
        // Note: This method assumes that you have a Template entity
        this.template = new Template();
        this.template.setId(templateId);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	

    // Other methods...
}

    
   


