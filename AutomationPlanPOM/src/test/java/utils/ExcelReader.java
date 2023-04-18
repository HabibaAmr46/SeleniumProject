package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	
	// This method handles the excel - opens it and reads the data from the
		// respective cells using a for-loop & returns it in the form of a string array
		public  Object[][] getExcelData() throws IOException {
			Object[][] data = null;
			try {
				DataFormatter dataformat=new DataFormatter();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Excel\\register.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				XSSFRow row = sheet.getRow(0);
				int noOfRows = sheet.getPhysicalNumberOfRows();
				int noOfCols = row.getLastCellNum();
				data = new Object[noOfRows - 1][noOfCols];

				for (int i = 1; i < noOfRows; i++) {
					row = sheet.getRow(i); 
					for (int j = 0; j < noOfCols; j++) {
						data[i - 1][j] =dataformat.formatCellValue(row.getCell(j));
								
					}
				}
				workbook.close();
				fis.close();
			} catch (Exception e) {
				System.out.println("The exception is: " + e.getMessage());
			}
			return data;
			
		}
		
		public  Object[][] getExcelData2() throws IOException {
			Object[][] data = null;
			try {
				DataFormatter dataformat=new DataFormatter();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Excel\\register.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet("Sheet2");
				XSSFRow row = sheet.getRow(0);
				int noOfRows = sheet.getPhysicalNumberOfRows();
				int noOfCols = row.getLastCellNum();
				data = new Object[noOfRows - 1][noOfCols];

				for (int i = 1; i < noOfRows; i++) {
					row = sheet.getRow(i); 
					for (int j = 0; j < noOfCols; j++) {
						data[i - 1][j] =dataformat.formatCellValue(row.getCell(j));
								
					}
				}
				workbook.close();
				fis.close();
			} catch (Exception e) {
				System.out.println("The exception is: " + e.getMessage());
			}
			return data;
			
		}

}
