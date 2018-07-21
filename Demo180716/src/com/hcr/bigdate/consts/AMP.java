package com.hcr.bigdate.consts;

public class AMP {
	private String name;
	private PersonEnum perEnum;
	private AgeEnum ageEnum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PersonEnum getPerEnum() {
		return perEnum;
	}
	public void setPerEnum(PersonEnum perEnum) {
		this.perEnum = perEnum;
	}
	public AgeEnum getAgeEnum() {
		return ageEnum;
	}
	public void setAgeEnum(AgeEnum ageEnum) {
		this.ageEnum = ageEnum;
	}
	public AMP(String name, PersonEnum perEnum, AgeEnum ageEnum) {
		super();
		this.name = name;
		this.perEnum = perEnum;
		this.ageEnum = ageEnum;
	}
	public AMP() {
		super();
	}
	@Override
	public String toString() {
		return "AMP [name=" + name + ", perEnum=" + perEnum + ", ageEnum=" + ageEnum + "]";
	}
	
	
}
