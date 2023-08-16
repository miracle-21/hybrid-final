package project04.vo;

import java.util.Date;

public class Search {
	private String ptype;
	private int postid;
	private int accno;
	private String title;
	private Date uploaddate;
	private String content;

	public Search(String ptype, int postid, int accno, String title, Date uploaddate, String content) {
		super();
		this.ptype = ptype;
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.uploaddate = uploaddate;
		this.content = content;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
