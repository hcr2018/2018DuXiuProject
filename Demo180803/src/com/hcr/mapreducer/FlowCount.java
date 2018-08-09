package com.hcr.mapreducer;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowCount {
	static class FlowCountMapper extends Mapper<LongWritable, Text, FlowBean,Text > {

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String line = value.toString();
			String[] fields = line.split("\t");//s空格  b单词边界符
			try {
				String phonenbr = fields[0];

				//long upflow = Long.parseLong(fields[6]);
				//long dflow = Long.parseLong(fields[7]);
				
				FlowBean flowBean = new FlowBean(fields[6], fields[7]);

				context.write(flowBean,new Text(phonenbr));
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	static class FlowCountReducer extends Reducer<FlowBean,Text,Text, FlowBean> {

		@Override
		protected void reduce(FlowBean bean, Iterable<Text> phonenbr, Context context) throws IOException, InterruptedException {

			Iterator<Text> it = phonenbr.iterator();
			while(it.hasNext()){
				
				context.write(it.next(), bean);
			}

		}

	}
	
	/**
	 * 将结果分组
	 * @author cjx
	 *
	 */
	public static class FlowPartitioner extends Partitioner<Text,FlowBean>{

		@Override
		public int getPartition( Text phone,FlowBean value, int numPart) {
			// TODO Auto-generated method stub
			int res=0;
			String str = phone.toString();
			String num = str.substring(0, 3);
			switch (num){
			 case ""+135:
				 res=0;
			 break;
			 
			 case ""+136:
				 res=1;
			 break;
			 case ""+137:
				 res=2;
			 break;
			 
			 case ""+138:
				 res=3;
			 break;
			 
			default:
				res=4;
			 break;	
			 
			}
			return res;
		}
		
	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf);

		job.setJarByClass(FlowCount.class);
		job.setPartitionerClass(FlowPartitioner.class);
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);

		 job.setMapOutputKeyClass(FlowBean.class);
		 job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		// job.setInputFormatClass(TextInputFormat.class);
		job.setNumReduceTasks(4);
		FileInputFormat.setInputPaths(job, args[0]);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true)?0:1);

	}


}
