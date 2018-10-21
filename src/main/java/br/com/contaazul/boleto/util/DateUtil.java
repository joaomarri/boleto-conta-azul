package br.com.contaazul.boleto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Date toDate(final String date) throws ParseException {
		return dateFormat.parse(date);
	}
	
	public long getDaysBetween(Date end, Date begin) {
		if (end == null) {
			end = new Date();
		}
		LocalDateTime lend = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
		
		LocalDateTime lbegin = LocalDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
		
		return ChronoUnit.DAYS.between(lbegin, lend);
	}
	
	

}
