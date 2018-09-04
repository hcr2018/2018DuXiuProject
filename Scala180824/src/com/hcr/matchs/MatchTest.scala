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
    * ģʽƥ��
    */
   //һ��ģʽƥ�������һϵ�б�ѡ�ÿ������ʼ�ڹؼ��� case��ÿ����ѡ�������һ��ģʽ��һ��������ʽ����ͷ���� => ������ģʽ�ͱ��ʽ��
   //match ���ʽͨ���Դ����д���Ⱥ������ÿ��ģʽ����ɼ��㣬
   //ֻҪ������һ��ƥ���case��ʣ�µ�case�������ƥ�䡣
   def matchTest(x:Int):String=x  match {
    // case _ => "vhvb"
     case 1 => "one"
     case 2 => "two"
     case y: Int => "scala.Int"
     case _ => "hhhhvhvb"
   }
   
   /**
    * ʹ�������ࣺʹ����case�ؼ��ֵ��ඨ����Ǿ���������(case classes)
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
    // ������
   case class Person(name: String, age: Int)
   
    //�������󣬶�����������һ��
   //������߶���ķ�������ͨ������ֱ�ӵ���
  /* object Person{
      
      def apply(name: String, age: Int):Person={
         new Person(name,age)
      }
      
      def go(x:Int):Int=x+10
   }*/
   
}

