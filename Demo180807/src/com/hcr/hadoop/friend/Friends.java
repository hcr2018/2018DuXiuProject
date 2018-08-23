package com.hcr.hadoop.friend;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Friends {
	
	public static class FriendsMapper extends Mapper<LongWritable,Text,Text,Text>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			//A:B,C,D,R
			String line = value.toString();
			String[] split = line.split(":");
			String owner=split[0];
			String[] friends = split[1].split(",");
			for (String f : friends) {
				context.write(new Text(f), new Text(owner));
			}
			
			
			//super.map(key, value, context);
		}
	}
	
	public static class FriendsReducer extends Reducer<Text,Text,Text,ArrayWritable>{
		
		@Override
		protected void reduce(Text owner, Iterable<Text> friends, Reducer<Text, Text, Text, ArrayWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			ArrayList<Text> list = new ArrayList<>();
			friends.forEach(list::add);
			Text[] values=new Text[list.size()];
			for (int i = 0; i < values.length; i++) {
				values[i] = list.get(i);
			}
			ArrayWritable arr = new ArrayWritable(Text.class);
			arr.set(values);
			context.write(owner, arr);
			//super.reduce(arg0, arg1, arg2);
		}
	}
	
	public static void main(String[] args) {
		Configuration config = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(config);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		job.setJarByClass(Friends.class);

		job.setMapperClass(FriendsMapper.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setReducerClass(FriendsReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(ArrayWritable.class);

		try {
			FileInputFormat.setInputPaths(job, args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean b = false;
		try {
			b = job.waitForCompletion(true);
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(b ? 0 : 1);

	}
	
	
	
	

}
