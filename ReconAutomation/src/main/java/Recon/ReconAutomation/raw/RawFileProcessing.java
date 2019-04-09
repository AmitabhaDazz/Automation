package Recon.ReconAutomation.raw;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;

import Recon.ReconAutomation.raw.*;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Recon.ReconAutomation.raw.RawFilePojo;


public class RawFileProcessing{
private static final int RawFilePojo = 0;
private String url;
XSSFWorkbook workbook;
public RawFileProcessing() {

}

public RawFileProcessing(String x) {


x=this.url;
}

public String readFile(String ur) {
	String sheetName = "AP Recon";
	XSSFWorkbook workbook;
	try {
		FileInputStream fileInputStreamRawFile=new FileInputStream(new File(ur));
		workbook = new XSSFWorkbook(fileInputStreamRawFile);
		System.out.println("File:" + workbook.getSheetIndex(sheetName));
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<RawFilePojo> rawdataList = new ArrayList<RawFilePojo>();
		Map<String, List<RawFilePojo>> rawmapforpojo = new HashMap<String, List<RawFilePojo>>();
		List<RawFilePojo> rawfilePojoList = new ArrayList<RawFilePojo>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if(row.getRowNum()==0) {

			}
			if(row.getRowNum()>0) {
				RawFilePojo rawfilepojo = new RawFilePojo();
				if(row.getCell(9)!=null) {
					BigDecimal a = new BigDecimal(row.getCell(26).getNumericCellValue()*1.0941);
					@SuppressWarnings("deprecation")
					BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
					rawfilepojo = new RawFilePojo(row.getCell(9).toString(), row.getCell(26).toString());
					rawfilepojo.setResellerCost(a.toString());
					if(rawmapforpojo.containsKey(row.getCell(9).toString())) {
						rawfilePojoList = rawmapforpojo.get(row.getCell(9).toString());
						rawfilePojoList.add(rawfilepojo);
					}else {
						rawfilePojoList = new ArrayList<RawFilePojo>();
						rawfilePojoList.add(rawfilepojo);
						rawmapforpojo.put(row.getCell(9).toString(), rawfilePojoList);
					}
					System.out.println("Subscriptionid:" +rawfilepojo.getSubscriptionid()+"---"+ "Posttaxtotal:"+rawfilepojo.getPosttaxtotal()+"---"+"Resellercost:" +rawfilepojo.getResellerCost());
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		//System.out.println(rawmapforpojo);
		
		fileInputStreamRawFile.close();
	}
		
		
		
		
		
	catch(Exception e) {
		e.printStackTrace();
	}
	return sheetName;
}
}


