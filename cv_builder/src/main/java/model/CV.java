package model;

public class CV {
	private int id;
	private int userId;
	private String name;
	private String first_name;
	private String last_name;
	private String phone;
	private String address;
	private String email;
	private String website;
	private String date;

	public CV(int id, int userId, String name, String first_name, String last_name, String phone, String address,
			String email, String website, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.website = website;
		this.date = date;
	}

	public CV(int id, int userId, String first_name, String last_name, String phone, String address, String email,
			String website) {
		super();
		this.id = id;
		this.userId = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.website = website;
	}

	public CV(int userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
