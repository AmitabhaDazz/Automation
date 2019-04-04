package Recon.ReconAutomation;

public class StartAutomation {

	public static void main(String args[]) {
System.out.println("Starting Automation Please wait until it complete!!!!");
		
		//Steps TODO
		
		/*
		 * Call the java file to see the directory structure is correct or not.
		 */
		
		  System.out.println("The directory to read the downloaded files is - : " +args[0]);
		  System.out.println("The directory to read the RAW files is - : " +args[1]);
		  System.out.println("The directory to read the CMP files is - : " + args[2]);
		 
		/*
		 * DumpDirOperations dumpDirOperations = new DumpDirOperations(args[0]);
		 * dumpDirOperations.copyFilesAtRaw((Map<String,
		 * FileObjectPojo>)dumpDirOperations.getFilesToCopyRaw(), new File(args[1]));
		 * dumpDirOperations.copyFilesAtCmp((Map<String,
		 * FileObjectPojo>)dumpDirOperations.getFilesToCopyCmp(), new File(args[2]));
		 */
//		System.out.println("Raw files : "+dumpDirOperations.getFilesToCopyRaw());
//		System.out.println("CMP Files : "+dumpDirOperations.getFilesToCopyCmp());
		
		
		/*
		 * Iterate through each file of Raw folder
		 */
		/*
		 * Map<String, FileObjectPojo> rawfilemap = (Map<String,
		 * FileObjectPojo>)dumpDirOperations.getFilesToCopyRaw(); for(Map.Entry<String,
		 * FileObjectPojo> file : rawfilemap.entrySet()) { RawFileProcessing rfp = new
		 * RawFileProcessing(file.getValue().getAbsolutePath()); }
		 */
		
		/*
		 * Call the java file to parse the CMP file 
		 * */
	}
}
