package util;

import java.sql.Timestamp;

public class TimestampToString {
	private TimestampToString() {}
	private static TimestampToString instance = new TimestampToString();
	public static TimestampToString getInstance() {
		return instance;
	}
	
	public String TTS(Timestamp timestamp) {
		String str = String.valueOf(timestamp);
		String MM = str.substring(5, 7);
		String DD = str.substring(8, 10);
		String hh = str.substring(11,13);
		String mm = str.substring(14,16);

		String tts = MM + "-" + DD + " " + hh + ":" + mm;
		
		return tts;
	}
}
