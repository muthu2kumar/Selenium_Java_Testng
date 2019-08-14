package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	public static File Log;
	public static String folderName;
	public static FileWriter w;
	
	public static File htmlLog;
	public static FileWriter hw;
	
	public static String exceptionMsg="";
	
	
	public static void createLogFile()
	{

		Log = new File(System.getProperty("user.dir")+"//Log.logs");
		try 
		{
			Log.createNewFile();
			w = new FileWriter(Log);
		} 
		catch (IOException e) 
		{
			System.out.println("Error in creating log file "+e.getMessage());
		}
	}
	
	public static void createHTMLFile()
	{
		htmlLog = new File(System.getProperty("user.dir")+"//Log.html");
		try 
		{
			htmlLog.createNewFile();
			hw = new FileWriter(htmlLog);
		} 
		catch (IOException e) 
		{
			System.out.println("Error in creating HTML file "+e.getMessage());
		}
	}
	
	public static void initHTML()
	{
		String baseDate = "<html>\r\n"+
				"  <head>\r\n"+
				"    <style>\r\n"+
				"      .contents{\r\n"+
				"      font-family: arial, sans-serif;\r\n"+
				"      border-collapse: collapse;\r\n"+
				"      font-size:16px;\r\n"+
				"      }\r\n"+
				"      tr:hover{\r\n"+
				"        background-color:#F1F1F1;\r\n"+
				"      }\r\n"+
				"      td[Exception]{\r\n"+
				"        color:red;\r\n"+
				"        text-decoration: none;\r\n"+
				"      }\r\n"+
				"    </style>\r\n"+
				"  </head>\r\n"+
				"  <body>\r\n"+
				"    <h1 style='color: #4485b8; text-align:center;'>Result</h1>\r\n"+
				"    <table class='contents' >\r\n"+
				"      <tbody>\r\n";
		try
		{
			hw.write(baseDate);
			hw.flush();
		}
		catch(Exception e)
		{
			exceptionMsg = e.getMessage();
		
		}
		
	}
	
	
	public static void log(String logs)
	{
		try
		{
			System.out.println(logs);
			//Logging to .logs file
			w.write(new Date()+"	"+logs+"\r\n");
			
			if(!exceptionMsg.isEmpty())
				w.write(exceptionMsg+"\r\n");
			
			
			w.flush();
			
			//Logging to .html file
			hw.write("<tr><td>"+new Date()+"	"+logs+"</td></tr>\r\n");
			
			if(!exceptionMsg.isEmpty())
				hw.write("<tr><td Exception>"+exceptionMsg+"</td></tr>\r\n");
			
			
			exceptionMsg = "";
			hw.flush();
		
			
		}
		catch(Exception e)
		{
			System.out.println("Error in logging "+e.getMessage());
		}
		
	}
	
	public static void closeLog()
	{
		try 
		{
			w.flush();
			w.close();
			
			hw.flush();
			hw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
