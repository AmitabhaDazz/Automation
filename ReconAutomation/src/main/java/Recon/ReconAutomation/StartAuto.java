package Recon.ReconAutomation;

import java.io.File;

import DirOps.DumpDirOperations;

public class StartAuto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="C:\\Users\\Amitabha Das\\Desktop\\New folder\\testx.csv";
		System.out.println("The directory to read the downloaded files is - : " +url);
		//System.out.println("The directory to read the RAW files is - : " + args[1]); 
		//System.out.println("The directory to read the CMP files is - : " + args[2]);
		DumpDirOperations dumpDirOperations = new DumpDirOperations(url);
		 // dumpDirOperations.copyFilesAtRaw((Map<String, FileObjectPojo>)dumpDirOperations.getFilesToCopyRaw(), new File(args[1]));
		  //dumpDirOperations.copyFilesAtCmp((Map<String,FileObjectPojo>)dumpDirOperations.getFilesToCopyCmp(), new File(args[2]));
		 
//		System.out.println("Raw files : "+dumpDirOperations.getFilesToCopyRaw());
//		System.out.println("CMP Files : "+dumpDirOperations.getFilesToCopyCmp());
		

	}

}
