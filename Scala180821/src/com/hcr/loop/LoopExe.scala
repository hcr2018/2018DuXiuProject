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
		
		//����go
		//Ĭ��ֵ����׳�Կ���
		var end=1000
		if(aa.length>0){
		  var temp=Integer.parseInt(aa(0))
		    if(temp>0){
		      end=temp
		    }
		}
		go(end).foreach( print)
		
		//����go����
	}
	
	/**
	 * break,for(if;if)���˵�ʹ��
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
	
	//����
	def testFor(){
	  testFor(10)
	}
	
	def testFor(colum:Int):Unit={
	//	println("����X to Y")
	  //��IO�������٣����Ž���
	 /* var sb =new StringBuffer()
	  sb.append("����X to Y \n")*/
	  var sb =new StringBuffer("����X to Y \n")//newʱ���ϣ��ɼ����ڴ�ռ��ռ�ã�new ʱ�ڴ�ռ�Ϊ16
		var c=0
		for(i<-1.to (100)){
			//print(i)
		  sb.append(i+"\t")
			c+=1
			//ʹ�ø�ֵ�����ʹ��ģ����Ч�ʸ�
			if(c==10){
				//println
			  sb.append("\n")
				c=0
			}
		}
	
	//	println("*******************")
		sb.append("*******************")
		//until������100
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
			//��λ���㣬��a%2==0�ȼۣ�����λ����Ч�ʸ���
			if(a&1)==0
			if(a%3==0)
		
		}yield a
		
		for(s<-retList){
		//	s.forEach(println)
		  print(s+"\t")
		}
		
	}

}