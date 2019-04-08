package Recon.ReconAutomation;

import java.io.File;
import java.io.IOException;

import Recon.ReconAutomation.cmp.CmpFileProcessing;
import Recon.ReconAutomation.raw.RawFileProcessing;

public class StartAuto {



	 

	public static void main(String[] args) throws IOException {

		String url = "E:\\New folder\\testx.csv";
		System.out.println("The directory to read the CMP files is - : " + url);
		String urlcmp = "F:\\Raw\\new.xlsx";
		CmpFileProcessing cmp = new CmpFileProcessing();
		cmp.readFile(urlcmp);
		System.out.println("The directory to read the RAW files is - : " + url);

		/*
		 *  CmpFileProcessing cmpFileProcessing = new CmpFileProcessing(url);
		 * 
		 * 
		 * cmpFileProcessing.readFile(url);
		 */

	/*System.out.println("Keep Calm and Code");
=======
		String url = "E:\\New folder\\a.xlsm";
		System.out.println("The directory to read the RAW files is - : " + url); 
>>>>>>> branch 'master' of https://github.com/AmitabhaDazz/Automation.git
		//System.out.println("The directory to read the CMP files is - : " + args[1]);
		/*
		 * System.out.println("The Country name is - : " + args[2]);
		 * System.out.println("The Continent - : " + args[3]);
		 */
	/*	
		CmpFileProcessing cmpFileProcessing = new CmpFileProcessing(url);
		/* cmpFileProcessing.readFile(new File(args[0]), args[2], args[3]); */
		//String path= args[0];
		//cmpFileProcessing.readFile(url); 
		

		RawFileProcessing rawFileProcessing = new RawFileProcessing(url);
		/* cmpFileProcessing.readFile(new File(args[0]), args[2], args[3]); */
		//String path= args[0];
		rawFileProcessing.readFile(url); 
		System.out.println("Keep Calm and Code");

	}

}
