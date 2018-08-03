package com.hcr.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountRunner {
	
public static void main(String[] args) {
		///把业务逻辑相关的信息（哪个是mapper，哪个是reducer，要处理的数据在哪里，输出的结果放哪里……）描述成一个job对象
		//把这个描述好的job提交给集群去运行

		Configuration conf = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//指定jar包
		job.setJarByClass(WordCountRunner.class);
		
		//设置我们的业务逻辑Mapper类的输出key和value的数据类型
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//设置我们的业务逻辑Reducer类的输出key和value的数据类型
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//判断用户是否传递参数
		if(args.length<2){
			System.out.println("无法执行程序");
			System.exit(0);
		}else{
		//处理数据的所在位置
		try {
			FileInputFormat.setInputPaths(job, args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//处理完成后结果保存的位置
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		}
		//向yarn集群提交job
		boolean b = false;
		try {
			b = job.waitForCompletion(true);
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(b? 0:1);
	}
}
