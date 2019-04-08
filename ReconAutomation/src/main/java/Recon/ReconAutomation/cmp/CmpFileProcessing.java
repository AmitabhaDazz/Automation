package Recon.ReconAutomation.cmp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CmpFileProcessing {
	private Map<String, List<String>> cmpfileDataColl=new HashMap<String, List<String>>();
	public Map<String, List<String>> getCmpfileDataColl() {
		return cmpfileDataColl;
	}
	public void setCmpfileDataColl(Map<String, List<String>> cmpfileDataColl) {
		this.cmpfileDataColl = cmpfileDataColl;
	}
	
	private String url;
	BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
   
    
	
	  public CmpFileProcessing() {
	  
	  
	  }
	 
	 
	 public CmpFileProcessing(String x) { // TODO Auto-generated constructor stub
	  x=this.url; }
	


		public void readFile(String ur) throws IOException {
			 cmpfileDataColl=new HashMap<String, List<String>>();
            try {
				br = new BufferedReader(new FileReader(ur));
				 //CmpFilePojo cmp= new CmpFilePojo();
					while ((line = br.readLine()) != null) {
					 
					 	String[] country = line.split(cvsSplitBy);
					 	List<String> namesList = Arrays.asList( country[1], country[5], country[19], country[20]);
		                
		              //  System.out.println(country[1]+"	,"+ country[19]+"	,"+country[5]+"	,"+ country[20]);
		               
					 	cmpfileDataColl.put(country[5], namesList);
					 	this.setCmpfileDataColl(cmpfileDataColl);
						
				 } 
            }catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
			
				
				/*String sheetName = "AP Recon";
				XSSFWorkbook workbook;
			
				try {
					FileInputStream fileInputStreamCmpFile=new FileInputStream(new File(ur));
					workbook = new XSSFWorkbook(fileInputStreamCmpFile);
					System.out.println("File:" + workbook.getSheetIndex(sheetName));
					
		              XSSFSheet sheet = workbook.getSheetAt(1);
		              ArrayList<CmpFilePojo> cmpdataList = new ArrayList<CmpFilePojo>();
		             // Iterator<Row> rowIterator = sheet.iterator();
		              for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++) {
		            	  CmpFilePojo e= new CmpFilePojo();
		            	  cmpdataList.add(e);
		              }
		              int x=0;
		              for(CmpFilePojo emp: cmpdataList){
		            	  x++;
		                  System.out.println("ID:"+emp.getResellerCompanyName()+" firstName:"+emp.getSubscriptionId());
		                 
		              }
		              System.out.println(x);
		              fileInputStreamCmpFile.close();
				}
				catch(Exception e) {
					e.printStackTrace();				
				}
		}*/
		  
}
