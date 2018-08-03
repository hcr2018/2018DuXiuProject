package com.hcr.hdfs;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UsbFinding {
	public static class  FilterMapper extends Mapper<LongWritable,Text,LongWritable,Text>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
				throws IOException, InterruptedException {
			/*
			 * .filter(s ->{
						String str=s.split("\\|")[2];
						return str.matches(".*([uU][sS][bB]).*((id:0x[A-Fa-f0-9]+).*)|(.*(device 0x[A-Fa-f0-9]+).*)");
					}) 
			 */
			String line=value.toString();
			String str=line.split("\\|")[2];
			
			if(str.matches(".*([uU][sS][bB]).*((id:0x[A-Fa-f0-9]+).*)|(.*(device 0x[A-Fa-f0-9]+).*)")){
				context.write(key, value);
			}
			
		}
	}
	
	public static class FilterReducer extends Reducer<LongWritable,Text,Text,Text>{
		/*
		 *.forEach(s -> {
						String[] arr = s.split("\\|");						
						Matcher matcher = pattern.matcher(arr[2]);
						
						if (matcher.find()) {							
							String group2= matcher.group(2);							
							System.out.println((DateUtils.toDateString(dtf.parse(arr[0], LocalDateTime::from)) + "\n\t\t") +"::::::" + (group2 != null ? group2 : matcher.group(4))  );
						}
					});
		 */
		
		@Override
		protected void reduce(LongWritable lines, Iterable<Text> values,
				Reducer<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
			String s=null;
			Iterator<Text> it =values.iterator();
			Text text=it.next();
			s=text.toString();
			
			
			String[] arr = s.split("\\|");
			Pattern pattern = Pattern.compile("(.*id:(0x[A-Fa-f0-9]+).*)|(.*device (0x[A-Fa-f0-9]+).*)");
			Matcher matcher = pattern.matcher(arr[2]);
			
			if (matcher.find()) {							
				String group2= matcher.group(2);
				context.write(new Text(arr[0]),new Text((group2 != null ? group2 : matcher.group(4))));
				//System.out.println((DateUtils.toDateString(dtf.parse(arr[0], LocalDateTime::from)) + "\n\t\t") +"::::::" + (group2 != null ? group2 : matcher.group(4))  );
			}
		}
		
	}
	
	public static void main(String[] args) {
		Configuration conf=new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		job.setJarByClass(UsbFinding.class);
		
		job.setMapperClass(FilterMapper.class);
		
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		
		job.setReducerClass(FilterReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		try {
			FileInputFormat.setInputPaths(job, args[0]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean b = false;
		try {
			b = job.waitForCompletion(true);
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(b?0 :1);
	}
}
