package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public static void main(String[] args) {
		//getRowCount();
		getCellDataString(0,0);
		getCellDataNumber(1,1);
	}
	public ExcelUtils(String excelpath,String sheetName) {
		try {
			workbook = new XSSFWorkbook("C:\\Users\\hmohammed25\\OneDrive - DXC Production\\Documents\\ja\\AutomationPlanPOM\\Excel\\data.xlsx");
			sheet=workbook.getSheet("Sheet1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getRowCount()
	{
	
		try {
			int rowCount=sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows: "+rowCount);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void getCellDataString(int rownum,int colnum)
	{
		try {
			String cellData=sheet.getRow(rownum).getCell(colnum).getStringCellValue();
			System.out.println(cellData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void getCellDataNumber(int rowNum,int colNum)
	{
		try {
			double cellData=sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
