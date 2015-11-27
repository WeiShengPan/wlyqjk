package Crawler4Baidu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class Crawler4BaiduNewsPage extends Crawler4Baidu{

	public Crawler4BaiduNewsPage(String key) {
		super(key);
		// TODO Auto-generated constructor stub
		host="http://news.baidu.com";
		this.key="/ns?word="+key+"&tn=news&from=news&cl=2&rn=20&ct=1";
		url=host+this.key;
		
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		try {
			doc=Jsoup.connect(url).get();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(int i=0;i<=getPages()-1;i++)
		{
			if(url==null)
				break;
			else
			{
				if(this.connect()==true)
				{
						linkMap=new HashMap<String,Element>();
						links=doc.select("div#content_left h3.c-title a[href]");
						for(Element link:links)
						{
							linkMap.put(link.text(), link);
						}
						
						linkList.add(linkMap);
						
						getNextPage();
				}
			}
		}
		print();
	}

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		nextPageLink=doc.select("p#page a:contains(下一页)").first();
		if(nextPageLink==null)
			url=null;
		else
			url=nextPageLink.attr("abs:href");
		
		return url;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<linkList.size();i++)
		{
			System.out.printf("page:%d\n", i+1);
			for(Map.Entry<String, Element> entry:linkList.get(i).entrySet())
			{
				System.out.printf("标题:%s\n",entry.getKey());
				System.out.printf("网址:%s\n", entry.getValue().attr("abs:href"));
			}
			System.out.println();
		}
	}

	@Override
	public int getResultCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
