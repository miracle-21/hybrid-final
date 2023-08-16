package project04.vo;

import java.util.Date;

public class Posting {
	private int postid;
	private int accno;
	private String subtype;
	private String title;
	private Date uploaddate;
	private String uploaddate_s;
	private String pfile;
	private String content;
	public Posting() {
		// TODO Auto-generated constructor stub
	}
	public Posting(int postid, int accno, String subtype, String title, Date uploaddate, String pfile, String content) {
		this.postid = postid;
		this.accno = accno;
		this.subtype = subtype;
		this.title = title;
		this.uploaddate = uploaddate;
		this.pfile = pfile;
		this.content = content;
	}
	public Posting(int postid, int accno, String subtype, String title, String uploaddate_s, String pfile,
			String content) {
		this.postid = postid;
		this.accno = accno;
		this.subtype = subtype;
		this.title = title;
		this.uploaddate_s = uploaddate_s;
		this.pfile = pfile;
		this.content = content;
	}
	
	public Posting(int postid, int accno, String subtype, String title, String pfile, String content) {
		this.postid = postid;
		this.accno = accno;
		this.subtype = subtype;
		this.title = title;
		this.pfile = pfile;
		this.content = content;
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
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
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
	public String getUploaddate_s() {
		return uploaddate_s;
	}
	public void setUploaddate_s(String uploaddate_s) {
		this.uploaddate_s = uploaddate_s;
	}
	public String getPfile() {
		return pfile;
	}
	public void setPfile(String pfile) {
		this.pfile = pfile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
