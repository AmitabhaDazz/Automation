
 package Recon.ReconAutomation.raw;

import java.io.File;

import java.io.FileInputStream;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import Recon.ReconAutomation.cmp.CmpFilePojo;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class RawFileProcessing{
private String url;
XSSFWorkbook workbook;
public RawFileProcessing() {
            
}

 public RawFileProcessing(String x) {

            // TODO Auto-generated constructor stub

  x=this.url;
 }

public void readFile(String ur) {
String sheetName = "AP Recon";
XSSFWorkbook workbook;
try {

                    FileInputStream fileInputStreamRawFile=new FileInputStream(new File(ur));

                    workbook = new XSSFWorkbook(fileInputStreamRawFile);

                    System.out.println("File:" + workbook.getSheetIndex(sheetName));

                    XSSFSheet sheet = workbook.getSheetAt(0);

                      ArrayList<RawFilePojo> rawdataList = new ArrayList<RawFilePojo>();

                     // Iterator<Row> rowIterator = sheet.iterator();

                      for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++) {

                          RawFilePojo e= new RawFilePojo();

                          rawdataList.add(e);

                      }

                      int x=0;

                      for(RawFilePojo emp: RawdataList){

                          x++;

                          System.out.println("ID:"+emp.getSubscriptionId()+" firstName:"+emp.getPostTaxTotal());

                         }

                      System.out.println(x);

                      fileInputStreamRawFile.close();

                     

                        

            /*

             * while (rowIterator.hasNext()) { Cell cell = cellIterator.next(); CmpFilePojo

             * data=new CmpFilePojo(); cmpdataList.add(data); }

             */

                      

            /*

             * for(CmpFilePojo dt: cmpdataList){ System.out.println(""); }

             */


    fileInputStreamCmpFile.close();

                }

                catch(Exception e) {

                    e.printStackTrace();                

                }

        }

          

}

 