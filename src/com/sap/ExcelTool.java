package com.sap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTool {

	double[] myArray = new double[8];
	List<Double> list = new ArrayList<Double>();
//	List<Map<String, double>> siteValue = new 
//	Map<String, Double> siteValueMap = new HashMap<String, Double>();
	List<Double> difValueList = new ArrayList<Double>();
	List<String> siteList = new ArrayList<String>();

	public int[] updateXLSXFile(String inputFileName, String outputFileName) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(inputFileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFWorkbook test = new XSSFWorkbook();
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		int r = 0;// row index
		int[] sumArray = new int[4];
		Iterator rows = sheet.rowIterator();
		row = (XSSFRow) rows.next();
		
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			for(int i = 0; i < 8; i++){
				cell = row.getCell(i+3);
				myArray[i] = cell.getNumericCellValue();
			}
			ArraySort.toBig(myArray);
			double medium = (myArray[3]+myArray[4])/2;
			list.add(medium);
			cell = row.createCell(11);
			cell.setCellValue(medium);
			cell = row.getCell(1);
			double cellTelecom = cell.getNumericCellValue();
			System.out.println("telecom = " + cellTelecom);
			if(medium > cellTelecom){
				sumArray[0]++;
				double dif = medium - cellTelecom;
				cell = row.getCell(0);
				String site = cell.getStringCellValue();
				difValueList.add(dif);
				siteList.add(site);
			}else if(medium == cellTelecom){
				sumArray[1]++;
			}else{
				sumArray[2]++;
				
			}
			r++;
			System.out.println();
		}
		ExcelFileToRead.close();
		sumArray[3] = r;
		r = 0;
		FileOutputStream fileOut = new FileOutputStream(outputFileName);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		return sumArray;

	}

	public void writeXLSXFile(List<String> fileNameList, List<int[]> resultList) throws IOException {

		String excelFileName = "C:/Users/I324402/Desktop/excelfiles-1/Test.xlsx";// name																				
		String sheetName = "Sheet1";// name of sheet
		String sheetName2 = "Sheet2";// name of sheet2
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("FileName");
		cell = row.createCell(1);
		cell.setCellValue("Bad");
		cell = row.createCell(2);
		cell.setCellValue("Equal");
		cell = row.createCell(3);
		cell.setCellValue("Good");
		cell = row.createCell(4);
		cell.setCellValue("TotalNumber");
		// iterating r number of rows
		for (int r = 1; r <= resultList.size(); r++) {
			row = sheet.createRow(r);
			// iterating c number of columns
			cell = row.createCell(0);
			cell.setCellValue(fileNameList.get(r - 1));
			for (int c = 1; c < 5; c++) {
				cell = row.createCell(c);
				cell.setCellValue(resultList.get(r - 1)[c - 1]);
			}
		}
		XSSFSheet sheet2 = wb.createSheet(sheetName2);
		XSSFRow row2;
		XSSFCell cell2;
		row2 = sheet2.createRow(0);
		cell2 = row2.createCell(0);
		cell2.setCellValue("Site");
		cell2 = row2.createCell(1);
		cell2.setCellValue("DIfValue");
		for(int j = 1; j <= siteList.size(); j++){		
			row2 = sheet2.createRow(j);
			cell2 = row2.createCell(0);
			cell2.setCellValue(siteList.get(j-1));
			cell2 = row2.createCell(1);
			cell2.setCellValue(difValueList.get(j-1));
		}
		
//		cell = row.createCell(1);
//		cell.setCellValue("Greater");
//		cell = row.createCell(2);
		
		
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
	}

}