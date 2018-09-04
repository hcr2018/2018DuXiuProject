package com.hcr.collection

object TestOption {

  def main(args: Array[String]): Unit = {
    myOption
  }

  /**
   * Scala Option(选项)类型用来表示一个值是可选的（有值或无值)。
   * Option[T] 是一个类型为 T 的可选值的容器：
   * 如果值存在， Option[T] 就是一个 Some[T] ，
   * 如果不存在， Option[T] 就是对象 None 。
   */
  def myOption() {
    var map: Map[String, String] = Map("a" -> "haha", "b" -> "hcr", "c" -> "jies")

    println(map.get("a")) //Some(haha)
    println(map.get("e")) //None
    /* val value1: Option[String] = myMap.get("key1")
    val value2: Option[String] = myMap.get("key2")

    println(value1) // Some("value1")
    println(value2) // None
	*/
    println("*************************")
    //加上判断
    val value1: Option[String] = map.get("a")
    //isEmpty() 方法--来检测元组中的元素是否为 None
    if (value1.isEmpty) {
      println("为空")
    } else {
      println(value1) //Some(haha)
      println(value1.get) //haha
    }

    println("*****************************")
    val value2: Option[String] = map.get("r")
    //使用 getOrElse() 方法来获取元组中存在的元素或者使用其默认的值
    println(value1.getOrElse(0)) //haha
    println(value2.getOrElse("kdfg")) //kdfg
    
 println("*****************************")
  //  val nameMaybe = request getParameter "name"
    val value3: Option[String] = map.get("b")
    value3 match {
      case Some(name) =>
       // println(name.toString().trim().toUpperCase())
         println(name.toString.trim.toUpperCase)//HCR
      case None =>
        println("No name value")
    }
  }
}