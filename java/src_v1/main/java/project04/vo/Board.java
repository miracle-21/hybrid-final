package project04.vo;

public class Board {
	private int postid;
	private int accno;
	private String ptype;
	public Board() {
		// TODO Auto-generated constructor stub
	}
	public Board(int postid, int accno, String ptype) {
		this.postid = postid;
		this.accno = accno;
		this.ptype = ptype;
	}
	public Board(int accno, String ptype) {
		this.accno = accno;
		this.ptype = ptype;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
}
