//RawFileprocessing


package Recon.ReconAutomation.raw;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.raw.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OutputAutomation.OutputPojo;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import Recon.ReconAutomation.raw.RawFilePojo;


public class RawFileProcessing{


	CaseInsensitiveMap<String, List<RawFilePojo>> rawmapforpojo = new CaseInsensitiveMap<String, List<RawFilePojo>>();
	CaseInsensitiveMap<String, List<RawFilePojo>> rawmapforpojoRCN = new CaseInsensitiveMap<String, List<RawFilePojo>>();


	public CaseInsensitiveMap<String, List<RawFilePojo>> getRawmapforpojo() {
		return rawmapforpojo;
	}

	public void setRawmapforpojo(CaseInsensitiveMap<String, List<RawFilePojo>> rawmapforpojo) {
		this.rawmapforpojo = rawmapforpojo;
	}

	public RawFileProcessing() {


	}

	public void readFile(String ur, CmpFileProcessed processedObj) {
		String sheetName = "AP Recon";
		XSSFWorkbook workbook;
		try {
			File f=new File(ur);	
			if(f.exists()) {
			FileInputStream fileInputStreamRawFile=new FileInputStream(f);
			workbook = new XSSFWorkbook(fileInputStreamRawFile);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			List<RawFilePojo> rawfilePojoList = new ArrayList<RawFilePojo>();
			//		Map<String, List<RawFilePojo>> rawmapforpojo = new HashMap<String, List<RawFilePojo>>();
			Iterator<Row> rowIterator = sheet.iterator();
			int rownum = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(rownum==0) {

				}
				if(rownum>0) {
					RawFilePojo rawfilepojo = new RawFilePojo();
					if(row.getCell(9)!=null) {
						BigDecimal a = new BigDecimal(row.getCell(26).getNumericCellValue()*1.0941);
						@SuppressWarnings("deprecation")
						BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
						rawfilepojo = new RawFilePojo(row.getCell(9).toString(), row.getCell(26).toString());
						rawfilepojo.setResellerCompName(processedObj.getResellerCompanyNameBySubscriptionID(row.getCell(9).toString()));
						rawfilepojo.setResellerCost(a.toString());					
						if(rawmapforpojo.containsKey(row.getCell(9).toString())) {
							rawfilePojoList = rawmapforpojo.get(row.getCell(9).toString());
							rawfilePojoList.add(rawfilepojo);
						}else {
							rawfilePojoList = new ArrayList<RawFilePojo>();
							rawfilePojoList.add(rawfilepojo);
							rawmapforpojo.put(row.getCell(9).toString(), rawfilePojoList);
						}
							
					}
				}
				rownum++;
			}
			fileInputStreamRawFile.close();
		}
			else {
				System.out.println("File not exits");
				System.exit(0);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public String getResellerCostByResellerCompanyName(String resellerCompanyName){
		List<RawFilePojo> allsameRCNList = new ArrayList<RawFilePojo>();
		for (Map.Entry<String, List<RawFilePojo>> entry : rawmapforpojo.entrySet()) { 
			List<RawFilePojo> rawpojo = entry.getValue();
			if(resellerCompanyName!=null && rawpojo.get(0).getResellerCompName().toString()!=null) {
			if(rawpojo.get(0).getResellerCompName().equalsIgnoreCase(resellerCompanyName)) {
				allsameRCNList.addAll(rawpojo);
			}
			}
			else {
				System.out.println(resellerCompanyName);
			}
		}
		
				
			ListIterator<RawFilePojo> iterator = allsameRCNList.listIterator();
			BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
			bigDecimalResellerCost.setScale(3, BigDecimal.ROUND_CEILING);
			while (iterator.hasNext()) {
				RawFilePojo obj = (RawFilePojo)iterator.next();
				String resellecrCost = obj.getResellerCost().toString();
				BigDecimal resellerCosrBD = new BigDecimal(resellecrCost);
				resellerCosrBD.setScale(3, BigDecimal.ROUND_CEILING);
				bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(format(obj.getResellerCost().toString(), resellerCosrBD.scale() > 0 ? resellerCosrBD.precision() : resellerCosrBD.scale())));
			}
			return bigDecimalResellerCost.toString();
	}
	
	public String getPartnerCenterIMCostByResellerCompanyName(String resellerCompanyName){
		List<RawFilePojo> allsameRCNList = new ArrayList<RawFilePojo>();
		for (Map.Entry<String, List<RawFilePojo>> entry : rawmapforpojo.entrySet()) { 
			List<RawFilePojo> rawpojo = entry.getValue();
			if(resellerCompanyName!=null) {
			if(rawpojo.get(0).getResellerCompName().equalsIgnoreCase(resellerCompanyName)) {
				allsameRCNList.addAll(rawpojo);
			}
			}
		}
				
			ListIterator<RawFilePojo> iterator = allsameRCNList.listIterator();
			BigDecimal bigDecimalPostTaxTotal = new BigDecimal(0.0);
			bigDecimalPostTaxTotal.setScale(3, BigDecimal.ROUND_CEILING);
			while (iterator.hasNext()) {
				RawFilePojo obj = (RawFilePojo)iterator.next();
				String partnetCenterIMCost = obj.getPosttaxtotal().toString();
				BigDecimal partnerCenterCost = new BigDecimal(partnetCenterIMCost);
				partnerCenterCost.setScale(3, BigDecimal.ROUND_CEILING);
				bigDecimalPostTaxTotal = bigDecimalPostTaxTotal.add(new BigDecimal(format(obj.getPosttaxtotal().toString(), partnerCenterCost.scale() > 0 ? partnerCenterCost.precision() : partnerCenterCost.scale())));
			}
			return bigDecimalPostTaxTotal.toString();
	}
	
	public String getResellerCostBySubscriptionID(String subscriptionID){
		if(rawmapforpojo.containsKey(subscriptionID)){
			List<RawFilePojo> subTotal = rawmapforpojo.get(subscriptionID);
			ListIterator<RawFilePojo> iterator = subTotal.listIterator();
			BigDecimal bigDecimalResellerCost = new BigDecimal(0.0);
			bigDecimalResellerCost.setScale(3, BigDecimal.ROUND_CEILING);
			while (iterator.hasNext()) {
				RawFilePojo obj = (RawFilePojo)iterator.next();
				String resellecrCost = obj.getResellerCost().toString();
				BigDecimal resellerCosrBD = new BigDecimal(resellecrCost);
				resellerCosrBD.setScale(3, BigDecimal.ROUND_CEILING);
				bigDecimalResellerCost = bigDecimalResellerCost.add(new BigDecimal(format(obj.getResellerCost().toString(), resellerCosrBD.scale() > 0 ? resellerCosrBD.precision() : resellerCosrBD.scale())));
			}
			return bigDecimalResellerCost.toString();
		}else {
			return "NA";
		}

	}

	public String getPosttaxtotalCostBySubscriptionID(String subscriptionID){
		if(rawmapforpojo.containsKey(subscriptionID)){
			List<RawFilePojo> subTotal = rawmapforpojo.get(subscriptionID);
			ListIterator<RawFilePojo> iterator = subTotal.listIterator();
			BigDecimal bigDecimalpostTaxTotal = new BigDecimal(0.0);
			bigDecimalpostTaxTotal.setScale(3, BigDecimal.ROUND_CEILING);
			while (iterator.hasNext()) {
				RawFilePojo obj = (RawFilePojo)iterator.next();
				String postTaxTotal = obj.getPosttaxtotal().toString();
				BigDecimal postTaxTotalBD = new BigDecimal(postTaxTotal);
				postTaxTotalBD.setScale(3, BigDecimal.ROUND_CEILING);
				bigDecimalpostTaxTotal = bigDecimalpostTaxTotal.add(new BigDecimal(format(obj.getPosttaxtotal().toString(), postTaxTotalBD.scale() > 0 ? postTaxTotalBD.precision() : postTaxTotalBD.scale())));
			}
			return bigDecimalpostTaxTotal.toString();
		}else {
			return "NA";
		}
	}



	private String format(String number, int scale) {
		BigDecimal value = new BigDecimal(number);
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
		BigDecimal positive = new BigDecimal(1);// scale is zero
		positive.setScale(0);// unnecessary
		BigDecimal negative = new BigDecimal(-1);// scale is zero
		negative.setScale(0);// unnecessary
		if (value.compareTo(positive) == 1 || value.compareTo(negative) == -1) {
			symbols.setExponentSeparator("e+");
		} else {
			symbols.setExponentSeparator("e");
		}
		DecimalFormat formatter = new DecimalFormat("0.0E0", symbols);
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		formatter.setMinimumFractionDigits(scale);
		return formatter.format(value);
	}

}



