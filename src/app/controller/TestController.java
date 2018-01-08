package app.controller;

public class TestController {
	
	private static long traceCounterLong = 0;
	private static final boolean traceFlagBool = false;
	private static final boolean checkdbFlagBool = false;

	public static long getTraceCounterLong() {
		return traceCounterLong;
	}

	public static void setTraceCounterLong(long traceCounterLong) {
		TestController.traceCounterLong = traceCounterLong;
	}

	public static boolean isTraceFlagBool() {
		return traceFlagBool;
	}

	public static boolean isCheckdbFlagBool() {
		return checkdbFlagBool;
	}

	public static void traceCounter(String messageString) {
		if(traceFlagBool)
			System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + messageString);
	}
	
}
