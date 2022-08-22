package com.example.demo.common;

import java.util.regex.Pattern;

public class AppUtils {

	private static final Pattern numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return numericPattern.matcher(strNum).matches();
	}
	
	public static boolean nullOrBlank(String str) {
		if(str == null || str.isEmpty() || str.trim().isEmpty() || str.isBlank()) {
			return true;
		}
		return false;
	}
	
}
