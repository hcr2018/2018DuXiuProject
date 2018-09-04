package com.hcr.function



object FuncEx {
   def main(args: Array[String]): Unit = {
     
   // println( gaoJie(f1, 100))
   //  print(inc(2,3))
     testZip
    
  }
   
   /**
    * 高阶函数
    * 高阶函数（Higher-Order Function）就是操作其他函数的函数。

Scala 中允许使用高阶函数, 高阶函数可以使用其他函数作为参数，或者使用函数作为输出结果。

以下实例中，gaojie() 函数使用了另外一个函数 f 和 值 v 作为参数，而函数 f 又调用了参数 v：
    */
   def gaoJie(f:Int=>Double,v:Int)=f(v)
   
   def f1(num:Int):Double={
        num
     }
   
   /**
    * 匿名函数，箭头左边是参数列表，右边是函数体。
    */
   var inc=(a:Int,b:Int)=>a+b
   
  /* var inc2 = (x:Int) => x+1 
        上述定义的匿名函数，其实是下面这种写法的简写：
   def add2 = new Function1[Int,Int]{  
    def apply(x:Int):Int = x+1;  
	} 
  */ 
   
   /**
    * 拉链操作，将两个集合组成在一起
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