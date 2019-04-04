package DirOps;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.ensim.automation.reconsilation.utils.FileObjectPojo;


public class DumpDirOperations {

	
	  private File dumpdir; 
	  private Map filesToCopyRaw; 
	 private Map filesToCopyCmp;
	 
	 public DumpDirOperations(){
		 this.dumpdir = new File("F:\\Automation\\dump");
	 }
	 
	  public DumpDirOperations(File dumpFileDir) { 
		  this.dumpdir = dumpFileDir; 
		 }
	  
	  public DumpDirOperations(String dumpFileDir) { 
		  this.dumpdir = new File(dumpFileDir); 
		  processFiles(); 
		  }
	  
	  private void processFiles() { 
		  //filesToCopyRaw = new HashMap<String,FileObjectPojo>();
	  filesToCopyCmp = new HashMap<String, FileObjectPojo>();
	 for(File file: getAllFiles())
	 {
		 FileObjectPojo fileObjectPojo = new FileObjectPojo(); // System.out.println(file.getName());
	  fileObjectPojo.setName(file.getName());
	  fileObjectPojo.setAbsolutePath(file.getAbsolutePath()); String[] splitResult = file.getName().split("_"); if(splitResult.length>1) {
	 fileObjectPojo.setCountryName(splitResult[0]); //TODO call the Continent name Set Class
	   if(splitResult[0].equalsIgnoreCase("US") ||
	  splitResult[0].equalsIgnoreCase("CA"))
	  fileObjectPojo.setContinentName("America"); else
	  if(splitResult[0].equalsIgnoreCase("NL"))
	  fileObjectPojo.setContinentName("Europe");
	  fileObjectPojo.setFileTypeOfAutomation(splitResult[1]);
	  if(fileObjectPojo.getFileTypeOfAutomation().equalsIgnoreCase("raw"))
	  filesToCopyRaw.put(fileObjectPojo.getName(), fileObjectPojo); } else
	  filesToCopyCmp.put(fileObjectPojo.getName(), fileObjectPojo); } }
	  
	  private File[] getAllFiles() { return dumpdir.listFiles(); }
	  
	  public Map getFilesToCopyRaw() { return filesToCopyRaw; }
	  
	  public Map getFilesToCopyCmp() { return filesToCopyCmp; }
	 
	  public void copyFilesAtRaw(Map<String,FileObjectPojo> map, FiledestinationDir) 
	  { 
		  try 
		  { 
			  for(Map.Entry<String, FileObjectPojo> file : map.entrySet()) 
		  { 
				  File continentDir = new File(destinationDir.getPath()+File.separator+file.getValue().getContinentName()); if(!continentDir.exists()){
	  if(continentDir.mkdirs()) System.out.println(" Continent "+file.getValue().getContinentName() + " Created..."); } File countryDir = new
	  File(destinationDir.getPath()+File.separator+
	  file.getValue().getContinentName()+ File.separator+
	  file.getValue().getCountryName()); if(!countryDir.exists()){
	  if(countryDir.mkdirs()) System.out.println(" Continent "+
	  file.getValue().getCountryName() + " Created..."); }
	  Files.copy(Paths.get(file.getValue().getAbsolutePath()),
	  Paths.get(destinationDir.getPath()+File.separator+
	  file.getValue().getContinentName()+ File.separator+
	  file.getValue().getCountryName()+ File.separator+ file.getValue().getName()),
	  StandardCopyOption.REPLACE_EXISTING);
	  ((FileObjectPojo)getFilesToCopyRaw().get(file.getKey())).setAbsolutePath(
	  destinationDir.getPath()+File.separator+ file.getValue().getContinentName()+
	  File.separator+ file.getValue().getCountryName()+ File.separator+
	  file.getValue().getName()); 
	  }
	}catch (IOException e) { 
		// TODO: handle
	  exception System.err.println(e.getCause()); } 
		  }
	 
	  public void copyFilesAtCmp(Map<String,FileObjectPojo> map, FiledestinationDir) {
		  try {
			  for(Map.Entry<String, FileObjectPojo> file :map.entrySet()) 
			  {
				  Files.copy(Paths.get(file.getValue().getAbsolutePath()),
	  Paths.get(destinationDir.getPath() + File.separator+
	  file.getValue().getName()), StandardCopyOption.REPLACE_EXISTING);
	  ((FileObjectPojo)getFilesToCopyCmp().get(file.getKey())).setAbsolutePath(
	  destinationDir.getPath() + File.separator+ file.getValue().getName()); 
	  }
	  }catch (Exception e) { // TODO: handle exception
	  System.err.println(e.getCause()); } }
	 
}
