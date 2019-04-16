package OutputAutomation;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class  ExcelWriter{
	List<OutputPojo> employees= new ArrayList<OutputPojo>();
public void printExcel(String filepath) {
	
	if(filepath!=null && !"".equals(filepath.trim()))
	{
		try
		{
		
			XSSFWorkbook excelWookBook = new XSSFWorkbook();

			
			CreationHelper createHelper = excelWookBook.getCreationHelper();

		
			Sheet sheet = excelWookBook.createSheet("Variance per reseller level");

			
			/*/ First create header row. /*/
			Row headerRow = sheet.createRow(0);

			headerRow.createCell(0).setCellValue("Resellercompanyname");
			headerRow.createCell(1).setCellValue("CMP IMcost");
			headerRow.createCell(2).setCellValue("CMP Reseller cost");
			headerRow.createCell(3).setCellValue("Partner center IMcost");
			headerRow.createCell(4).setCellValue("Partner center Resellercost");
			headerRow.createCell(5).setCellValue("Varianceimcost");

			
			/*/ Loop for the employee dto list, add each employee data info into one row. /*/
			
			
			CaseInsensitiveMap<String, OutputPojo> outputMapFinal = new CaseInsensitiveMap<String, OutputPojo>();
			for (Map.Entry<String,OutputPojo> entry : outputMapFinal.entrySet()) {
				String resellecrCompanyNameFinal= entry.getKey();
				OutputPojo outputObjLocal = outputMapFinal.get(resellecrCompanyNameFinal);
			}
			
			
			if(employees!=null)
			{
				int size = employees.size();
				for(int i=0;i<size;i++)
				{
					OutputPojo eDto = employees.get(i);

					/*/ Create row to save employee info. /*/
					Row row = sheet .createRow(i+1);

					row.createCell(0).setCellValue(eDto.getResellerCompanyName() );
					row.createCell(1).setCellValue(eDto.getCmpIngramMicroCost());
					row.createCell(2).setCellValue(eDto.getCmpResellerCost());
					row.createCell(3).setCellValue(eDto.getPartnerCenterIngramCost() );
					row.createCell(4).setCellValue(eDto.getParnerCenterResellerCost());

				}
			}

				/* / Write to excel file / */
			FileOutputStream fOut = new FileOutputStream(filepath);
			excelWookBook.write(fOut);
			fOut.close();

			System.out.println("File " + filepath + " is created successfully. ");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
}