package project04.vo;

public class Rez {
	private int rezid;
	private String rezidS;
	private String date;
	private String pname;
	private String name;
	private String email;
	private String phone;
	private String pay;
	public Rez() {
		// TODO Auto-generated constructor stub
	}
	public Rez(int rezid) {
		this.rezid = rezid;
	}
	public Rez(int rezid, String date, String pname, String name, String email, String phone, String pay) {
		super();
		this.rezid = rezid;
		this.date = date;
		this.pname = pname;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.pay = pay;
	}
	public Rez(String date, String pname, String name, String email, String phone, String pay) {
		super();
		this.date = date;
		this.pname = pname;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.pay = pay;
	}


	public String getRezidS() {
		rezidS = Integer.toString(getRezid());
		return rezidS;
	}
	public void setRezidS(String rezidS) {
		this.rezidS = rezidS;
	}
	public int getRezid() {
		return rezid;
	}
	public void setRezid(int rezid) {
		this.rezid = rezid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}

}