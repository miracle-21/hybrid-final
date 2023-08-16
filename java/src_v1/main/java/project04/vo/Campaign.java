package project04.vo;

import java.util.Date;

public class Campaign {
	private int postid;
	private int accno;
	private String title;
	private String poster;
	private String link;
	private String content;
	private Date sdate;
	private Date edate;
	private String sdate_s;
	private String edate_s;
	public Campaign() {
		// TODO Auto-generated constructor stub
	}
	public Campaign(int postid, int accno, String title, String poster, String link, String content, Date sdate,
			Date edate) {
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.poster = poster;
		this.link = link;
		this.content = content;
		this.sdate = sdate;
		this.edate = edate;
	}
	public Campaign(int postid, int accno, String title, String poster, String link, String content, String sdate_s,
			String edate_s) {
		this.postid = postid;
		this.accno = accno;
		this.title = title;
		this.poster = poster;
		this.link = link;
		this.content = content;
		this.sdate_s = sdate_s;
		this.edate_s = edate_s;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public String getSdate_s() {
		return sdate_s;
	}
	public void setSdate_s(String sdate_s) {
		this.sdate_s = sdate_s;
	}
	public String getEdate_s() {
		return edate_s;
	}
	public void setEdate_s(String edate_s) {
		this.edate_s = edate_s;
	}
	
}
