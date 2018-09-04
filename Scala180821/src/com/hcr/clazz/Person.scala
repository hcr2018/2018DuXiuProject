package com.hcr.clazz

import scala.collection.mutable.Set

class Person(val name: String, var age: Int) extends Ordered[Person] {

  var sex: String = "male"
  //println("执行构造函数")  

  def this() {
    //每一个辅助构造器都必须以一个对先前已定义的其它辅助构造器或主构造器的调用开始
    this(name = "hshsh", 16)
    this.sex = "male"
  }

  def this(name: String, age: Int, sex: String) {
    //this()
    this(name, age)
    this.sex = sex

  }

  /**
   * 重写toString方法
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
   * 重写比较方法，进行Set排序
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

    var p: Person = new Person("zhangeu", 20, "男")
    set += p
    p = new Person("heji", 18, "女")
    set += p
    p = new Person("hejihe", 20, "女")
    set += p
    p = new Person("jihe", 17, "男")
    set += p
    p = new Person("zhangeu", 17, "男")
    set += p
    set.foreach(println)
    println("$$$$$$$$$$$$$$$$$$$$$$$$")
    print("max::::" + set.max)
    print("min::::" + set.min)
  }

}