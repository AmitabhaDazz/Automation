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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OutputAutomation.ExcelWriter;
import OutputAutomation.OutputPojo;
import OutputAutomation.OutputPojoforReseller;
import Recon.ReconAutomation.cmp.CmpFilePojo;
import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFilePojo;
import Recon.ReconAutomation.raw.RawFileProcessing;

public class StartAuto {
	public static void main(String[] args) throws IOException {

		// Reading from config File
		configFileProcess configPath = new configFileProcess();
		String urlCmp = configPath.readFileCmp();
		String urlraw = configPath.readFileRaw();
		String urloutput = configPath.readFileOutput();

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
			OutputPojo subIdBag = new OutputPojo();
			subIdBag.setCmpIngramMicroCost(processedObj.getPartnerCostBySubscriptionID(subscriptionID).toString());
			subIdBag.setCmpResellerCost(processedObj.getResellerCostBySubscriptionID(subscriptionID).toString());
			subIdBag.setResellerCompanyName(processedObj.getResellerCompanyNameBySubscriptionID(subscriptionID));
			subIdBag.setParnerCenterResellerCost(rawFileProcessing.getResellerCostBySubscriptionID(subscriptionID));
			subIdBag.setPartnerCenterIngramCost(rawFileProcessing.getPosttaxtotalCostBySubscriptionID(subscriptionID));
			outputMap.put(subscriptionID, subIdBag);
		}

		for (Map.Entry<String, List<RawFilePojo>> entry : rawFileProcessing.getRawmapforpojo().entrySet()) {
			String subId = entry.getKey();
			OutputPojo output = outputMap.get(subId);
			if (output != null) {
				output.setParnerCenterResellerCost(rawFileProcessing.getResellerCostByResellerCompanyName(
						processedObj.getResellerCompanyNameBySubscriptionID(subId)));
				output.setPartnerCenterIngramCost(rawFileProcessing.getPosttaxtotalCostBySubscriptionID(subId));
			} else {
			}
		}

		CaseInsensitiveMap<String, OutputPojo> outputMapFinal = new CaseInsensitiveMap<String, OutputPojo>();

		for (Map.Entry<String, OutputPojo> entry : outputMap.entrySet()) {
			String subId = entry.getKey();
			OutputPojo outputObjLocal = outputMap.get(subId);
			if (outputMapFinal.containsKey(outputObjLocal.getResellerCompanyName())) {
				outputObjLocal = outputMap.get(subId);

				BigDecimal bigDecimalCmpIngramMicroCost = new BigDecimal(outputObjLocal.getCmpIngramMicroCost());
				bigDecimalCmpIngramMicroCost = bigDecimalCmpIngramMicroCost
						.add(new BigDecimal(processedObj.format(outputObjLocal.getCmpIngramMicroCost().toString(),
								bigDecimalCmpIngramMicroCost.scale() > 0 ? bigDecimalCmpIngramMicroCost.precision()
										: bigDecimalCmpIngramMicroCost.scale())));
				outputObjLocal.setCmpIngramMicroCost(bigDecimalCmpIngramMicroCost.toString());

				outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
			} else {
				if (rawFileProcessing.getRawmapforpojo().containsKey(subId)) {
					outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
				}
			}
		}
		int i = 0;
		int size = outputMapFinal.size();
		XSSFWorkbook excelWookBook = new XSSFWorkbook();
		Sheet sheet = excelWookBook.createSheet("Variance per reseller level");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Resellercompanyname");
		headerRow.createCell(1).setCellValue("CMP IMcost");
		headerRow.createCell(2).setCellValue("CMP Reseller cost");
		headerRow.createCell(3).setCellValue("Partner center IMcost");
		headerRow.createCell(4).setCellValue("Partner center Resellercost");
		headerRow.createCell(5).setCellValue("Varianceimcost");
		headerRow.createCell(5).setCellValue("Varianceimcostin%");
		headerRow.createCell(5).setCellValue("VariancePCcost");
		headerRow.createCell(5).setCellValue("VariancePCcostin%");
		for (Map.Entry<String, OutputPojo> entry : outputMapFinal.entrySet()) {
			String resellecrCompanyNameFinal = entry.getKey();
			Row row = sheet.createRow(i + 1);
			OutputPojo outputObjLocal = outputMapFinal.get(resellecrCompanyNameFinal);
			row.createCell(0).setCellValue(outputObjLocal.getResellerCompanyName());
			row.createCell(1)
					.setCellValue(processedObj.getCmpIngramMicroCostByResellerCompName(resellecrCompanyNameFinal));
			row.createCell(2)
					.setCellValue(processedObj.getCmpResellerCostByResellerCompName(resellecrCompanyNameFinal));
			row.createCell(3).setCellValue(outputObjLocal.getPartnerCenterIngramCost());
			row.createCell(4).setCellValue(outputObjLocal.getParnerCenterResellerCost());

			i++;
		}

		FileOutputStream fOut = new FileOutputStream(urloutput);
		excelWookBook.write(fOut);
		fOut.close();

	}

}
