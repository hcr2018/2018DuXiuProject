package com.hcr.collection

object TestOption {

  def main(args: Array[String]): Unit = {
    myOption
  }

  /**
   * Scala Option(ѡ��)����������ʾһ��ֵ�ǿ�ѡ�ģ���ֵ����ֵ)��
   * Option[T] ��һ������Ϊ T �Ŀ�ѡֵ��������
   * ���ֵ���ڣ� Option[T] ����һ�� Some[T] ��
   * ��������ڣ� Option[T] ���Ƕ��� None ��
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
    //�����ж�
    val value1: Option[String] = map.get("a")
    //isEmpty() ����--�����Ԫ���е�Ԫ���Ƿ�Ϊ None
    if (value1.isEmpty) {
      println("Ϊ��")
    } else {
      println(value1) //Some(haha)
      println(value1.get) //haha
    }

    println("*****************************")
    val value2: Option[String] = map.get("r")
    //ʹ�� getOrElse() ��������ȡԪ���д��ڵ�Ԫ�ػ���ʹ����Ĭ�ϵ�ֵ
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