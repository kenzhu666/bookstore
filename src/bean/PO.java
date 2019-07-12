package bean;

public class PO {
	private int id;
	private int uid;
	private String status;
	private String shipName, shipTel, billName, billTel;
	private int shipAddress, billAddress;

	public PO(int id, int uid, String status, String shipName, String shipTel, String billName, String billTel,
			int shipAddress, int billAddress) {
		super();
		this.id = id;
		this.uid = uid;
		this.status = status;
		this.shipName = shipName;
		this.shipTel = shipTel;
		this.billName = billName;
		this.billTel = billTel;
		this.shipAddress = shipAddress;
		this.billAddress = billAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipTel() {
		return shipTel;
	}

	public void setShipTel(String shipTel) {
		this.shipTel = shipTel;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillTel() {
		return billTel;
	}

	public void setBillTel(String billTel) {
		this.billTel = billTel;
	}

	public int getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(int shipAddress) {
		this.shipAddress = shipAddress;
	}

	public int getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(int billAddress) {
		this.billAddress = billAddress;
	}

	@Override
	public String toString() {
		return "PO [id=" + id + ", uid=" + uid + ", status=" + status + ", shipName=" + shipName + ", shipTel="
				+ shipTel + ", billName=" + billName + ", billTel=" + billTel + ", shipAddress=" + shipAddress
				+ ", billAddress=" + billAddress + "]";
	}
	
	

}
