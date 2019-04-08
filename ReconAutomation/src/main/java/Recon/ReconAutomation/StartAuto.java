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
		RawFileProcessing rawFileProcessing = new RawFileProcessing(url);
		rawFileProcessing.readFile(url); 
		System.out.println("Keep Calm and Code");

	}

}
