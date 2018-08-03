package com.hcr.hadoop.mapreducer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.hcr.hdfs.UsbFindId;
import com.hcr.hdfs.UsbFindId.FilterMapper;
import com.hcr.hdfs.UsbFindId.FilterReducer;

public class UsbFind {
	
	public static class FilterMapper extends Mapper<LongWritable, Text,  LongWritable,Text>{

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
			String line = new String(value.getBytes());
			//�ԡ�|���ָ��ÿһ�飬���������usb
			String str = line.split("\\|")[2];
			//2018-05-24T10:54:07.500+08:00| vmx| I125: USB: Connecting device desc:name:Virtual\ Bluetooth\ Adapter vid:0a12 pid:0001 speed:full family:wireless,bluetooth deviceType:virtual-bluetooth info:0000001 version:3 id:0x700000010a120001
			//�ҳ�ƥ��������usb����
			if(str.matches(".*([uU][sS][bB]).*((id:0x[A-Fa-f0-9]+).*)|(.*(device 0x[A-Fa-f0-9]+).*)")){
				//д������ļ���
				context.write(key, value);
			}
		//super.map(key, value, context);
		}
		
	
	}


public static class FilterReducer extends Reducer<LongWritable, Text, String, Text>{
	
	@Override
	protected void reduce(LongWritable key, Iterable<Text> values,
			Reducer<LongWritable, Text, String, Text>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//����������
		Iterator<Text> it = values.iterator();
		String str=null;
		while(it.hasNext()){//��ȡÿһ�е�����
			byte[] by = it.next().getBytes();
			//ת��string����
			 str= new String(by);
			
		}
		String[] split = str.split("|");
		//����������ʽ
		Pattern pattern = Pattern.compile("(.*id:(0x[A-Fa-f0-9]+).*)|(.*(device(0x[A-Fa-f0-9]+).*)");
		//��������Ԫ������������ʽƥ��
		Matcher matcher = pattern.matcher(split[2]);
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		if(matcher.find()){
			//���飬��ȡ��2��͵�4��
			String group2 = matcher.group(2);
			LocalDateTime ldt = dtf.parse(split[0], LocalDateTime::from);
			context.write(DateUtils.toDateString(ldt), new Text((group2 != null ? group2 : matcher.group(4))));

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
		System.out.println("�޷�ִ��");
		System.exit(0);
	}else{
		try {
			//ָ��Ҫ������ļ�λ��
			FileInputFormat.setInputPaths(job, args[0]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ָ��������ɺ�����ŵ�λ��
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
