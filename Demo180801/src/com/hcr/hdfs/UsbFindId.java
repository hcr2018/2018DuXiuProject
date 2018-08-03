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

public class UsbFindId {
	
	public static class FilterMapper extends Mapper<LongWritable, Text,  LongWritable,Text>{

			@Override
			protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
				String line = new String(value.getBytes());
				//以“|”分割，得每一组，第三组包含usb
				String str = line.split("\\|")[2];
				//2018-05-24T10:54:07.500+08:00| vmx| I125: USB: Connecting device desc:name:Virtual\ Bluetooth\ Adapter vid:0a12 pid:0001 speed:full family:wireless,bluetooth deviceType:virtual-bluetooth info:0000001 version:3 id:0x700000010a120001
				//找出匹配项，输出含usb的行
				if(str.matches(".*([uU][sS][bB]).*((id:0x[A-Fa-f0-9]+).*)|(.*(device 0x[A-Fa-f0-9]+).*)")){
					//写到输出文件中
					context.write(key, value);
				}
			//super.map(key, value, context);
			}
			
		
		}
	
	
	public static class FilterReducer extends Reducer<LongWritable, Text, Text, Text>{
		
		@Override
		protected void reduce(LongWritable key, Iterable<Text> values,
				Reducer<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			//遍历所有行
			Iterator<Text> it = values.iterator();
			String str=null;
			while(it.hasNext()){//获取每一行的内容
				byte[] by = it.next().getBytes();
				//转成string类型
				 str= new String(by);
				
			}
			String[] split = str.split("|");
			//编译正则表达式
			Pattern pattern = Pattern.compile("(.*id:(0x[A-Fa-f0-9]+).*)|(.*(device(0x[A-Fa-f0-9]+).*)");
			//将第三个元素项与正则表达式匹配
			Matcher matcher = pattern.matcher(split[2]);
			if(matcher.find()){
				//分组，获取第2组和第4组
				String group2 = matcher.group(2);
				context.write(new Text(split[0]), new Text((group2 != null ? group2 : matcher.group(4))));

			}

		}
	
		
	}
	
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		Job job = null;
		try {
			job = Job.getInstance(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		job.setJarByClass(UsbFindId.class);
		job.setMapperClass(FilterMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(FilterReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		if (args.length<2){
			System.out.println("无法执行");
			System.exit(0);
		}else{
			try {
				//指定要处理的文件位置
				FileInputFormat.setInputPaths(job, args[0]);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//指定处理完成后结果存放的位置
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

}
