package com.hcr.hadoop.mapreducer;

import java.time.LocalDateTime;

public class DateUtils {
	public static String toDateString(LocalDateTime ldt){
		return ldt.getYear() + "��" + ldt.getMonthValue() + "��" + ldt.getDayOfMonth() + "��"
				+ ldt.getHour() + "ʱ" + ldt.getMinute() + "��" + ldt.getSecond() + "��";

	}

}
