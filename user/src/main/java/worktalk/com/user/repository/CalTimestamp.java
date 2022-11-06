package worktalk.com.user.repository;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalTimestamp {
	
	public Timestamp calDate(Timestamp ts, int num) {
		Timestamp calDate = ts;
		Calendar cal = Calendar.getInstance();
		cal.setTime(calDate);
		cal.add(Calendar.DATE, num);
		calDate.setTime(cal.getTime().getTime());
		
		return calDate;
	}
	public Timestamp calHour(Timestamp ts, int num) {
		Timestamp calHour = ts;
		Calendar cal = Calendar.getInstance();
		cal.setTime(calHour);
		cal.add(Calendar.HOUR, num);
		calHour.setTime(cal.getTime().getTime());
		
		return calHour;
	}

}
