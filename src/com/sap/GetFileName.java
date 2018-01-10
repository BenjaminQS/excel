package com.sap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFileName {
	
	List<String> results = new ArrayList<String>();

	public List<String> getNameList(String folderPath){
		File[] files = new File(folderPath).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		return results;
	}
}
