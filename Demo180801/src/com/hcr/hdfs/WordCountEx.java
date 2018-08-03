package com.hcr.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCountEx {

	public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			// public final class ActivationDesc
			String[] arr = line.split("\\b");
			for (String s : arr) {
				context.write(new Text(s), new IntWritable(1));
			}

			/*
			 * try { Stream.of(arr).forEach((s)->{ try { context.write(new
			 * Text(s), new IntWritable(1)); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } }); } catch
			 * (Exception e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } //super.map(key, value, context);
			 */ }
	}

	public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context)
						throws IOException, InterruptedException {
			// public,1 final,1 class,1 public,1 ActivationDesc,1
			int count = 0;

			for (IntWritable iValue : values) {
				count += iValue.get();
			}

			context.write(key, new IntWritable(count));

		}
	}

	public static void main(String[] args) {
		Configuration config = new Configuration();
		Job job = null;

		try {
			job = Job.getInstance(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		job.setJarByClass(WordCountEx.class);

		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// 判断，用户是否传递参数，。。。
		try {
			//FileInputFormat.setInputPaths(job, args[0]);
			FileInputFormat.setInputPaths(job,"hdfs://192.168.145.130:8020/myDir/test.txt");
		} catch (IllegalArgumentException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//FileOutputFormat.setOutputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.145.130:8020/workcount/output02"));

		boolean b = false;
		try {
			b = job.waitForCompletion(false);
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.exit(b ? 0 : 1);

	}

}
