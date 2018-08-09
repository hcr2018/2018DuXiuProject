package com.hcr.mapreducer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{

	long upflow;
	long downflow;
	long sumflow;
	
	//����ղι��캯�������ǣ�һ��Ҫ��ʾ����һ�£������ڷ�����ʱ�����쳣
	public FlowBean(){}
	
	
	
	public FlowBean(long upflow, long downflow) {
		super();
		this.upflow = upflow;
		this.downflow = downflow;
		this.sumflow = upflow + downflow;
	}
	
	//תΪstring����
	public FlowBean(String sUpflow,String sDownflow){
		
		this(Long.valueOf(sUpflow),Long.valueOf(sDownflow));
	}
	
	
	public long getSumflow() {
		return sumflow;
	}

	public void setSumflow(long sumflow) {
		this.sumflow = sumflow;
	}

	public long getUpflow() {
		return upflow;
	}
	public void setUpflow(long upflow) {
		this.upflow = upflow;
	}
	public long getDownflow() {
		return downflow;
	}
	public void setDownflow(long downflow) {
		this.downflow = downflow;
	}

	//���л�����������ֶ���Ϣд�������
	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeLong(upflow);
		out.writeLong(downflow);
		out.writeLong(sumflow);
		
	}

	//�����л������������ж�ȡ�����ֶ���Ϣ
	@Override
	public void readFields(DataInput in) throws IOException {
		upflow = in.readLong();
		downflow = in.readLong();
		sumflow = in.readLong();
		
	}
	
	
	@Override
	public String toString() {
		return upflow + "\t" + downflow + "\t" + sumflow;
	}
	@Override
	public int compareTo(FlowBean o) {
		//�Զ��嵹��ȽϹ���
		return sumflow > o.getSumflow() ? -1:1;
		//return  (int) -(this.getSumflow()-o.getSumflow());
	}

	

}
