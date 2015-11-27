package Crawler4TianYa;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class LinksGrab {

	private Queue<String> unvisited=new LinkedBlockingQueue<String>();
	private Queue<String> visited=new LinkedBlockingQueue<String>();
	private List<Atl> atlList=new ArrayList<Atl>();
	
	public LinksGrab(String url,String key,int number){
			try {
				if(url=="http://bbs.tianya.cn/"){
					String keyword=java.net.URLEncoder.encode(key,"utf-8");
					url="http://search.tianya.cn/bbs?q="+keyword;
					url=this.grab(url, "按发帖时间");
					url=this.grab(url, "搜索标题");
					String nextUrl=url;
					for(int i=1;i<=number;i++){
						System.out.println("第"+i+"页");
						while(url!=null){
							url=this.grab(url, key);
						}
						url=nextUrl.replace("http://search.tianya.cn/bbs?q="+keyword,
								"http://search.tianya.cn/bbs?q="+keyword+"&pn="+(i+1));	
					}
					this.outputToExcel(atlList,key);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings({ "finally", "unused" })
	private String grab(String url,String key){		
		try {
			visited.add(url);
			HtmlExtract extract=new HtmlExtract();
			if(extract.findAtl(url)!=null){
				atlList.add(extract.findAtl(url));
			}
			Document doc = Jsoup.connect(url).timeout(20000).get();
			Elements links;
			if(url.startsWith("http://search.tianya.cn/bbs")){
				if(key=="按发帖时间"||key=="搜索标题"){
					links=doc.getElementById("left").getElementsByTag("a");
				}
				else{
					links=doc.getElementById("main").getElementsByTag("a");				
				}
			}
			else{
				links=doc.getElementsByTag("a");
			}
			for(Element link:links){
				String linkHref=link.attr("abs:href");
				String linkText=link.text();
				if(linkText.contains(key)&&!visited.contains(linkHref)&&!unvisited.contains(linkHref)){
					unvisited.add(linkHref);
					System.out.println(linkText+"	网址:"+linkHref);
				}
			}
			url=unvisited.poll();
			while(url.startsWith("http://bbs.tianya.cn/list")){
				url=unvisited.poll();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return url;
		}
	}
	
	public void outputToExcel(List<Atl> atlList,String key){
//		Workbook inBook=null;
		WritableWorkbook outBook=null;
		try {
			Date nowDate=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String filePath = "F:\\ExcelFile"+File.separator +key+"_TianYa"+sdf.format(nowDate) +".xls";  
	         File file = new File(filePath);
	         if (!file.getParentFile().exists()) {
	        	 if (!file.getParentFile().mkdirs()) {
	        		 
	        	 }
	         }  
			 if(!file.exists()) {
				 try {
					 file.createNewFile();
				 } 
				 catch (IOException e) {
					 e.printStackTrace();
				 }  
		     }
			outBook=Workbook.createWorkbook(file);
			WritableSheet sheet=outBook.createSheet("sheet1",0);
			sheet.addCell(new Label(0,0,"标题"));
			sheet.addCell(new Label(1,0,"楼主"));
			sheet.addCell(new Label(2,0,"时间"));
			sheet.addCell(new Label(3,0,"点击量"));
			sheet.addCell(new Label(4,0,"回复量"));
			sheet.addCell(new Label(5,0,"正文"));
			int i=1;
			for(Atl atl:atlList){
				sheet.addCell(new Label(0,i,atl.getTitle()));
				sheet.addCell(new Label(1,i,atl.getAuthor()));
				sheet.addCell(new Label(2,i,atl.getDate()));
				sheet.addCell(new Label(3,i,atl.getClick()));
				sheet.addCell(new Label(4,i,atl.getReply()));
				sheet.addCell(new Label(5,i,atl.getContent()));
				i=i+1;
			}
			outBook.write();
			outBook.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
        }	    
	}
}
