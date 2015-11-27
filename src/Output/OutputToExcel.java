package Output;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jsoup.nodes.Element;

import Crawler4Baidu.Crawler4Baidu;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OutputToExcel {

	private String fileName;
	private String[] titles;
	private Crawler4Baidu data;
	//private String path;
	
	private WritableWorkbook workbook;
	
	public OutputToExcel(Crawler4Baidu data,String fileName,String[] titles) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.setFileName(fileName);
//		for(int i=0;i<titles.length;i++)
//			this.titles[i]=titles[i];
		this.titles=titles;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void createWorkbook()
	{
		
		try {
			File file = new File(fileName);
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
			workbook=Workbook.createWorkbook(file);
			WritableSheet sheet=workbook.createSheet("sheet1", 0);
			
			//��ӡ���������
			for(int i=0;i<titles.length;i++)
			{
				Label label=new Label(i,0,titles[i]);
				
				sheet.addCell(label);
				
			}
			
			
			
			int count=1;
			for(int i=0;i<data.getLinkList().size();i++)
			{
				
				for (Map.Entry<String, Element> entry : data.getLinkList().get(i).entrySet()) 
				{
//					System.out.printf("����:%s\n", entry.getKey());
//					System.out.printf("��ַ:%s\n", entry.getValue().attr("abs:href"));
					Label resultLabel=null;
					resultLabel=new Label(0,count,entry.getKey());
					sheet.addCell(resultLabel);
					resultLabel=new Label(1,count,entry.getValue().attr("abs:href"));
					sheet.addCell(resultLabel);			
					count++;
				}
				
			}
			
			workbook.write();
			workbook.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
