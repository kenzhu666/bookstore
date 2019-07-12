package bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import rest.DateAdapter;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
	@XmlAttribute
	private String bid;
	private String title;
	private int price;
	private String category;
	private String author;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date release;
	private String description;
	@XmlTransient
	private String img;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Book() {

	}

	public Book(String bid, String title, int price, String category, String author, Date release, String description,
			String img) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
		this.author = author;
		this.release = release;
		this.description = description;
		this.img = img;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookBean [bid=" + bid + ", title=" + title + ", price=" + price + ", category=" + category + "]";
	}

}
