package Recon.ReconAutomation.cmp;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import Recon.ReconAutomation.cmp.CmpFilePojo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CmpFileProcessing {
	private String url;
		XSSFWorkbook workbook;
		public CmpFileProcessing() {
			
			  
		}
		
		
		public CmpFileProcessing(String x) {
			// TODO Auto-generated constructor stub
			x=this.url;
		}


		public void readFile(String ur, String continentCode, String countryCode) {
			
				
				String sheetName = "CMP";
				XSSFWorkbook workbook;
				int subscriptionIDColNumber = 0;
				int postTaxTotalColNumber = 0;
				
				
				try {
					FileInputStream fileInputStreamCmpFile=new FileInputStream(new File(url));
					workbook = new XSSFWorkbook(fileInputStreamCmpFile);
		              XSSFSheet sheet = workbook.getSheetAt(0);
		              ArrayList<CmpFilePojo> cmpdataList = new ArrayList<CmpFilePojo>();
		              for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
		            	  CmpFilePojo data=new CmpFilePojo();
		            	  cmpdataList.add(data);
		              }
		              
		              for(CmpFilePojo dt: cmpdataList){
		                  System.out.println("");
		              }

		              fileInputStreamCmpFile.close();
				}
				catch(Exception e) {
					e.printStackTrace();				
				}
		}
		  
}
