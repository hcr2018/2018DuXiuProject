package com.hcr.collection

//import scala.collection.immutable.Nil
/**
 * 用到了修饰模式
 */
class PersonManager {

  private var persons: List[Person] = Nil

  def addPerson(p: Person): Boolean = {
    persons = p :: persons
    true
  }

  def deletePerson(p: Person): Boolean = {

    var oldLength = persons.size
    persons = persons.dropWhile(_.equals(p))
    oldLength - persons.length > 0

  }

  def showPerson() {
    if (persons.size != 0) {
      println(persons.mkString("##"))
    }
    // println("no person")
  }

}

object PersonManager {

  def main(args: Array[String]) {
    //  var pm=new PersonManager
    var pm = new PersonManager
    var p: Person = new Person("hcr05", 20, "female")
    pm.addPerson(p)
    p = new Person("dffg", 18, "male")
    pm.addPerson(p)

    pm.showPerson
  }

}