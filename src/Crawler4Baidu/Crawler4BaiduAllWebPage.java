package Crawler4Baidu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;



public class Crawler4BaiduAllWebPage extends Crawler4Baidu{


	public Crawler4BaiduAllWebPage(String key) 
	{		
		// TODO Auto-generated constructor stub
		super(key);
		host="http://www.baidu.com";
		this.key="/s?wd="+key;
		url=host+this.key;
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub	
		// connect
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("failed to connect.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void execute() 
	{
		// TODO Auto-generated method stub
		for (int i = 0; i <= getPages() - 1; i++) {
			if(url==null)
				break;
			else {
				if (this.connect() == true) {
					linkMap = new HashMap<String, Element>();

					
					links = doc.select("div#content_left h3[class*=t] a[href]").not("a[href=#]");

					for (Element link : links) {
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
	public String getNextPage() 
	{
		// TODO Auto-generated method stub
		nextPageLink = doc.select("div#page a:contains(下一页)").first();
		if(nextPageLink==null)
			url=null;
		else
			url = nextPageLink.attr("abs:href");
		
		return url;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<linkList.size();i++)
		{
			System.out.printf("page:%d\n",i+1);
			for (Map.Entry<String, Element> entry : linkList.get(i).entrySet()) 
			{
				System.out.printf("标题:%s\n", entry.getKey());
				System.out.printf("网址:%s\n", entry.getValue().attr("abs:href"));
			}
			System.out.println();
		}
		
	}

	@Override
	public int getResultCount() {
		// TODO Auto-generated method stub
		//not used yet!
		Element resultCountElem=doc.select("html body div div div div.nums").first();
		
		String resultCountText=resultCountElem.text();
		String regEx="[^0-9]";   
        Pattern pattern = Pattern.compile(regEx);      
        Matcher matcher = pattern.matcher(resultCountText);
        resultCountText = matcher.replaceAll("");
        resultCount = Integer.parseInt(resultCountText);
		
		return resultCount;
	}

}
