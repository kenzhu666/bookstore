package ctrl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import bean.Address;
import bean.Book;
import bean.POItem;
import bean.User;
import dao.AddressDAO;
import dao.OrderDAO;

public class Payment extends Controller {

	private Map<Book, Integer> cart;

	private boolean same;
	private User user;

	private String shipName;
	private String shipTEL;
	private Address shipAddress;

	private String billName;
	private String billTEL;
	private Address billAddress;

	private String creditCard;

	private int orderID;

	@PostConstruct
	public void init() {
		try {
			shipName = user.getFullName();
			shipTEL = user.getPhone();
			shipAddress = AddressDAO.getAddress(user.getAddress());

			billName = user.getFullName();
			billTEL = user.getPhone();
			billAddress = new Address(shipAddress);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public Map<Book, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Book, Integer> cart) {
		this.cart = cart;
	}

	public boolean isSame() {
		return same;
	}

	public void setSame(boolean same) {
		this.same = same;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = capitalize(billName);
	}

	public String getBillTEL() {
		return billTEL;
	}

	public void setBillTEL(String billTEL) {
		this.billTEL = capitalize(billTEL);
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = capitalize(shipName);
	}

	public String getShipTEL() {
		return shipTEL;
	}

	public void setShipTEL(String shipTEL) {
		this.shipTEL = shipTEL;
	}

	public Address getShipAddress() throws Exception {
		return this.shipAddress;
	}

	public void setShipAddress(Address shipAddress) {
		this.shipAddress = shipAddress;
	}

	public Address getBillAddress() throws Exception {
		return this.billAddress;
	}

	public void setBillAddress(Address billAddress) {
		this.billAddress = billAddress;
	}

	public int getOrderID() {
		return orderID;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public boolean validate() {
		return orderID % 3 != 0;
	}

	public String autoScroll() {
		PrimeFaces.current().scrollTo("everything");
		return null;
	}

	public String checkout() {
		if (cart.isEmpty()) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			addError("Empty Shopping Cart", "Please add some book and retry");
			return "cart?faces-redirect=true";
		}
		if (user.getId() == 0) {
			addError("Login Expired", "Please refresh the page and retry");
		}
		try {
			int shipAddressID = AddressDAO.getAddressID(shipAddress);
			int billAddressID = AddressDAO.getAddressID(billAddress);
			orderID = OrderDAO.addPO(user.getId(), shipName, shipTEL, shipAddressID, billName, billTEL, billAddressID);
			for (Book book : cart.keySet()) {
				POItem poItem = new POItem(orderID, book.getBid(), cart.get(book));
				OrderDAO.addPOItem(poItem);
			}

			if (validate()) {
				OrderDAO.changeStatus(orderID, "PROCESSED");
				getRequest().setAttribute("transaction", true);
				cart.clear();
			} else {
				OrderDAO.changeStatus(orderID, "DENIED");
				getRequest().setAttribute("transaction", false);
			}
			getRequest().setAttribute("orderID", orderID);
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return "result";
	}

}
