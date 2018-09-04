package com.hcr.groupingcomparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class OrderBean implements WritableComparable<OrderBean> {
	private Text itemId;
	private DoubleWritable amount;
	
	

	public Text getItemId() {
		return itemId;
	}

	public void setItemId(Text itemId) {
		this.itemId = itemId;
	}

	public DoubleWritable getAmount() {
		return amount;
	}

	public void setAmount(DoubleWritable amount) {
		this.amount = amount;
	}
	
	

	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBean(Text itemId, DoubleWritable amount) {
		super();
		this.itemId = itemId;
		this.amount = amount;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		// TODO Auto-generated method stub
		String readUTF = input.readUTF();
		double readDouble = input.readDouble();
		this.itemId = new Text(readUTF);
		this.amount=new DoubleWritable(readDouble);
	}

	@Override
	public void write(DataOutput output) throws IOException {
		// TODO Auto-generated method stub
		output.writeUTF(itemId.toString());
		output.writeDouble(amount.get());
		
		
	}

	@Override
	public int compareTo(OrderBean o) {
		// TODO Auto-generated method stub
		int cmp = this.itemId.compareTo(o.getItemId());
		if(cmp==0){
			cmp = -this.amount.compareTo(o.getAmount());
		}
		return cmp;
	}

	@Override
	public String toString() {
	
		return itemId.toString()+"   "+amount.get();
	}
	
	
	
}
