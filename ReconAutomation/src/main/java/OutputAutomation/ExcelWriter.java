package OutputAutomation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Recon.ReconAutomation.cmp.CmpFileProcessed;
import Recon.ReconAutomation.cmp.CmpFileProcessing;
public class ExcelWriter {
	private static String[] columns = {"Reseller Company Name", "CMP IMcost", "CMP Reseller cost", "Partner center IMcost","Partner center Resellercost","Varianceimcost","variancein%","varianceResellercost","Variancein%"};
    private static List<OutputPojo> employees =  new ArrayList<OutputPojo>();

	// Initializing employees data to insert into the excel file
   public void printExcel() {

	   try {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Employee");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        
        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(OutputPojo employee: employees) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.cmpIngramMicroCost);

            row.createCell(1)
                    .setCellValue(employee.cmpResellerCost);


            row.createCell(3)
                    .setCellValue(employee.partnerCenterCostVarience);
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
      
			workbook.write(fileOut);
			  fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      

        // Closing the workbook
     
    }
}
