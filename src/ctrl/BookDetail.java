package ctrl;

import java.util.List;

import org.primefaces.PrimeFaces;

import bean.Book;
import bean.Review;
import bean.User;
import dao.ReviewDAO;
import dao.UserDAO;
import util.StarUtil;

public class BookDetail extends Controller {

	private Book book;

	public BookDetail() {
		book = new Book();
	}

	public String validCall() {
		if (getBid() == null) {
			return "index?faces-redirect=true";
		}
		PrimeFaces.current().scrollTo("all");
		return null;
	}

	public String loadBook(Book book) {
		this.book = book;
		return "bookInfo?faces-redirect=true";
	}

	public String getBid() {
		return book.getBid();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Review> getReviews() throws Exception {
		return ReviewDAO.getReviews(book.getBid());
	}

	public String getAvgRate() throws Exception {
		return ReviewDAO.getAvgRate(book.getBid());
	}

	public String getRateStar() throws Exception {
		double rate = Double.parseDouble(getAvgRate());
		return StarUtil.getRateStar(rate);
	}

	public User getUserByID(int uid) {
		try {
			return UserDAO.getUserByID(uid);
		} catch (Exception e) {
			addErrorMessage(e);
			return null;
		}
	}

	public void addReview(Review review) {
		if (review.getUid() == 0) {
			addError("Login Expired", "Please refresh the page and retry");
		} else {
			try {
				ReviewDAO.addReview(review);
				// reset the review bean
				review.setRate(5);
				review.setDescription("");
				addHint("Review added!");
				PrimeFaces.current().scrollTo("footer");
			} catch (Exception e) {
				addHint(e.getMessage());
			}
		}
	}

}
