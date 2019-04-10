package Recon.ReconAutomation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFilePojo;
import Recon.ReconAutomation.raw.RawFileProcessed;
//import Recon.ReconAutomation.raw.RawFileProcessed;
import Recon.ReconAutomation.raw.RawFileProcessing;

public class StartAuto {
	public static void main(String[] args) throws IOException {

		/*	String url = "E:\\New folder\\testx.csv";
		System.out.println("The directory to read the CMP files is - : " + url);
		String urlcmp = "F:\\Raw\\new.xlsx";
		CmpFileProcessing cmp = new CmpFileProcessing();
		cmp.readFile(urlcmp);*/
		String urlraw ="F:\\Raw\\new.xlsx";
		System.out.println("The directory to read the RAW files is - : " + urlraw);
		RawFileProcessing rawFileProcessing = new RawFileProcessing();
		rawFileProcessing.readFile(urlraw); 
		System.out.println("Keep Calm and Code");


		RawFileProcessed processedObj = new RawFileProcessed(rawFileProcessing.getRawFilePojoList());
		processedObj.createMapBySubscriptionID();

		for (Map.Entry<String, List<RawFilePojo>> entry : processedObj.getSubscriptionIDMap().entrySet()) { 
			String subId= entry.getKey();
			System.out.println("For "  + subId + ", ResellerCostTotal is = " +  processedObj.getResellerCostBySubscriptionID(subId));
			}
	}
}
