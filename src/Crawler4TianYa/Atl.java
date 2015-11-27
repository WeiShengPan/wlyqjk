package Crawler4TianYa;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//��̳������
public class Atl {
	private String title;
	private String author;
	private String date;
	private String click;
	private String reply;
	private String content;
	private List<AtlItem> itemList=new LinkedList<AtlItem>();

	public Atl(){
		
	}
	public Atl(Document doc) {
		// TODO Auto-generated constructor stub
		this.setTitle(doc.select("[class=s_title]").text());
		this.setAuthor(doc.select("div.atl-menu").select("div.atl-info").select("span").get(0).text());
		this.setDate(doc.select("div.atl-menu").select("div.atl-info").select("span").get(1).text());
		this.setClick(doc.select("div.atl-menu").select("div.atl-info").select("span").get(2).text());
		this.setReply(doc.select("div.atl-menu").select("div.atl-info").select("span").get(3).text());
		this.setContent(doc.select("[class=bbs-content clearfix]").text());
		Elements elements=doc.select("div.atl-item");
		for(Element element:elements){			
			AtlItem item=new AtlItem(element);
			itemList.add(item);
		}		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public List<AtlItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<AtlItem> itemList) {
		this.itemList = itemList;
	}
}
