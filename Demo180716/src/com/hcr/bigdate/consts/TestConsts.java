package com.hcr.bigdate.consts;

import org.junit.Test;
import static com.hcr.bigdate.consts.PersonConsts.*;
public class TestConsts {
	@Test
	public void test(){
		//常量可以直接用类名调用
		int numBer = PersonConsts.NUM_BER;		
		System.out.println(numBer);
		//使用静态导入
		int num=NUM_BER;
		System.out.println(num);
	}
	
	
	@Test
	//使用test不能运行，要用main方法运行
	public static void main(String args[]){
		/*PersonConsts pc = new PersonConsts() {
			
			@Override
			public String control(String s) {
				// TODO Auto-generated method stub
				return "abc"+s;
			}
		};*/
		//使用lamdon表达式实现，与上面等价
		//pc是个函数，定义了一个函数
		//PersonConsts pc=(s)->{return "abc:"+s;};
		PersonConsts pc=s->"abc:"+s;
		
		System.out.println(pc.NUM_BER);
		pc.say("helo dog");
		String control = pc.control("dog");
		System.out.println(control);
		
		
	}
}
