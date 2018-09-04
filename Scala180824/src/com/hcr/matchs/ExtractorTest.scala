package com.hcr.matchs

object ExtractorTest {
   
  def main(args: Array[String]) {
    testExtrator
      
   }
  /**
   * 提取器使用模式匹配
		在我们实例化一个类的时，可以带上0个或者多个的参数，编译器在实例化的时会调用 apply 方法。
		我们可以在类和对象中都定义 apply 方法。
		unapply 用于提取我们指定查找的值，它与 apply 的操作相反。 
		当我们在提取器对象中使用 match 语句是，unapply 将自动执行，
   */
  def testExtrator(){
     val x = ExtractorTest(5) //apply 被调用
      println(x)//10

      x match //unapply 被调用
      {  
         case ExtractorTest(num) => println(x + " 是 " + num + " 的两倍！")
        //10 是 3 的两倍！------num:Some(z/3)
         case _ => println("无法计算")
      }
  }
  
   def apply(x: Int) = x*2
   def unapply(z: Int): Option[Int] = if (z%2==0) Some(z/3) else None

}