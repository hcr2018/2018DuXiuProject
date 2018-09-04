package com.hcr.loop
import scala.util.control.Breaks
object LoopExe {
 
	def main(aa:Array[String]){
	//	testFor
		//buf
		
		/*
		var num=0
		for(str<-aa){
		  num=num+Integer.valueOf(str)
		  
		}
		print(num)*/
		
		//调用go
		//默认值，健壮性考虑
		var end=1000
		if(aa.length>0){
		  var temp=Integer.parseInt(aa(0))
		    if(temp>0){
		      end=temp
		    }
		}
		go(end).foreach( print)
		
		//调用go结束
	}
	
	/**
	 * break,for(if;if)过滤的使用
	 */
	
	def go(num:Int):Set[Int]={
	  
	  var set=Set[Int]()
	  val loop=new Breaks;
	  loop.breakable{
	    for(temp<-1 to num;if(temp&1)==0;if((temp%3)==3)){
	      if(temp==720) loop.break
	      set=set+temp
	    }
	  }	  
	 set 
	}
	
	//重载
	def testFor(){
	  testFor(10)
	}
	
	def testFor(colum:Int):Unit={
	//	println("调用X to Y")
	  //将IO操作减少，最后才进行
	 /* var sb =new StringBuffer()
	  sb.append("调用X to Y \n")*/
	  var sb =new StringBuffer("调用X to Y \n")//new时加上，可减少内存空间的占用，new 时内存空间为16
		var c=0
		for(i<-1.to (100)){
			//print(i)
		  sb.append(i+"\t")
			c+=1
			//使用赋值运算比使用模运算效率高
			if(c==10){
				//println
			  sb.append("\n")
				c=0
			}
		}
	
	//	println("*******************")
		sb.append("*******************")
		//until不包含100
		for(i<-1.until (100)){
			//println(i)
		  sb.append(i).append("\n")
		} 
		print(sb)
	}
	
	
	
	def buf(){
		var list=List(1,2,4,5,6,12,80)
		val retList=for{
			a<-list
			//按位运算，与a%2==0等价，但按位运算效率更高
			if(a&1)==0
			if(a%3==0)
		
		}yield a
		
		for(s<-retList){
		//	s.forEach(println)
		  print(s+"\t")
		}
		
	}

}