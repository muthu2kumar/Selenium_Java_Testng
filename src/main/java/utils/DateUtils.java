package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class DateUtils {

	/**
	 * nextOrSame - returns 'date' field of next or immediate 'day of week' as provided in parameter
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate nextOrSame(String dayOfWeek) {
		Calendar date1 = Calendar.getInstance();
		LocalDate ld = LocalDate.of(date1.get(1),date1.get(2)+1,date1.get(5));
		switch(dayOfWeek) {
		case "MONDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
			break;
		}
		case "TUESDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
			break;
		}
		case "WEDNESDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
			break;
		}
		case "THURSDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
			break;
		}
		case "FRIDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
			break;
		}
		case "SATURDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
			break;
		}
		case "SUNDAY":{
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
			break;
		}
		default:{
			
		}
		}
		
		return ld;
	}
	
	/**
	 * next - returns 'date' field of next 'day of week' as provided in parameter
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate next(String dayOfWeek) {
		Calendar date1 = Calendar.getInstance();
		LocalDate ld = LocalDate.of(date1.get(1),date1.get(2)+1,date1.get(5));
		switch(dayOfWeek) {
		case "MONDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			break;
		}
		case "TUESDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
			break;
		}
		case "WEDNESDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
			break;
		}
		case "THURSDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
			break;
		}
		case "FRIDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
			break;
		}
		case "SATURDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
			break;
		}
		case "SUNDAY":{
			ld = ld.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
			break;
		}
		default:{
			
		}
		}
		
		return ld;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar date1 = Calendar.getInstance();
		LocalDate ld = LocalDate.of(date1.get(1),date1.get(2)+1,date1.get(5));
		return ld.getMonthValue();
	}
}
