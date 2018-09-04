package com.hcr.io

import scala.io.BufferedSource
import scala.io.Source
import java.io._

object IOTest {
    def main(args: Array[String]): Unit = {
      ioTest
    }
    
   def ioTest(){
 
    // val inputStream = new FileInputStream("D:\\Workspaces\\hello.txt")
      //val writer=new BufferedSource( new InputStream("D:\\Workspaces\\hello.txt"))
    var fis= Source.createBufferedSource(new FileInputStream(new File("D:\\Workspaces\\hello.txt")))
    var str=  fis.getLines();
    while(str.hasNext){
       println(str.next)
    }
    
   }
}