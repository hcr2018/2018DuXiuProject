package com.hcr.bigdate.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
	
	public static void main(String[] args) {
		ConcurrentMap concurrentMap = new ConcurrentHashMap<>();
		concurrentMap.putIfAbsent("key", "hdyfhhf");
		
			
		Object value = concurrentMap.get("key");
		System.out.println(value);
		
	}
	}
	
