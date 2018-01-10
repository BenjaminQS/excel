package com.sap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String args[]) throws IOException{
		List<int[]> sumList = new ArrayList<int[]>();
		List<Map<String, String>> siteValueMapList = new ArrayList<Map<String,String>>();
		int[] intArray;
		ExcelTool eT = new ExcelTool();
		GetFileName gFN = new GetFileName();
		List<String> nameList = gFN.getNameList("C:/Users/I324402/Desktop/excelfiles");
		Iterator<String> iter = nameList.iterator();
		while(iter.hasNext()){
			String name = iter.next();
			System.out.println(name);
			intArray = eT.updateXLSXFile("C:/Users/I324402/Desktop/excelfiles/"+name, "C:/Users/I324402/Desktop/excelfiles-1/"+name);
			sumList.add(intArray);
		}
		for(int i = 0; i < sumList.size(); i++){
			System.out.println("sum list:"+sumList.get(i)[0]+sumList.get(i)[1]+sumList.get(i)[2]+sumList.get(i)[3]);
		}
//		eT.updateXLSXFile();
		eT.writeXLSXFile(nameList,sumList);
	}
}