package com.hcr.hdfs;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * �̳�---Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * @author cjx
 * @version 2018-08-02
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//���մ�map��������ÿһ��kv
	//�趨reduce����������Ե�����������text��intwritable������Ե�����������text��intwritable
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count=0;
		
		Iterator<IntWritable> it = values.iterator();		
		while(it.hasNext()){//����ͬһ��key�£�����value���ܺ�
			count+=it.next().get();
		}
		
		/*for (IntWritable itable : values) {//����ͬһ��key�£�����value���ܺ�
			count+=itable.get();
		}*/
		context.write(key, new IntWritable(count));
	}
}
