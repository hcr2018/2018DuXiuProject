package com.hcr.hadoop.mapreducer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.hadoop.io.WritableComparable;

/**
 * 时间格式化包装类
 * @author cjx
 *
 */
public class DateBean implements WritableComparable<DateBean> {
	
	private LocalDateTime ldt;
	private DateTimeFormatter dtf=DateTimeFormatter.ISO_DATE_TIME;
	
	
	
	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}

	public DateTimeFormatter getDtf() {
		return dtf;
	}

	public void setDtf(DateTimeFormatter dtf) {
		this.dtf = dtf;
	}
	
	

	
	
	

	public DateBean(LocalDateTime ldt) {
		super();
		this.ldt = ldt;
		
	}

	@Override
	public String toString() {
		return "DateBean [ldt=" + ldt + "]";
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		// TODO Auto-generated method stub
		String str = input.readUTF();
		ldt = dtf.parse(str, LocalDateTime::from);
	}

	@Override
	public void write(DataOutput output) throws IOException {
		// TODO Auto-generated method stub
		String format = dtf.format(ldt);
		output.writeBytes(format);
	}
	
	//降序排序
	@Override
	public int compareTo(DateBean o) {
		// TODO Auto-generated method stub
		return -this.getLdt().compareTo(o.getLdt());
	}
	

}
