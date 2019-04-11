package OutputAutomation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Variant;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPrint {
	
	
	public void writeExcel(OutputPojoforReseller resellerBag) {
		
			
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet employeeSheet = workbook.createSheet("Datatypes in Java");
			Row headerRow = employeeSheet.createRow(0);
			headerRow.createCell(0).setCellValue("ResellerCompanyName");
			headerRow.createCell(1).setCellValue("cmpIngramMicroCost");
			headerRow.createCell(2).setCellValue("cmpResellerCost");
			
			/*
			 * if(resellerBag!=null) { int size = resellerBag.size(); for(int
			 * i=0;i<size;i++) { OutputPojoforReseller eDto = resellerBag.get(i); Row row =
			 * employeeSheet.createRow(i+1);
			 * row.createCell(0).setCellValue(eDto.getResellerCompanyName());
			 * row.createCell(1).setCellValue(eDto.getCmpIngramMicroCost());
			 * row.createCell(2).setCellValue(eDto.getCmpResellerCost()); }}
			 */
			try {
	            FileOutputStream outputStream = new FileOutputStream("E:\\New folder\\dump\\output.xls");
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}
	}



