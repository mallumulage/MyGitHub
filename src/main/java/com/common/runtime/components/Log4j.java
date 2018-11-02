package com.common.runtime.components;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4j {

static	Logger logger = Logger.getLogger(Log4j.class);
	public static void log()
	{
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\properties\\Log4j.properties");
		
	}
}
