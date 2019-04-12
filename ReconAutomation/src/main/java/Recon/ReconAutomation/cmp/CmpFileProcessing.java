//cmpfileprocessing

package Recon.ReconAutomation.cmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CmpFileProcessing {
	
	private List<CmpFilePojo> cmpfilePojoList = new ArrayList<CmpFilePojo>();
		
	public CmpFileProcessing() {
	}
	 
	 
	 public void readFile(String filePath) throws IOException {
			 String line = ""; 
			 int rownum =0;
			 CmpFilePojo cmpfilepojo = new CmpFilePojo();
			 try {
			 BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			 while((line = br.readLine()) != null){
				  String[] b = line.split(",");
				  if(line.length()>60 && b[1]!=null && !b[1].equalsIgnoreCase("ResellerCompanyName") && b[6]!=null && b[20]!=null && b[21]!=null ) {
					  if(rownum>=1) {
						  cmpfilepojo = new CmpFilePojo(b[1],b[6],b[20],b[21]);
						  this.cmpfilePojoList.add(cmpfilepojo);
					  }
					  
				  }else {
					  System.out.println("Skipped...." + rownum);
				  }
				  rownum++;
				  this.setCmpfilePojoList(cmpfilePojoList);
			}
			 br.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			 
	 }
	
	public List<CmpFilePojo> getCmpfilePojoList() {
		return cmpfilePojoList;
	}


	public void setCmpfilePojoList(List<CmpFilePojo> cmpfilePojoList) {
		this.cmpfilePojoList = cmpfilePojoList;
	}
}
		

		  

