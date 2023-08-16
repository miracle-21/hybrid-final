package project04.vo;

import java.util.Date;

public class Photog {
	//postid, userid, title, uploaddate, content, imgurl 
	private int postid;
	private int accno;
	private String title;
	private Date uploaddate;
	private String uploaddate_s;
	private String content;
	private String imgurl;
	public Photog() {
		// TODO Auto-generated constructor stub
	}
	
	public Photog(int postid, int accno, String title, String content, String imgurl) {
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.content = content;
		this.imgurl = imgurl;
	}

	public Photog(int accno, String title, String content, String imgurl) {
		this.accno = accno;
		this.title = title;
		this.content = content;
		this.imgurl = imgurl;
	}

	public Photog(int postid, int accno, String title, String uploaddate_s, String content, String imgurl) {
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.uploaddate_s = uploaddate_s;
		this.content = content;
		this.imgurl = imgurl;
	}

	public Photog(int postid, int accno, String title, Date uploaddate, String content, String imgurl) {
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.uploaddate = uploaddate;
		this.content = content;
		this.imgurl = imgurl;
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUploaddate_s() {
		return uploaddate_s;
	}

	public void setUploaddate_s(String uploaddate_s) {
		this.uploaddate_s = uploaddate_s;
	}
	
	
}
