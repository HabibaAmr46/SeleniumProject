package utils;

public class ExcelUtilsDemo {
	
	public static void main(String[] args) {
		ExcelUtils excel=new ExcelUtils("", "");
		excel.getCellDataString(0,1);
		excel.getRowCount();
	}

}
