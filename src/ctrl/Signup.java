package ctrl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import bean.Province;
import dao.AddressDAO;
import dao.UserDAO;

public class Signup extends Controller {
	private String username, password, fname, lname, tel, address, zip, province, city;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Province> getAllProvince() throws Exception {
		return AddressDAO.getAllProvince();
	}

	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String data = value.toString();
		if (data.isEmpty()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username cannot be empty",
					"Please enter your username"));
		} else if (data.length() < 3 || data.length() > 20) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username length invalid",
					"Username length invalid"));
		} else if (!data.matches("^[a-zA-Z].*$")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Username invalid",
					"Username should start with letters"));
		}
		try {
			if (!UserDAO.usernameAvailable(data)) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Already in-use",
					"This username already exists! Please try another one."));
		}
	}

	private boolean validate() {
		if (username == null || username.isEmpty()) {
			addError("Please enter your username");
			return false;
		}
		if (username.length() < 3 || username.length() > 20) {
			addError("Username length invalid");
			return false;
		}
		if (!username.matches("^[a-zA-Z].*$")) {
			addError("Username should start with letters");
			return false;
		}
		try {
			if (!UserDAO.usernameAvailable(username)) {
				throw new Exception();
			}
		} catch (Exception e) {
			addError("Already in-use", "This username already exists! Please try another one.");
			return false;
		}
		if (password == null || password.length() < 4 || password.length() > 16) {
			addError("Please enter a password at least 4 characters");
			return false;
		}
		fname = capitalize(fname);
		if (fname == null || fname.isEmpty() || !fname.matches("[A-Z][a-zA-Z]+")) {
			addError("Please enter your first name");
			return false;
		}
		lname = capitalize(lname);
		if (lname == null || lname.isEmpty() || !lname.matches("[A-Z][a-zA-Z]+")) {
			addError("Please enter your last name");
			return false;
		}
		if (tel == null || tel.isEmpty() || !tel.matches("\\d{3}-\\d{3}-\\d{4}")) {
			addError("Please enter your phone number");
			return false;
		}
		if (province == null || province.isEmpty()) {
			addError("Please select your province");
			return false;
		}
		city = capitalize(city);
		if (city == null || city.isEmpty()) {
			addError("Please enter your city");
			return false;
		}
		address = capitalize(address);
		if (address == null || address.isEmpty()) {
			addError("Please enter your address");
			return false;
		}
		zip = zip.toUpperCase();
		if (zip == null || zip.isEmpty() || !zip.matches("[A-Z]\\d[A-Z]( )?\\d[A-Z]\\d")) {
			addError("Please enter your ZIP code");
			return false;
		}
		return true;
	}

	public String signup() {
		if (validate()) {
			try {
				int addressID = AddressDAO.getAddressID(address, city, province, zip);
				UserDAO.addUser(username, password, fname, lname, addressID, tel, "CUSTOMER");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				addHint("Sign Up Seccessful", "You can use '" + username + "' to login now");
				return "login?faces-redirect=true";
			} catch (Exception e) {
				addErrorMessage(e);
				return null;
			}
		} else {
			return null;
		}
	}

}
