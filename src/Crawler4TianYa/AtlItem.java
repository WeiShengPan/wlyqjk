package Crawler4TianYa;

import org.jsoup.nodes.Element;

//论坛正文回复类
public class AtlItem {
	private String id;
	private String author;
	private String date;
	private String content;
	
	public AtlItem(Element element) {
		// TODO Auto-generated constructor stub
		if(element.id()!=""){
			this.setId(element.id());
			this.setAuthor(element.select("div.atl-info").select("span").get(0).text());
			this.setDate(element.select("div.atl-info").select("span").get(1).text());
			this.setContent(element.select("[class=bbs-content]").text());
		}
		else{
			this.setId("");
			this.setAuthor("");
			this.setDate("");
			this.setContent("");
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}

