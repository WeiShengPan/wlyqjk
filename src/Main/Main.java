package Main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Crawler4Baidu.Crawler4Baidu;
import Crawler4Baidu.Crawler4BaiduAllWebPage;
import Crawler4Baidu.Crawler4BaiduNewsPage;
import Output.OutputToExcel;


public class Main extends HttpServlet{


	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		String key=request.getParameter("key");	
		String pages=request.getParameter("pages");
		String option=request.getParameter("option");
		String[] titles = { "标题", "网址" };
		
			
		if(key!=null)
		{	
			if(option.equals("baidu"))
			{
				System.out.println("1");
				Crawler4Baidu crawler4Baidu=new Crawler4BaiduAllWebPage(key);
				crawler4Baidu.setPages(Integer.parseInt(pages));
				crawler4Baidu.execute();
				
				Date nowDate=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				String filePath = "F:\\ExcelFile"+File.separator +"Baidu"+sdf.format(nowDate) +".xls";
				OutputToExcel outputToExcel = new OutputToExcel(crawler4Baidu, filePath, titles);
				outputToExcel.createWorkbook();
			}
			else if(option.equals("baidunews"))
			{
				System.out.println("2");
				Crawler4Baidu crawler4Baidu=new Crawler4BaiduNewsPage(key);
				crawler4Baidu.setPages(Integer.parseInt(pages));
				crawler4Baidu.execute();
			}
			else if(option.equals("tianya"))
			{
				System.out.println("3");
				System.out.println("tianya");
			}
		}
		
	}
	
	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		Crawler4Baidu crawler3 = new Crawler4BaiduNewsPage("���Ŵ�ѧ");
//		crawler3.setPages(5);
//		crawler3.execute();
//		String[] titles = { "����", "��ַ" };
//		OutputToExcel outputToExcel = new OutputToExcel(crawler3, "D:\\test.xls", titles);
//		outputToExcel.createWorkbook();
//
//		Crawler4Baidu crawler = new Crawler4BaiduAllWebPage("���Ŵ�ѧ");
//		crawler.setPages(3);
//		crawler.execute();
//
//	}

}
