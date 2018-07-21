package com.hcr.bigdata.collection;


import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import com.hcr.bigdata.bean.MonthEnum;
import com.hcr.bigdata.bean.Person;

/***
 * TreeSet(¶þ²æÊ÷£¬ºìºÚÊ÷)
 * @author cjx
 *
 */
public class TreeSetEx  {
	@Test
	public void testTreeSet(){
	
		System.out.println("************comparator******************");
		//Comparator.comparing(person->((Person)person).getName());
		Comparator<Person> c1 = Comparator.comparing(Person::getName);
		Comparator<Person> c2 =Comparator.comparing(Person::getHeight);
		Comparator<Person> c3 = Comparator.comparing(Person::getMonth);
		Comparator<Person> com = c1.thenComparing(c2).thenComparing(c3);
		 TreeSet<Person> set = new TreeSet<>(com);
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
		
	}
	
	

	

}
