package com.hcr.hdfs;

import java.io.IOException;
import java.util.stream.Stream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * ��mapreduce�����У�����ᰴ��setInputFormat�����õķ���Ϊ�������зֳ�һ����InputSplit����Map�����У������Ϊÿһ��InputSplit����map���������Ｔ�Կո����ָ����������п������Ե�����Ϊkey��1��Ϊvalue����Ҫ�ر�ָ�����ǣ�mapreduce��<key,value>������key����value����mapreduceԤ�ȶ���õĸ�ʽ�������wordcount��������У�����Ҫ��Stringת����text��ʽ��intת��ΪIntWritable��ʽ��*  
   LongWritable, IntWritable, Text ���� Hadoop ��ʵ�ֵ����ڷ�װ Java �������͵��࣬��Щ��ʵ����WritableComparable�ӿڣ� 
        ���ܹ������л��Ӷ������ڷֲ�ʽ�����н������ݽ���������Խ����Ƿֱ���Ϊlong,int,String �����Ʒ��         
 * @author cjx
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	/** Mapper<LongWritable, Text, Text, IntWritable>   //�趨��map�����������ʽΪlongwritable<key>text<value>�������ʽΪtext<key>intwritable<value>
	 * @param key һ�е���ʼ�����ļ��е�ƫ����������
	 * @param value һ��һ�е�����
	 */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//��һ������ת����string�ַ���
		//String line = value.toString();
		String line = new String(value.getBytes());
		//��һ������ÿ������ȡ�����Կո�Ϊ�ָ�
		String[] words = line.split("\\b");
		
		//�������飬�����ʽ<���ʣ�1>��<���ʣ�2>
		/*for (String str : words) {
			//д��������ļ���
			////����һ��intwritable������˵�����ֹ�һ��
			context.write(new Text(str), new IntWritable(1));
		}*/
		
		Stream.of(words).forEach(str->{
			try {
				context.write(new Text(str), new IntWritable(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//super.map(key, value, context);
	}
	
	

}


