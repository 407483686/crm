package CRM.tool;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
	public static Timestamp changeToTimestamp(String time) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf1.parse(time);
		return new Timestamp(date.getTime());
	}
	
	public static Date changeToDate(String time) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf1.parse(time);
		return date;
	}
}
