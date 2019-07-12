package ctrl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;

import bean.Book;
import bean.Review;
import dao.BookDAO;
import dao.ReviewDAO;

public class BookList extends Controller {

	private String searchTitle;
	private List<Book> books;
	private List<String> allCategories;
	
	@PostConstruct
	public void init() {
		try {
			books = BookDAO.getAllBooks();
			allCategories = BookDAO.getCategories();
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}
	
	public BookList() {
		books = new ArrayList<>();
	}

	public List<Book> getBooks() {
		return books;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public void searchBooks() {
		try {
			if (searchTitle != null && !searchTitle.trim().isEmpty()) {
				books = BookDAO.getBookByTitle(searchTitle);
			} else {
				books = BookDAO.getAllBooks();
			}
		} catch (Exception e) {
			addErrorMessage(e);
		} finally {
			// reset the search info
			searchTitle = null;
			PrimeFaces.current().scrollTo("books");
		}
	}

	public List<String> getCategories() {
		Set<String> categories = new LinkedHashSet<>();
		for (Book book : books) {
			categories.add(book.getCategory());
		}
		return new ArrayList<>(categories);
	}

	public List<String> getAllCategories() {
		return allCategories;
	}

	public List<Book> getBooksByCategory(String category) {
		List<Book> result = new ArrayList<>();
		for (Book book : books) {
			if (book.getCategory().equals(category)) {
				result.add(book);
			}
		}
		return result;
	}

	public String addReview(Review review) {
		try {
			ReviewDAO.addReview(review);
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return "bookpage";
	}

}
