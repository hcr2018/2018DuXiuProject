package com.hcr.bigdate.consts;

public enum MonthEnum {
	/*
	 * 一月 Jan.January 二月 Feb.February   三月 Mar.March
	 * 四月 Apr.April   五月 May.May        六月 June.June  
	 * 七月 July.July   八月 Aug.Aguest     九月 Sept.September 
	 * 十月 Oct.October 十一月 Nov.November 十二月 Dec.December
	 */
	MON_JANUARY("一月","January","Jan"),MON_FEBRUARY("二月","February","Feb"),MON_MARCH("三月","March","Mar"),MON_APRIL,MON_MAY;

	private String chineseName;
	private String englishName;
	private String simpleName;
	


	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	private MonthEnum(String chineseName, String englishName, String simpleName) {
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.simpleName = simpleName;
	}
	
	private MonthEnum() {
		
	}
	
	
	
	
	
	

}
