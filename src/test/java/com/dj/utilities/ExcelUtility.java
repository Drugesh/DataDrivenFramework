package com.dj.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.record.formula.SheetNameFormatter;
import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	public ExcelUtility(String path) {

		try {
			FileInputStream fis=new FileInputStream(path);
			workbook= new XSSFWorkbook(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public int getRowCount(String sheetname){
		sheet=workbook.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum()+1;
		return rowcount;
	}
	public int getCellCount(String sheetname){
		
		sheet=workbook.getSheet(sheetname);
		XSSFRow row=sheet.getRow(0);
		int count= row.getLastCellNum();
		
		return count;
		
	}
	
	
	public String getCellData(String sheetname,int rownum,int cellnum){
		
		sheet=workbook.getSheet(sheetname);
		XSSFRow row=sheet.getRow(rownum);
		XSSFCell cell=row.getCell(cellnum);
		String data;
		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
		data=cell.getStringCellValue().toString();
	
		return data;
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			data=String.valueOf(cell.getNumericCellValue());
			
			return data;
			
		}
		else{
			data="";
			return data;
		}
	}
	

}
