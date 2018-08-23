package com.hcr.hadoop.friend;

import org.junit.Test;

public class TestFriends {
	
	
		@Test 
		public void test1(){
			String line="A:B,C,D,F,E,O";
			//A:B,C,D,F,E,O
			String [] arr=line.split(":");
			String owner=arr[0];
			String friends[]= arr[1].split(",");
			for(String f:friends){
				//context.write(new Text(f), new Text(owner));
				System.out.println(f+"::::"+owner);
			}
		}
	

}
