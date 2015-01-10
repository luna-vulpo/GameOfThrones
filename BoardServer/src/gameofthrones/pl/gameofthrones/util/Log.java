package pl.gameofthrones.util;

import java.io.PrintStream;

public final class Log {

	private static PrintStream logOutput = System.out;
	private static final String DELIMETER = " | ";
	
	synchronized
	public static void setLogOutput(PrintStream ps){
		logOutput = ps;
	}
	
	synchronized
	private static void log(String level, String tag, String message, Exception e){
		final String newTag = String.format("%-20s",tag.length() > 20 ? tag.substring(0, 20) : tag);
		
		logOutput.append(level).append(DELIMETER)
		.append(newTag).append(DELIMETER)
		.append(message).append("\n")
		.flush();
		
		if(e != null){
			logOutput.append(level).append(DELIMETER).append(newTag).append(DELIMETER).append("\n");
			e.printStackTrace(logOutput);
		}
		
	}
	
	public static void v(String tag, String message){
		log("V",tag,message, null);
	}
	
	public static void i(String tag, String message){
		log("I",tag,message, null);
	}
	
	public static void w(String tag, String message){
		log("W",tag,message, null);
	}

	public static void d(String tag, String message){
		log("D",tag,message, null);
	}
	
	public static void e(String tag, String message){
		log("E",tag,message, null);
	}

	public static void e(String tag, String message, Exception e){
		log("E",tag,message, e);
	}
	
	public static void wtf(String tag, String message){
		log("X",tag,message, null);
	}

	public static void wtf(String tag, String message, Exception e){
		log("X",tag,message, e);
	}
	
}
