package com.hcr.clazz

import scala.collection.mutable.Set

class Person(val name: String, var age: Int) extends Ordered[Person] {

  var sex: String = "male"
  //println("ִ�й��캯��")  

  def this() {
    //ÿһ��������������������һ������ǰ�Ѷ�����������������������������ĵ��ÿ�ʼ
    this(name = "hshsh", 16)
    this.sex = "male"
  }

  def this(name: String, age: Int, sex: String) {
    //this()
    this(name, age)
    this.sex = sex

  }

  /**
   * ��дtoString����
   */
  override def toString(): String = {
    name + " " + age + " " + sex + " "
  }

  override def equals(pp: Any): Boolean = {
    if (pp.isInstanceOf[Person]) {
      var p = pp.asInstanceOf[Person]

      return name.equals(p.name) && age == p.age && sex.equals(p.sex)
    }
    false
  }

  /**
   * ��д�ȽϷ���������Set����
   * abstract def compare(that: A): Int
   */
  override def compare(p: Person): Int = {
    var res: Int = this.name.compareTo(p.name)
    if (res == 0) {
      res = this.age - p.age
      if (res == 0) {
        return this.sex.compareTo(p.sex)
      } else {
        return res
      }

    } else

      res
  }
}

object Person {

  def main(aa: Array[String]): Unit = {
    //run
    testSet
  }

  def run(): Unit = {
    var per = new Person("hcr", 18)
    per.sex = "female"
    per.age = 19

    println(per.name)
    println(per.age)
    println(per)

  }

  def testSet(): Unit = {
    val set: Set[Person] = Set[Person]()

    var p: Person = new Person("zhangeu", 20, "��")
    set += p
    p = new Person("heji", 18, "Ů")
    set += p
    p = new Person("hejihe", 20, "Ů")
    set += p
    p = new Person("jihe", 17, "��")
    set += p
    p = new Person("zhangeu", 17, "��")
    set += p
    set.foreach(println)
    println("$$$$$$$$$$$$$$$$$$$$$$$$")
    print("max::::" + set.max)
    print("min::::" + set.min)
  }

}