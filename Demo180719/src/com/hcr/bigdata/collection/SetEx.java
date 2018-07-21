package com.hcr.bigdata.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Test;

import com.hcr.bigdata.bean.MonthEnum;
import com.hcr.bigdata.bean.Person;

public class SetEx {
	
	@Test
	public void testHashSet(){
		HashSet<Person> set = new HashSet<>();
		Person p = new Person("zhangsan", 186, MonthEnum.MON_APRIL);
		set.add(p);
		 p = new Person("lisi", 156, MonthEnum.MON_APRIL);
		set.add(p);
		 p = new Person("lisi", 156, MonthEnum.MON_MAY);
			set.add(p);
		 p = new Person("zhangliu", 162, MonthEnum.MON_FEBRUARY);
		set.add(p);
		 p = new Person("hehe", 157, MonthEnum.MON_JANUARY);
		set.add(p);
		
		set.forEach(System.out::println);
		
		LinkedList<Person> list = new LinkedList<>();
		Person pe = new Person("lisi", 156, MonthEnum.MON_MAY);
			list.add(pe);
		 pe = new Person("zhangliu", 162, MonthEnum.MON_FEBRUARY);
		list.add(pe);
		
		System.out.println("****************retainall***************");
		boolean retainAll = set.retainAll(list);
		set.forEach(System.out::println);
		
		System.out.println("***************removeif**************");
		set.removeIf(pp->pp.getName().contains("li"));
		
		
	}
	
	
	
}
