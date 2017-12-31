package app.controller;

public class TestController {
	
	private static long traceCounterLong = 0;
	private static boolean tracelFlagBool = false;
	private static boolean checkdbFlagBool = false;

	public static long getTraceCounterLong() {
		return traceCounterLong;
	}

	public static void setTraceCounterLong(long traceCounterLong) {
		TestController.traceCounterLong = traceCounterLong;
	}

	public static boolean isTracelFlagBool() {
		return tracelFlagBool;
	}

	public static void setTracelFlagBool(boolean tracelFlagBool) {
		TestController.tracelFlagBool = tracelFlagBool;
	}

	public static boolean isCheckdbFlagBool() {
		return checkdbFlagBool;
	}

	public static void setCheckdbFlagBool(boolean checkdbFlagBool) {
		TestController.checkdbFlagBool = checkdbFlagBool;
	}

	public static void traceCounter(String messageString) {
		if(tracelFlagBool)
			System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + messageString);
	}
	
}
