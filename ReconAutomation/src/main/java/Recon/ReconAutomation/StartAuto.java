package Recon.ReconAutomation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import OutputAutomation.ExcelPrint;
import OutputAutomation.OutputPojo;
import OutputAutomation.OutputPojoforReseller;
import Recon.ReconAutomation.cmp.CmpFilePojo;
import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFilePojo;
import Recon.ReconAutomation.raw.RawFileProcessed;
import Recon.ReconAutomation.raw.RawFileProcessing;


public class StartAuto {
	public static void main(String[] args) throws IOException {

		String urlcmp = "E:\\New folder\\dump\\Partner Reconciliation AzureTR custom period Feb 2, 2019 - Mar 1, 2019.csv";
		//System.out.println("The directory to read the CMP files is - : " + urlcmp);
		String urlraw = "E:\\New folder\\dump\\new.xlsx";
		//Raw file processing

		/*
		 * RawFileProcessing rawFileProcessing = new RawFileProcessing();
		 * rawFileProcessing.readFile(urlraw); RawFileProcessed processedRawObj= new
		 * RawFileProcessed(rawFileProcessing.getRawFilePojoList());
		 * processedRawObj.createMapBySubscriptionID(); for (Map.Entry<String,
		 * List<RawFilePojo>> entry : processedRawObj.getSubscriptionIDMap().entrySet())
		 * { String subId= entry.getKey(); System.out.println("For " + subId +
		 * ", ResellerCostTotal is = " +
		 * processedRawObj.getResellerCostBySubscriptionID(subId)); }
		 */
		 

		//CMP file processing
		CmpFileProcessing cmp = new CmpFileProcessing();
		cmp.readFile(urlcmp);

		//TODO
		/*
		 * Sort the list by Subscription ID and add the total resellerCost and partnerCost
		 */
		CmpFileProcessed processedObj = new CmpFileProcessed(cmp.getCmpfilePojoList());



		processedObj.createMapBySubscriptionID();
		for (Map.Entry<String, List<CmpFilePojo>> entry : processedObj.getSubscriptionIDMap().entrySet()) {
			String subscriptionID = entry.getKey();
			OutputPojo subIdBag= new OutputPojo();
			subIdBag.setCmpIngramMicroCost(processedObj.getPartnerCostBySubscriptionID(subscriptionID).toString());
			subIdBag.setCmpResellerCost(processedObj.getResellerCostBySubscriptionID(subscriptionID).toString());
			//System.out.println("For " + subscriptionID + ", ResellerCostTotal is = " + processedObj.getResellerCostBySubscriptionID(subscriptionID) +" and Partner Cost is ...."+ processedObj.getPartnerCostBySubscriptionID(subscriptionID) +" ResellerCompany Name is "+ processedObj.getResellerCompanyNameBySubscriptionID(subscriptionID));
		}



		 OutputPojoforReseller resellerBag=new OutputPojoforReseller();


		processedObj.createMapByResellerCompanyName();
		for (Map.Entry<String, List<CmpFilePojo>> entry : processedObj.getResellerCompanyNameMap().entrySet()) {
			String resellerCompanyName = entry.getKey();
			
			//System.out.println("For " + resellerCompanyName + ", Cmp Ingram Micro Cost is = " + processedObj.getCmpIngramMicroCostByResellerCompName(resellerCompanyName) +" and Cmp Reseller Cost is ...."+ processedObj.getCmpResellerCostByResellerCompName(resellerCompanyName));
			
			  resellerBag.setCmpIngramMicroCost(processedObj.getCmpIngramMicroCostByResellerCompName(resellerCompanyName));
			  resellerBag.setResellerCompanyName(processedObj.getCmpIngramMicroCostByResellerCompName(resellerCompanyName));
			  resellerBag.setCmpResellerCost(processedObj.getCmpIngramMicroCostByResellerCompName(resellerCompanyName));
				
			/*
			 *
			 * resellerBag.setCmpIngramMicroCost(processedObj.getPartnerCostBySubscriptionID
			 * (resellerCompanyName).toString());
			 * resellerBag.setCmpResellerCost(processedObj.getResellerCostBySubscriptionID(
			 * resellerCompanyName).toString());
			 */
			 
			
		}
				
		
		
		List<OutputPojoforReseller> employeeDtoList = new ArrayList<OutputPojoforReseller>();
		ExcelPrint pt =new ExcelPrint();
		pt.writeExcel(resellerBag);
		//		System.out.println("60CE331C-4D98-4FFE-9967-95FE06616C09 total  ...."+processedObj.getSubscriptionIDTotalResellerCost("60CE331C-4D98-4FFE-9967-95FE06616C09"));



		System.out.println("Keep Calm and Code");

		System.exit(0);
	}

}
