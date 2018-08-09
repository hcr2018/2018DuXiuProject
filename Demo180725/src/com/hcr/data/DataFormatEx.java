package com.hcr.data;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class DataFormatEx {
	@Test
	public void testDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		// LocalDate date = LocalDate.now();
		 Date date = new Date();
		String format = simpleDateFormat.format(date);
		System.out.println(format);
		
		DateFormat df = DateFormat.getTimeInstance(0, Locale.ENGLISH);
		String format2 = df.format(new Date());
		System.out.println(format2);
		
	}
	
	@Test
	public void testNumber(){
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setRoundingMode(RoundingMode.HALF_UP);
		String str = nf.format(123456789.2555559);
		System.out.println(str);
		
		System.out.println("************************");
		NumberFormat ci = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
		String format = ci.format(123456.55550008);
		System.out.println(format);
		
		
	}
	

}
