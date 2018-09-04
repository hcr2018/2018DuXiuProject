package com.hcr.collection

object ListEx {
   
 def main(args: Array[String]): Unit = {
     val map=Map(1->"zanssa",2->"ffgr",3->"gyde")
     //map遍历输出的三种方式
     // map.foreach(println)
        
    /* var it =map.iterator
        while(it.hasNext){
          var temp=it.next
          // println(it.next) //(1,zanssa)(2,ffgr)(3,gyde)
           println(temp._1+"\t"+temp._2)
        }*/
     
     for(ma<-map) println(ma)
      var count:Int=0
    // map.reduce((a1,a2)=>{count+=a1._2.length})
     
  }
   
  
}