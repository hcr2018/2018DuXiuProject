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
		///��ҵ���߼���ص���Ϣ���ĸ���mapper���ĸ���reducer��Ҫ������������������Ľ�����������������һ��job����
		//����������õ�job�ύ����Ⱥȥ����

		Configuration conf = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ָ��jar��
		job.setJarByClass(WordCountRunner.class);
		
		//�������ǵ�ҵ���߼�Mapper������key��value����������
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//�������ǵ�ҵ���߼�Reducer������key��value����������
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//�ж��û��Ƿ񴫵ݲ���
		if(args.length<2){
			System.out.println("�޷�ִ�г���");
			System.exit(0);
		}else{
		//�������ݵ�����λ��
		try {
			FileInputFormat.setInputPaths(job, args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������ɺ��������λ��
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		}
		//��yarn��Ⱥ�ύjob
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
