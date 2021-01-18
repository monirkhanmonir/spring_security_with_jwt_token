package com.excellenceict.eprescription.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class DateUtils {
	private DateUtils() {
		
	}
	public static Integer getYearFromCurrentDate() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static Integer getYearFromDate(Date date) {
		Calendar cal  =Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	 public static Date getExpirationTime(Long expireHour) {
		 	Date now =new Date();
		 	Long expireInMilis = TimeUnit.MINUTES.toMillis(expireHour);
		 	return new Date(expireInMilis + now.getTime());
	 }
	

}
