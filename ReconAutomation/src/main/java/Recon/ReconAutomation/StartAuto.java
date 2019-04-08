package Recon.ReconAutomation;

import java.io.File;
import Recon.ReconAutomation.cmp.CmpFileProcessing;

public class StartAuto {

	public static void main(String[] args) {
		
		String url = "E:\\New folder\\a.xlsm";

		System.out.println("The directory to read the RAW files is - : " + url);

		 CmpFileProcessing cmpFileProcessing = new CmpFileProcessing(url);

		
		cmpFileProcessing.readFile(url);

	System.out.println("Keep Calm and Code");
	}

}
