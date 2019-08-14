package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class DateUtils {

	public static LocalDate nextOrSame(String dayOfWeek) {
		Calendar date1 = Calendar.getInstance();
		LocalDate ld = LocalDate.of(date1.get(1),date1.get(2)+1,date1.get(5));
		switch(dayOfWeek) {
		case "MONDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
			break;
		}
		
		default:{
			
		}
		}
		
		return ld;
	}
	
	public static int getCurrentMonth() {
		Calendar date1 = Calendar.getInstance();
		LocalDate ld = LocalDate.of(date1.get(1),date1.get(2)+1,date1.get(5));
		return ld.getMonthValue();
	}
}
