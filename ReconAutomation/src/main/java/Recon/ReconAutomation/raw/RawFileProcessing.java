package Recon.ReconAutomation.raw;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;

import Recon.ReconAutomation.raw.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
List<RawFilePojo> rawfilePojoList = new ArrayList<RawFilePojo>();

public RawFileProcessing() {

}
	/*
	 * public RawFileProcessing(String x) {
	 * 
	 * List<RawFilePojo> rawfilePojoList = new ArrayList<RawFilePojo>() {
	 * 
	 * public <T> T[] toArray(T[] a) { // TODO Auto-generated method stub return
	 * null; }
	 * 
	 * public Object[] toArray() { // TODO Auto-generated method stub return null; }
	 * 
	 * public List<Recon.ReconAutomation.raw.RawFilePojo> subList(int fromIndex, int
	 * toIndex) { // TODO Auto-generated method stub return null; }
	 * 
	 * public int size() { // TODO Auto-generated method stub return 0; }
	 * 
	 * public Recon.ReconAutomation.raw.RawFilePojo set(int index,
	 * Recon.ReconAutomation.raw.RawFilePojo element) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public boolean retainAll(Collection<?> c) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public boolean removeAll(Collection<?> c) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public Recon.ReconAutomation.raw.RawFilePojo remove(int index) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public boolean remove(Object o) { // TODO Auto-generated method stub return
	 * false; }
	 * 
	 * public ListIterator<Recon.ReconAutomation.raw.RawFilePojo> listIterator(int
	 * index) { // TODO Auto-generated method stub return null; }
	 * 
	 * public ListIterator<Recon.ReconAutomation.raw.RawFilePojo> listIterator() {
	 * // TODO Auto-generated method stub return null; }
	 * 
	 * public int lastIndexOf(Object o) { // TODO Auto-generated method stub return
	 * 0; }
	 * 
	 * public Iterator<Recon.ReconAutomation.raw.RawFilePojo> iterator() { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public boolean isEmpty() { // TODO Auto-generated method stub return false; }
	 * 
	 * public int indexOf(Object o) { // TODO Auto-generated method stub return 0; }
	 * 
	 * public Recon.ReconAutomation.raw.RawFilePojo get(int index) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public boolean containsAll(Collection<?> c) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public boolean contains(Object o) { // TODO Auto-generated method stub return
	 * false; }
	 * 
	 * public void clear() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * public boolean addAll(int index, Collection<? extends
	 * Recon.ReconAutomation.raw.RawFilePojo> c) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public boolean addAll(Collection<? extends
	 * Recon.ReconAutomation.raw.RawFilePojo> c) { // TODO Auto-generated method
	 * stub return false; }
	 * 
	 * public void add(int index, Recon.ReconAutomation.raw.RawFilePojo element) {
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * public boolean add(Recon.ReconAutomation.raw.RawFilePojo e) { // TODO
	 * Auto-generated method stub return false; } }; x=this.url; }
	 */

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
					//System.out.println("Subscriptionid:" +rawfilepojo.getSubscriptionid()+"---"+ "Posttaxtotal:"+rawfilepojo.getPosttaxtotal()+"---"+"Resellercost:" +rawfilepojo.getResellerCost());
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



public List getRawFilePojoList() {
	return rawfilePojoList;
	// TODO Auto-generated method stub
	
}

public void setRawFilePojoList(List rawfilePojoList) {
	this.rawfilePojoList = rawfilePojoList;
}

}


