package com.hcr.matchs

object MatchTest {
  
   def main(args: Array[String]): Unit = {
     println(matchTest(2))
     println(matchTest(3))
     println(matchTest(1))
     caseClassTest
   //println(  Person.go(10))
    
  }
   
   /**
    * 模式匹配
    */
   //一个模式匹配包含了一系列备选项，每个都开始于关键字 case。每个备选项都包含了一个模式及一到多个表达式。箭头符号 => 隔开了模式和表达式。
   //match 表达式通过以代码编写的先后次序尝试每个模式来完成计算，
   //只要发现有一个匹配的case，剩下的case不会继续匹配。
   def matchTest(x:Int):String=x  match {
    // case _ => "vhvb"
     case 1 => "one"
     case 2 => "two"
     case y: Int => "scala.Int"
     case _ => "hhhhvhvb"
   }
   
   /**
    * 使用样例类：使用了case关键字的类定义就是就是样例类(case classes)
    */
   
   
   def caseClassTest(){
       val alice = new Person("Alice", 25)
        val bob = new Person("Bob", 32)
       val charlie = new Person("Charlie", 32)
       
       for(person<-List(alice,bob,charlie)){
           person  match {
             case Person("Alice",25) => println("hi alice")
             case Person("Bob",32) => println("hi bob")
             case Person(name, age) =>
               println("Age: " + age + " year, name: " + name + "?")
             
           }
       }
   }
    // 样例类
   case class Person(name: String, age: Int)
   
    //伴生对象，对象名和类名一样
   //在这里边定义的方法可以通过类名直接调用
  /* object Person{
      
      def apply(name: String, age: Int):Person={
         new Person(name,age)
      }
      
      def go(x:Int):Int=x+10
   }*/
   
}

