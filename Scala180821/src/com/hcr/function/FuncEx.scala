package com.hcr.function



object FuncEx {
   def main(args: Array[String]): Unit = {
     
   // println( gaoJie(f1, 100))
   //  print(inc(2,3))
     testZip
    
  }
   
   /**
    * �߽׺���
    * �߽׺�����Higher-Order Function�����ǲ������������ĺ�����

Scala ������ʹ�ø߽׺���, �߽׺�������ʹ������������Ϊ����������ʹ�ú�����Ϊ��������

����ʵ���У�gaojie() ����ʹ��������һ������ f �� ֵ v ��Ϊ������������ f �ֵ����˲��� v��
    */
   def gaoJie(f:Int=>Double,v:Int)=f(v)
   
   def f1(num:Int):Double={
        num
     }
   
   /**
    * ������������ͷ����ǲ����б��ұ��Ǻ����塣
    */
   var inc=(a:Int,b:Int)=>a+b
   
  /* var inc2 = (x:Int) => x+1 
        ���������������������ʵ����������д���ļ�д��
   def add2 = new Function1[Int,Int]{  
    def apply(x:Int):Int = x+1;  
	} 
  */ 
   
   /**
    * �������������������������һ��
    */
   def testZip(){
     var arr=Array[Int](1,2,3,4)
     var arr3=Array(2,5,6)
     var arr2=Array("hehd","gdtfj","juudj","nnhvh")
    var part= arr.zip(arr2)
    var a=  arr.zip(arr)
    a.foreach(print)
    part.foreach(println)
    //(2,gdtfj)(3,juudj)(4,nnhvh)
    
    var aa=arr3.zipAll(arr2,3,"helo")
    aa.foreach(println)
    
   }
   
   def testFlatMap(){
      var list=List(1,2,3,4,5)
     
      val xs = Map("a" -> List(11,111), "b" -> List(22,222)).flatMap(_._2)
   }
   
   
}