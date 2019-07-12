package bean;

import ctrl.Controller;

public class Address {
	private int id;
	private String street, city, province, zip;

	public Address(Address other) {
		this(other.id, other.street, other.city, other.province, other.zip);
	}

	public Address(int id, String street, String city, String province, String zip) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.province = province;
		this.zip = zip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = Controller.capitalize(street);
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip.toUpperCase();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = Controller.capitalize(city);
	}

}
