package Crawler4TianYa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlExtract {
	public HtmlExtract(){
		
	}
	
	public Atl findAtl(String url){
		if(url.startsWith("http://bbs.tianya.cn/post-") && url.endsWith("-1.shtml")){
			return this.extract(url);
		}
		return null;
	}
	
	private Atl extract(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Atl atl=new Atl(doc);
			return atl;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
