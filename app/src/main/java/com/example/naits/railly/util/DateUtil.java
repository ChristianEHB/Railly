package com.example.naits.railly.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String timeStampToDate(long timeStamp) {
		//Time * 1000 because it's in ms
		Timestamp stamp = new Timestamp(timeStamp * 1000);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(new Date(stamp.getTime())) ;
	}
}
