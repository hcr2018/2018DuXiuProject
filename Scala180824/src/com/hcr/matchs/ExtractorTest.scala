package com.hcr.matchs

object ExtractorTest {
   
  def main(args: Array[String]) {
    testExtrator
      
   }
  /**
   * ��ȡ��ʹ��ģʽƥ��
		������ʵ����һ�����ʱ�����Դ���0�����߶���Ĳ�������������ʵ������ʱ����� apply ������
		���ǿ�������Ͷ����ж����� apply ������
		unapply ������ȡ����ָ�����ҵ�ֵ������ apply �Ĳ����෴�� 
		����������ȡ��������ʹ�� match ����ǣ�unapply ���Զ�ִ�У�
   */
  def testExtrator(){
     val x = ExtractorTest(5) //apply ������
      println(x)//10

      x match //unapply ������
      {  
         case ExtractorTest(num) => println(x + " �� " + num + " ��������")
        //10 �� 3 ��������------num:Some(z/3)
         case _ => println("�޷�����")
      }
  }
  
   def apply(x: Int) = x*2
   def unapply(z: Int): Option[Int] = if (z%2==0) Some(z/3) else None

}