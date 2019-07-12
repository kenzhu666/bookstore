package ctrl;

import java.util.LinkedHashMap;
import java.util.Map;

import bean.Book;

public class Cart extends Controller {
	private int maximum = 10;
	private Map<Book, Integer> cart;

	public Cart() {
		cart = new LinkedHashMap<>();
	}

	public Map<Book, Integer> getCart() {
		return cart;
	}

	public String add(Book book, int quantity) {
		if (!cart.containsKey(book)) {
			cart.put(book, quantity);
		} else {
			int count = cart.get(book);
			if (count + quantity > maximum) {
				addError("buyForm:quantity", "Quantity Exceed",
						"The maximum quantity you can order is 10.Now you have already had " + count
								+ " in the shopping cart.");
				return null;
			}
			cart.put(book, cart.get(book) + quantity);
		}
		return "cart?faces-redirect=true";
	}

	public void increase(Book book) {
		int count = cart.get(book);
		if (count < maximum) {
			cart.put(book, count + 1);
		}

	}

	public void decrease(Book book) {
		int count = cart.get(book);
		if (count > 1) {
			cart.put(book, count - 1);
		}
	}

	public void remove(Book book) {
		cart.remove(book);
	}

	public int rowPrice(Book book) {
		return book.getPrice() * cart.get(book);
	}

	public double getTotalPrice() {
		double price = 0;
		for (Book book : cart.keySet()) {
			price += book.getPrice() * cart.get(book);
		}
		return price;
	}

	public double getShippingFee() {
		return 3;
	}

	public double getTax() {
		return (getTotalPrice() + getShippingFee()) * 0.13;
	}

	public double getTotalAfterTax() {
		return getTotalPrice() + getShippingFee() + getTax();
	}

	public String redirectToCart() {
		if (cart.isEmpty()) {
			return "cart?faces-redirect=true";
		}
		return null;
	}

}
