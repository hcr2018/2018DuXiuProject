package com.hcr.loop

import scala.collection.mutable._

object ForEx{
	def main(aa:Array[String]):Unit={
		//for1
		//testArrayBuffer
		testFunction
	}
	
	
	def testFunction():Unit={
		val f2=   (i:Int) => i*5	
		val f3=  (i:Int) => i*6
	
		def f1(ffff:Function[Int,Int], j:Int):Int={
			var i=j
			i+=1
			i*=10
			
			
			ffff(i)
		}
		
		val res=f1(f3,4)
		
		println(res)
	}
	
	def for1(){
		val list=List(1,2,3,4,5,6,7,8,12,13,18)
		var temp=0
		
		val filterList=for{ 
			temp <-list
			if (temp & 1)==0
			if  (temp%3==0)
		 }yield temp
		 
		 
		 filterList.foreach(println)
		 println("**************")
		 println(filterList.toString)
		 println(filterList.getClass().getName)
	}
	
	def testArrayBuffer(){
		var arrayBuffer=new ArrayBuffer[Int] 
		arrayBuffer=( 1 +:2  +:3 +:4 +:arrayBuffer)
		
		var list2=List(10,20,30,40)
		
		arrayBuffer = arrayBuffer ++  list2
		
		arrayBuffer=arrayBuffer - 30
		
		arrayBuffer.update(arrayBuffer.indexOf(40),400)
		//arrayBuffer =(list2 ++: arrayBuffer)
		arrayBuffer.foreach(println)
	}
}
