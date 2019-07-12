package bean;

public class Province {
	private String province;
	private String fullName;

	public Province() {

	}

	public Province(String province, String fullName) {
		super();
		this.province = province;
		this.fullName = fullName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String full_name) {
		this.fullName = full_name;
	}
}
