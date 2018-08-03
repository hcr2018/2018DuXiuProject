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
 * 在mapreduce程序中，程序会按照setInputFormat中设置的方法为将输入切分成一个个InputSplit。在Map过程中，程序会为每一个InputSplit调用map函数，这里即以空格作分隔符将单词切开。并以单词作为key，1作为value。需要特别指出的是，mapreduce的<key,value>无论是key还是value都是mapreduce预先定义好的格式，因此在wordcount这个程序中，我们要把String转换成text格式，int转换为IntWritable格式。*  
   LongWritable, IntWritable, Text 均是 Hadoop 中实现的用于封装 Java 数据类型的类，这些类实现了WritableComparable接口， 
        都能够被串行化从而便于在分布式环境中进行数据交换，你可以将它们分别视为long,int,String 的替代品。         
 * @author cjx
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	/** Mapper<LongWritable, Text, Text, IntWritable>   //设定了map函数输入的形式为longwritable<key>text<value>输出地形式为text<key>intwritable<value>
	 * @param key 一行的起始点在文件中的偏移量，行数
	 * @param value 一行一行的内容
	 */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//将一行数据转换成string字符串
		//String line = value.toString();
		String line = new String(value.getBytes());
		//将一行数据每个单词取出，以空格为分隔
		String[] words = line.split("\\b");
		
		//遍历数组，输出形式<单词，1>，<单词，2>
		/*for (String str : words) {
			//写到输出的文件中
			////定义一个intwritable，用来说明出现过一次
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


