package userstory3.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String url;

    // New fields for user feedback
    private double averageRating;
    private int totalRatings;

    @OneToMany(mappedBy = "template", cascade = {CascadeType.ALL, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;

    public Template() {
        // Default constructor needed by JPA
    }

    public Template(Long id, String name, String image, String url) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.url = url;
        this.averageRating = 0.0; // Default average rating
        this.totalRatings = 0;   // Default total ratings
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

    // Getters and setters...

    // Methods to manage bidirectional relationship...
}
