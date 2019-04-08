package Recon.ReconAutomation;

import java.io.File;
import Recon.ReconAutomation.cmp.CmpFileProcessing;


import Recon.ReconAutomation.cmp.CmpFileProcessing;

public class StartAuto {

	public static void main(String[] args) {
		String url = "E:\\New folder\\a.xlsm";
		System.out.println("The directory to read the RAW files is - : " + url); 
		//System.out.println("The directory to read the CMP files is - : " + args[1]);
		/*
		 * System.out.println("The Country name is - : " + args[2]);
		 * System.out.println("The Continent - : " + args[3]);
		 */
		CmpFileProcessing cmpFileProcessing = new CmpFileProcessing(url);
		/* cmpFileProcessing.readFile(new File(args[0]), args[2], args[3]); */
		//String path= args[0];
		cmpFileProcessing.readFile(url); 
		System.out.println("Keep Calm and Code");
	}

}
