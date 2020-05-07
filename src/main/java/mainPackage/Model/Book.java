package mainPackage.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int Id;
	private String name;
	private String description;
	private String category;
	private String url;
	private String cover;
	

	public enum Category {
		Fiction, Nature, Economy, Adventure, Romance
	}

	public Book(int id, String name, String description, String category, String url, String cover) {
		super();
		this.Id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.url = url;
		this.cover = cover;
	}
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
