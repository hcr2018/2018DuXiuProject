package com.hcr.groupingcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ItemidGroupingComparator extends WritableComparator {
	
	public ItemidGroupingComparator() {
		// TODO Auto-generated constructor stub
		super(OrderBean.class,true);
		
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// TODO Auto-generated method stub
		OrderBean abean=(OrderBean) a;
		OrderBean bbean=(OrderBean) b;
		//将item_id相同的bean都视为相同，从而聚合为一组
		return abean.getItemId().compareTo(bbean.getItemId());
	}
	

}
