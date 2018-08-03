package com.hcr.hadoop.mapreducer;

import java.time.LocalDateTime;

public class DateUtils {
	public static String toDateString(LocalDateTime ldt){
		return ldt.getYear() + "年" + ldt.getMonthValue() + "月" + ldt.getDayOfMonth() + "日"
				+ ldt.getHour() + "时" + ldt.getMinute() + "分" + ldt.getSecond() + "秒";

	}

}
