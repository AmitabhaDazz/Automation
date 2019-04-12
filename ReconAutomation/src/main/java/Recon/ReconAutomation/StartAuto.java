//StartAuto
package Recon.ReconAutomation;

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

import OutputAutomation.OutputPojo;
import OutputAutomation.OutputPojoforReseller;
import Recon.ReconAutomation.cmp.CmpFilePojo;
import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFilePojo;
import Recon.ReconAutomation.raw.RawFileProcessing;

public class StartAuto {
	public static void main(String[] args) throws IOException {

		String urlCmp = "F:\\Raw\\Partner Reconciliation AzureTR custom period Feb 2, 2019 - Mar 1, 2019.csv";
		System.out.println("The directory to read the CMP files is - : " + urlCmp);
		String urlraw ="F:\\Raw\\TR_Mar 2019.xlsx";
		System.out.println("The directory to read the RAW files is - : " + urlraw);
		//System.out.println(rawFileProcessing.getRawmapforpojo());
//		RawFileProcessed processedObj = new RawFileProcessed(rawFileProcessing.getRawFilePojoMap());
//		processedObj.createMapBySubscriptionID();
		RawFileProcessing rawFileProcessing = new RawFileProcessing();
		rawFileProcessing.readFile(urlraw);
		
		
		
		CmpFileProcessing cmp = new CmpFileProcessing();
		cmp.readFile(urlCmp);
		CmpFileProcessed processedObj = new CmpFileProcessed(cmp.getCmpfilePojoList());
		processedObj.createMapBySubscriptionID();
		processedObj.createMapByResellerCompanyName();
		//System.out.println(processedObj.getCmpIngramMicroCostByResellerCompName("LTS BILG.ELEKT.REKL.BIL.VE DAN.HIZM"));
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
			if(output!=null) {
				output.setParnerCenterResellerCost(rawFileProcessing.getResellerCostBySubscriptionID(subId));
				output.setPartnerCenterIngramCost(rawFileProcessing.getPosttaxtotalCostBySubscriptionID(subId));
			}else {
				System.out.println("Subscription ID not found"+ subId);
			}
		}
		
		
		CaseInsensitiveMap<String, OutputPojo> outputMapFinal = new CaseInsensitiveMap<String, OutputPojo>();
		
		
		for (Map.Entry<String,OutputPojo> entry : outputMap.entrySet()) {
			String subId= entry.getKey();
			OutputPojo outputObjLocal = outputMap.get(subId);
			if(outputMapFinal.containsKey(outputObjLocal.getResellerCompanyName())) {
				outputObjLocal = outputMap.get(subId);
				
				BigDecimal bigDecimalCmpIngramMicroCost = new BigDecimal(outputObjLocal.getCmpIngramMicroCost());
				bigDecimalCmpIngramMicroCost = bigDecimalCmpIngramMicroCost.add(new BigDecimal(processedObj.format(outputObjLocal.getCmpIngramMicroCost().toString(), bigDecimalCmpIngramMicroCost.scale() > 0 ? bigDecimalCmpIngramMicroCost.precision() : bigDecimalCmpIngramMicroCost.scale())));
				outputObjLocal.setCmpIngramMicroCost(bigDecimalCmpIngramMicroCost.toString());
				
				outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
//				System.out.println(outputObjLocal.getResellerCompanyName() + "-" + outputObjLocal.getCmpIngramMicroCost() +"-"+ outputObjLocal.getCmpResellerCost()+"-"+outputObjLocal.getPartnerCenterIngramCost()+"-"+outputObjLocal.getParnerCenterResellerCost());
			}else {
				if(rawFileProcessing.getRawmapforpojo().containsKey(subId)) {
					outputMapFinal.put(outputObjLocal.getResellerCompanyName(), outputObjLocal);
//					System.out.println(outputObjLocal.getResellerCompanyName() + "-" + outputObjLocal.getCmpIngramMicroCost() +"-"+ outputObjLocal.getCmpResellerCost()+"-"+outputObjLocal.getPartnerCenterIngramCost()+"-"+outputObjLocal.getParnerCenterResellerCost());
				}
			}
		}
		
		for (Map.Entry<String,OutputPojo> entry : outputMapFinal.entrySet()) {
			String resellecrCompanyNameFinal= entry.getKey();
			OutputPojo outputObjLocal = outputMapFinal.get(resellecrCompanyNameFinal);
			System.out.println(outputObjLocal.getResellerCompanyName() + "-" + outputObjLocal.getCmpIngramMicroCost() +"-"+ outputObjLocal.getCmpResellerCost()+"-"+outputObjLocal.getPartnerCenterIngramCost()+"-"+outputObjLocal.getParnerCenterResellerCost());
		}
		
	}
	
	
}
