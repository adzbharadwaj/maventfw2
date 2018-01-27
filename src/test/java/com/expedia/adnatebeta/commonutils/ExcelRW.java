package com.expedia.adnatebeta.commonutils;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelRW {
	static FileInputStream fis = null;
	static Workbook wb = null;
	static Sheet s = null;

	static {
		try {
			fis = new FileInputStream("./TestData/NTData.xlsx");
			wb = WorkbookFactory.create(fis);
			s = wb.getSheet("Sheet1");

		} catch (Exception e) {

		}
	}
	
	public static int getRowCount(String testcaseName) {
		int count =0;
		for (int i=1;i<s.getPhysicalNumberOfRows();i++) {
			Row r = s.getRow(i);
			String tcn = r.getCell(1).getStringCellValue();
			String ts = r.getCell(2).getStringCellValue();
			if(tcn.equalsIgnoreCase(testcaseName)&&ts.equalsIgnoreCase("Y")) {
				count++;
			}
		}
		
		return count;
		
	}
	
	public static int getColumnCount(String tcName) {
		for (int i=1; i<s.getPhysicalNumberOfRows();i++) {
			Row r = s.getRow(i);
			String tcn = r.getCell(1).getStringCellValue();
			if(tcn.equalsIgnoreCase(tcName)) {
				return r.getPhysicalNumberOfCells() -3;
			}
		}
		return 0;
	}
	
	@DataProvider(name="tdp")
	public static  String[][] storeTestData(Method verifyRegistrationProcess){
		String testName = verifyRegistrationProcess.getName();
		int rowSize = getRowCount(testName);
		System.out.println(rowSize);
		int colsize = getColumnCount(testName);
		System.out.println(colsize);
		
		String [] [] td= new String[rowSize][colsize+1];
		int nri =0;
		for(int i =1; i<s.getPhysicalNumberOfRows();i++) {
			Row r=s.getRow(i);
			String tcn = r.getCell(1).getStringCellValue();
			String ts = r.getCell(2).getStringCellValue();
			
			if(tcn.equalsIgnoreCase(testName)&& ts.equalsIgnoreCase("Y")) {
				int nci =0;
				for(int c=3;c<r.getPhysicalNumberOfCells();c++) {
					String celldata =r.getCell(c).getStringCellValue();
					td[nri][nci++]=celldata;
				}
				td[nri++][nci]=i+"";
			}
		}
		return td;
			
	}

}
