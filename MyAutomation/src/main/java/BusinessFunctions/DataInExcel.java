package BusinessFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInExcel

{
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile() throws IOException {
		try {
			// Open the excel file
			FileInputStream file = new FileInputStream("C:\\Users\\Naveen\\Downloads\\HRM_FinalFramework\\Test.xlsx");

			// Access the excel file
			ExcelWBook = new XSSFWorkbook(file);

			// Access the sheet
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			System.out.println("ExcelWSheet is " + ExcelWSheet);
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getExcelData(int RowNum, int ColNum) {
		try {
			/*
			 * //http://www.guru99.com/all-about-excel-in-selenium-poi-jxl.html
			 * int rowCount = ExcelWSheet.getLastRowNum() -
			 * ExcelWSheet.getFirstRowNum();
			 * System.out.println("getLastRowNum is: "+
			 * ExcelWSheet.getLastRowNum());
			 * 
			 * System.out.println("getFirstRowNum is: "+
			 * ExcelWSheet.getFirstRowNum());
			 * 
			 * System.out.println("getLastRowNum()- getFir	stRowNum() is: "+
			 * rowCount);
			 * 
			 * XSSFRow row = ExcelWSheet.getRow(1);
			 * System.out.println("getRow(1) is "+row);
			 * 
			 * int row2 = row.getLastCellNum();
			 * System.out.println("row.getLastCellNum() is "+row2);
			 * 
			 * XSSFCell row3=row.getCell(0);
			 * System.out.println("row.getCell(0) is "+row3);
			 * 
			 * System.out.println("row.getCell(j).getStringCellValue() "+row.
			 * getCell(0).getStringCellValue());
			 */
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			System.out.println("Cell info is: " + Cell);

			String cellData = Cell.getStringCellValue();

			return cellData;
		} catch (Exception e) {
			// TODO: handle exception
			throw (e);
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	public static void setCellData(String SheetName, String[] dataToWrite) throws Exception {

		String filePath = "C:\\Users\\Naveen\\Downloads\\HRM_FinalFramework";
		String fileName = "Test.xlsx";

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		// Find the file extension by splitting file name in substring and
		// getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// If it is xlsx file then create object of XSSFWorkbook class
		ExcelWBook = new XSSFWorkbook(inputStream);

		// Read excel sheet by sheet name
		XSSFSheet sheet = ExcelWBook.getSheet(SheetName);

		// Get the current count of rows in excel file
		int rowCount = sheet.getLastRowNum();

		// Get the first row from the sheet
		XSSFRow row = sheet.getRow(0);

		// Create a new row and append it at last of sheet
		XSSFRow newRow = sheet.createRow(rowCount + 1);

		// Create a loop over the cell of newly created Row
		for (int i = 0; i < row.getLastCellNum(); i++) {
			// Fill data in row
			XSSFCell cell = newRow.createCell(i);

			cell.setCellValue(dataToWrite[i]);
		}

		// Close input stream
		inputStream.close();

		// Create an object of FileOutputStream class to create write data in
		// excel file
		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file
		ExcelWBook.write(outputStream);

		// close output stream
		outputStream.close();
	}
	
	// This method is to write in the Pass\Fail result in the result column
	public static void setPassOrFail(String SheetName, String result) throws Exception {

		String filePath = "C:\\Users\\Naveen\\Downloads\\HRM_FinalFramework";
		String fileName = "Test.xlsx";

		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		// Find the file extension by splitting file name in substring and
		// getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// If it is xlsx file then create object of XSSFWorkbook class
		ExcelWBook = new XSSFWorkbook(inputStream);

		// Read excel sheet by sheet name
		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		// Get the current count of rows in excel file
		// int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Get the first row from the sheet
		XSSFRow row = ExcelWSheet.getRow(1);

		// Create a new row and append it at last of sheet
		// XSSFRow newRow = sheet.createRow(rowCount + 1);
		// Create a new Cell and append it at last of sheet
		XSSFCell cell = row.createCell(row.getLastCellNum());

		cell.setCellValue(result);

		// Close input stream
		inputStream.close();

		// Create an object of FileOutputStream class to create write data in
		// excel file
		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file
		ExcelWBook.write(outputStream);

		// close output stream
		outputStream.close();
	}
}
