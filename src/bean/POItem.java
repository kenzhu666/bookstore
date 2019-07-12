package bean;

public class POItem {
	private int id;
	private String bid;
	private String title;
	private int quantity;

	public POItem() {

	}

	public POItem(int id, String bid, int quantity) {
		super();
		this.id = id;
		this.bid = bid;
		this.quantity = quantity;
	}

	public POItem(String bid, String title, int quantity) {
		super();
		this.bid = bid;
		this.title = title;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "POItem [id=" + id + ", bid=" + bid + ", title=" + title + ", quantity=" + quantity + "]";
	}

}
