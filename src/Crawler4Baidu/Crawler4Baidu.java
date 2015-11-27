package Crawler4Baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public abstract class Crawler4Baidu 
{
	protected String host;
	protected String key;
	protected String url;
	protected int pages;
	protected int resultCount;
	
	protected Document doc=null;
	protected Elements links=null;
	
	protected Element nextPageLink=null;
	
	public List<Map<String, Element>> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Map<String, Element>> linkList) {
		this.linkList = linkList;
	}

	public Map<String, Element> getLinkMap() {
		return linkMap;
	}

	public void setLinkMap(Map<String, Element> linkMap) {
		this.linkMap = linkMap;
	}

	protected List<Map<String,Element>> linkList=new ArrayList<Map<String, Element>>();
	protected Map<String, Element> linkMap=null;
	
	
	public Crawler4Baidu(String key)
	{
		try {
			System.out.println(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract boolean connect();
	
	public abstract void execute();
	
	public abstract String getNextPage();
	
	
	public abstract void print();
	
	public abstract int getResultCount();
	

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
}
