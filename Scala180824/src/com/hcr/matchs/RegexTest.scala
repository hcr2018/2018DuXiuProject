package com.hcr.matchs
import scala.util.matching.Regex
object RegexTest {
  def main(args: Array[String]): Unit = {
    testReg
  }
  /**
   * Scala 正则表达式
   */
  def testReg() {
    //使用 String 类的 r() 方法构造了一个Regex对象
    val date = "(\\d{4})-(\\d{2})-(\\d{2})-(\\d{2}):(\\d{2})".r
    "2018-08-24-15:46" match {
      case date(year, month, day, hour, minute) => println(s"$month-$day is a good day ")
    }

    //raw--原生的，不需要经过转义
    val num = raw"(\d+)".r
    val all = num.findAllIn("123").toList
    print(all) //List(123)

    val dates = "Important dates in history: 2004-01-20, 1958-09-05, 2010-10-06, 2011-07-15"
    val firtsDate = date.findFirstIn(dates).getOrElse("no date found")
    val firstYear = for (m <- date.findFirstMatchIn(dates)) yield m.group(1)
    val allYears = for (m <- date.findAllMatchIn(dates)) yield m.group(1)
    
    val mi = date.findAllIn(dates)
    while (mi.hasNext) {
      val d = mi.next
      if (mi.group(1).toInt < 1960) println(s"$d: An oldie but goodie.")
    }
  }
}