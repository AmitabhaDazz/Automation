package Recon.ReconAutomation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class configFileProcess {
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	public String readFileCmp() {
		String ar=null;
		
		 ar	= rb.getString("cmp");
	    return ar;
	}
	public String readFileRaw() {
		String ar=null;
		
		 ar = rb.getString("raw");
	    return ar;
	}
	
}