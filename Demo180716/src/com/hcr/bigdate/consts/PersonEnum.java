package com.hcr.bigdate.consts;

/**
 * 枚举类
 * @author cjx
 *
 */
public enum PersonEnum {
	//枚举成员，并赋值
	NUM_OF_CHILDAGE(10),NUM_OF_YOUNGAGE(25),NUM_OF_MIDAGE(60),NUM_OF_OLDAGE(80);
	//变量
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * 私有构造方法
	 * @param age
	 */
	private PersonEnum(int age) {
		this.age = age;
	}
	
	
	

}
