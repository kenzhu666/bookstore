package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "fullName", "phone", "addr" })
public class User {
	@XmlAttribute(name = "uid")
	private int id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private int address;
	@XmlElement(name = "phone")
	private String phone;
	private String type;
	@XmlElement(name = "address")
	private Address addr;

	public User() {

	}

	public User(int id, String username, String password, String fname, String lname, int address, String phone,
			String type) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "name")
	public String getFullName() {
		return String.format("%s %s", fname, lname);
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public void setAddress(Address address) {
		this.addr = address;
	}

}
