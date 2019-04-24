//StartAuto
package Recon.ReconAutomation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OutputAutomation.ExcelWriter;
import OutputAutomation.OutputPojo;
import Recon.ReconAutomation.cmp.CmpFilePojo;
import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFilePojo;
import Recon.ReconAutomation.raw.RawFileProcessing;


public class StartAuto {
	public static void main(String[] args) throws IOException {


		//Reading from config File
		
		/*
		 * configFileProcess configPath = new configFileProcess();
		 * 
		 * 
		 * String urlCmp=configPath.readFileCmp(); String
		 * urlraw=configPath.readFileRaw(); String
		 * urloutput=configPath.readFileOutput();
		 */
		 
		 
		
		
		  String urlCmp= args[0]; String urlraw=args[1]; String urloutput=args[2];
		 
		 

		//		RawFileProcessed processedObj = new RawFileProcessed(rawFileProcessing.getRawFilePojoMap());
		//		processedObj.createMapBySubscriptionID();
		RawFileProcessing rawFileProcessing = new RawFileProcessing();




		CmpFileProcessing cmp = new CmpFileProcessing();
		cmp.readFile(urlCmp);


		CmpFileProcessed processedObj = new CmpFileProcessed(cmp.getCmpfilePojoList());
		processedObj.createMapBySubscriptionID();
		processedObj.createMapByResellerCompanyName();


		rawFileProcessing.readFile(urlraw, processedObj);


		CaseInsensitiveMap<String, OutputPojo> outputMap = new CaseInsensitiveMap<String, OutputPojo>();
		for (Map.Entry<String, List<CmpFilePojo>> entry : processedObj.getSubscriptionIDMap().entrySet()) {
			String subscriptionID = entry.getKey();
			OutputPojo subIdBag= new OutputPojo();
			subIdBag.setCmpIngramMicroCost(processedObj.getPartnerCostBySubscriptionID(subscriptionID).toString());
			subIdBag.setCmpResellerCost(processedObj.getResellerCostBySubscriptionID(subscriptionID).toString());
			subIdBag.setResellerCompanyName(processedObj.getResellerCompanyNameBySubscriptionID(subscriptionID));
			subIdBag.setParnerCenterResellerCost(rawFileProcessing.getResellerCostBySubscriptionID(subscriptionID));
			subIdBag.setPartnerCenterIngramCost(rawFileProcessing.getPosttaxtotalCostBySubscriptionID(subscriptionID));
			outputMap.put(subscriptionID, subIdBag);
		}


		for (Map.Entry<String, List<RawFilePojo>> entry : rawFileProcessing.getRawmapforpojo().entrySet()) { 
			String subId= entry.getKey();
			OutputPojo output = outputMap.get(subId);
			if(output!=null && subId != null) {
				String compName=processedObj.getResellerCompanyNameBySubscriptionID(subId);
				output.setResellerCompanyName(processedObj.getResellerCompanyNameBySubscriptionID(subId.toString()));
				output.setParnerCenterResellerCost(rawFileProcessing.getResellerCostByResellerCompanyName(compName));
				output.setPartnerCenterIngramCost(rawFileProcessing.getPartnerCenterIMCostByResellerCompanyName(compName));
			}
		}



		CaseInsensitiveMap<String, OutputPojo> outputMapFinal = new CaseInsensitiveMap<String, OutputPojo>();


		for (Map.Entry<String,OutputPojo> entry : outputMap.entrySet()) {
			String subId= entry.getKey();
			OutputPojo outputObjLocal = outputMap.get(subId);
			//			if(outputMapFinal.containsKey(outputObjLocal.getResellerCompanyName())) {
			outputObjLocal = outputMap.get(subId);

			BigDecimal bigDecimalCmpIngramMicroCost = new BigDecimal(outputObjLocal.getCmpIngramMicroCost());
			bigDecimalCmpIngramMicroCost = bigDecimalCmpIngramMicroCost.add(new BigDecimal(processedObj.format(outputObjLocal.getCmpIngramMicroCost().toString(), bigDecimalCmpIngramMicroCost.scale() > 0 ? bigDecimalCmpIngramMicroCost.precision() : bigDecimalCmpIngramMicroCost.scale())));
			outputObjLocal.setCmpIngramMicroCost(bigDecimalCmpIngramMicroCost.toString());

			outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
			//			}else {
			if(rawFileProcessing.getRawmapforpojo().containsKey(subId)) {
				outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
			}
			//			}
		}
		int i=0;
		int size = outputMapFinal.size();

		XSSFWorkbook excelWookBook = new XSSFWorkbook();
		Sheet sheet = excelWookBook.createSheet("Variance per reseller level");
		Row headerRow=sheet.createRow(0);
		//styling the cell
		//headerRow.setRowStyle(mystyle);
		headerRow.createCell(0).setCellValue("Resellercompanyname");
		headerRow.createCell(1).setCellValue("CMP IMcost");
		headerRow.createCell(2).setCellValue("CMP Reseller cost");
		headerRow.createCell(3).setCellValue("Partner center IMcost");
		headerRow.createCell(4).setCellValue("Partner center Resellercost");
		headerRow.createCell(5).setCellValue("Varianceimcost");
		headerRow.createCell(6).setCellValue("Varianceimcostin%");
		headerRow.createCell(7).setCellValue("VariancePCcost");
		headerRow.createCell(8).setCellValue("VariancePCcostin%");
		for (Map.Entry<String,OutputPojo> entry : outputMapFinal.entrySet()) {
			String resellecrCompanyNameFinal= entry.getKey();
			Row row = sheet .createRow(i+1);
			OutputPojo outputObjLocal = outputMapFinal.get(resellecrCompanyNameFinal);
			//System.out.println(outputObjLocal.getResellerCompanyName() + "-" + outputObjLocal.getCmpIngramMicroCost() +"-"+ outputObjLocal.getCmpResellerCost()+"-"+outputObjLocal.getPartnerCenterIngramCost()+"-"+outputObjLocal.getParnerCenterResellerCost());
			//System.out.println(outputObjLocal.getResellerCompanyName() + "-" + processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal) +"-"+ processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal)+"-"+outputObjLocal.getPartnerCenterIngramCost()+"-"+outputObjLocal.getParnerCenterResellerCost());
			row.createCell(0).setCellValue(outputObjLocal.getResellerCompanyName() );
			BigDecimal cmpic=new BigDecimal(processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal));
			cmpic=cmpic.setScale(2, BigDecimal.ROUND_CEILING);
			row.createCell(1).setCellValue(cmpic.toString());
			BigDecimal cmprc=new BigDecimal(processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal));
			cmprc=cmprc.setScale(2, BigDecimal.ROUND_CEILING);
			row.createCell(2).setCellValue(cmprc.toString());
			String chk=outputObjLocal.getPartnerCenterIngramCost();
				if(chk!="NA") {
					try {
			  BigDecimal pcimc=new BigDecimal(outputObjLocal.getPartnerCenterIngramCost());
			  pcimc=pcimc.setScale(2, BigDecimal.ROUND_CEILING);
			  row.createCell(3).setCellValue(pcimc.toString());
					}catch(Exception e) {
						System.out.println(e);
					}
				}
				else {
					try {
			//pcimc=pcimc.setScale(2, BigDecimal.ROUND_CEILING);
			row.createCell(3).setCellValue(outputObjLocal.getPartnerCenterIngramCost().toString());
					}catch(Exception e) {
						System.out.println(e);
					}
				}
			String sck1=outputObjLocal.getParnerCenterResellerCost();
			if(sck1!="NA") {
				try {
			  BigDecimal pcrc=new BigDecimal(outputObjLocal.getParnerCenterResellerCost());
			  pcrc=pcrc.setScale(2, BigDecimal.ROUND_CEILING);
			 
			 
			
			row.createCell(4).setCellValue(pcrc.toString());
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			else {
				try {
				row.createCell(4).setCellValue(outputObjLocal.getParnerCenterResellerCost().toString());
				}catch(Exception e) {
					System.out.println(e);
				}
			}
			if(!outputObjLocal.getPartnerCenterIngramCost().equals("NA") && !processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal).equals("NA")) {
				BigDecimal varimc = new BigDecimal(Double.parseDouble(outputObjLocal.getPartnerCenterIngramCost())-Double.parseDouble(processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal)));
				varimc = varimc.setScale(2, BigDecimal.ROUND_CEILING);
				row.createCell(5).setCellValue(varimc.toString());
				
				String PCC =outputObjLocal.getPartnerCenterIngramCost();
				String IMC =processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal);
				String p=outputObjLocal.getPartnerCenterIngramCost();
				/* System.out.println(PCC+"***"+IMC+"***"+p); */
				if(PCC!="NA" & IMC!="NA" & p!="NA") {
					try {
				varimc = varimc.valueOf((((Double.parseDouble(PCC)-Double.parseDouble(IMC))/Double.parseDouble(p)))*100);
				varimc = varimc.setScale(2, BigDecimal.ROUND_CEILING);
				row.createCell(6).setCellValue(varimc.toString()+"%");
					}catch(NumberFormatException e) {
						System.out.println(e);
					}
				}
				else {
					row.createCell(6).setCellValue("NA");
				}

			}else {
				row.createCell(5).setCellValue("NA");
				row.createCell(6).setCellValue("NA");
			}
			if(!outputObjLocal.getParnerCenterResellerCost().equals("NA") && !processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal).equals("NA")) {
				BigDecimal varrc = new BigDecimal(Double.parseDouble(outputObjLocal.getParnerCenterResellerCost())-Double.parseDouble(processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal)));
				varrc=varrc.setScale(2,BigDecimal.ROUND_CEILING);
				row.createCell(7).setCellValue(varrc.toString());
				try {
				varrc=varrc.valueOf((((Double.parseDouble(outputObjLocal.getParnerCenterResellerCost())-Double.parseDouble(processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal)))/Double.parseDouble(outputObjLocal.getParnerCenterResellerCost())))*100);		
				varrc = varrc.setScale(2, BigDecimal.ROUND_CEILING);
				row.createCell(8).setCellValue(varrc.toString()+"%");
				}catch(NumberFormatException e) {
					System.out.println(e);
				}

			}else {
				row.createCell(7).setCellValue("NA");
				row.createCell(8).setCellValue("NA");
			}
			i++;
		}


		FileOutputStream fOut = new FileOutputStream(urloutput);
		excelWookBook.write(fOut);
		System.out.println("Processed successfully");
		fOut.close();

	}


}
