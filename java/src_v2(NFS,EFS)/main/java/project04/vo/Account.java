package project04.vo;

import java.sql.Date;

public class Account {
	private int accno;
	private String name;
	private String id;
	private String pw;
	private Date birthday;
	private String birthdayS;
	private String mnum;
	private String pnum;
	private String email;
	private String address;
	private Date regdate;
	private int admin;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accno, String name, String id, String pw, Date birthday, String mnum, String pnum, String email,
			String address, Date regdate, int admin) {
		super();
		this.accno = accno;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.birthday = birthday;
		this.mnum = mnum;
		this.pnum = pnum;
		this.email = email;
		this.address = address;
		this.regdate = regdate;
		this.admin = admin;
	}
	public Account(String name, String id, String pw, String birthdayS, String mnum, String pnum, String email,
			String address, int admin) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.birthdayS = birthdayS;
		this.mnum = mnum;
		this.pnum = pnum;
		this.email = email;
		this.address = address;
		this.admin = admin;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMnum() {
		return mnum;
	}
	public void setMnum(String mnum) {
		this.mnum = mnum;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getBirthdayS() {
		return birthdayS;
	}
	public void setBirthdayS(String birthdayS) {
		this.birthdayS = birthdayS;
	}
}
