package rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "orderDate", "status", "shipAddress", "billAddress", "items" })
public class Order {
	@XmlTransient
	private int id;
	@XmlAttribute
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date orderDate;
	private String status;
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<Item> items;
	@XmlElement(name = "billTo")
	private Address billAddress;
	@XmlElement(name = "shipTo")
	private Address shipAddress;

	public Order(int id, Date orderDate, String status, Address billAddress, Address shipAddress) {
		this.id = id;
		this.orderDate = orderDate;
		this.status = status;
		this.items = new ArrayList<>();
		this.billAddress = billAddress;
		this.shipAddress = shipAddress;
	}

	public void add(Item poItem) {
		items.add(poItem);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Address getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(Address billAddress) {
		this.billAddress = billAddress;
	}

	public Address getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(Address shipAddress) {
		this.shipAddress = shipAddress;
	}

}
