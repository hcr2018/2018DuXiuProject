package com.hcr.bigdate.consts;

import org.junit.Test;
/**
 * 枚举测试类
 * @author cjx
 *
 */
public class TestEnum {
	
	@Test
	public void test(){
		//直接用枚举类名调用枚举成员
		PersonEnum.NUM_OF_MIDAGE.setAge(65);
		int age = PersonEnum.NUM_OF_MIDAGE.getAge();
		System.out.println("6666666:::"+age);
		
		//调用getAge方法
		getAge(AgeEnum.NUM_OF_MIDAGE);
	}
	
	/***
	 * 定义一个方法，将枚举对象作为参数
	 * @param age
	 */
	
	public void getAge(AgeEnum age){
		switch (age) {
		case NUM_OF_CHILDAGE:
			System.out.println("你是一个少年");
			break;
		
		case NUM_OF_YOUNGAGE:
			System.out.println("优秀青年人");
			break;
		case NUM_OF_MIDAGE:
			System.out.println("即将进入老年生活");
			break;
		case NUM_OF_OLDAGE:
			System.out.println("优哉游哉的老年人");
			break;
		default:
			break;
		}
	}
	
	@Test
	public void testMonthEnum(){
		getMonth(MonthEnum.MON_JANUARY);
	}
	
	public void getMonth(MonthEnum monEnum){
		switch (monEnum) {
		case MON_JANUARY:
			System.out.println(monEnum.getChineseName());
			break;
		}
	}
	
	@Test
	public void testAMP(){
		AMP amp = new AMP("gougou", PersonEnum.NUM_OF_MIDAGE, AgeEnum.NUM_OF_MIDAGE);
		System.out.println(amp);
	}
}
