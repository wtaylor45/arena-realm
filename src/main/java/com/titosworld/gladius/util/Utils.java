package com.titosworld.gladius.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.titosworld.gladius.Reference;

public class Utils {
	private static Logger l;
	
	public static Logger getLogger() {
		if(l == null) {
			l = LogManager.getFormatterLogger(Reference.MODID);
		}
		return l;
	}
}
