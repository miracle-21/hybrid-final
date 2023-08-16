package project04.vo;

public class Ecog {
	private int postid;
	private int accno;
	private String mm;
	private String exspace;
	private String lcategory;
	private String mcategory;
	private String scategory;
	private String sname;
	private String kname;
	private String distribution;
	private String content;
	private String imgurl;
	public Ecog() {
		// TODO Auto-generated constructor stub
	}
	
	public Ecog(int postid, int accno, String mm, String exspace, String lcategory, String mcategory, String scategory,
			String sname, String kname, String distribution, String content, String imgurl) {
		this.postid = postid;
		this.accno = accno;
		this.mm = mm;
		this.exspace = exspace;
		this.lcategory = lcategory;
		this.mcategory = mcategory;
		this.scategory = scategory;
		this.sname = sname;
		this.kname = kname;
		this.distribution = distribution;
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
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getExspace() {
		return exspace;
	}
	public void setExspace(String exspace) {
		this.exspace = exspace;
	}
	public String getLcategory() {
		return lcategory;
	}
	public void setLcategory(String lcategory) {
		this.lcategory = lcategory;
	}
	public String getMcategory() {
		return mcategory;
	}
	public void setMcategory(String mcategory) {
		this.mcategory = mcategory;
	}
	public String getScategory() {
		return scategory;
	}
	public void setScategory(String scategory) {
		this.scategory = scategory;
	}
	public String getDistribution() {
		return distribution;
	}
	public void setDistribution(String distribution) {
		this.distribution = distribution;
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
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}
	
}
