package com.hcr.hdfs;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * 继承---Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * @author cjx
 * @version 2018-08-02
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//接收从map传过来的每一组kv
	//设定reduce函数中输入对的数据类型是text和intwritable，输出对的数据类型是text和intwritable
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count=0;
		
		Iterator<IntWritable> it = values.iterator();		
		while(it.hasNext()){//计算同一个key下，所有value的总和
			count+=it.next().get();
		}
		
		/*for (IntWritable itable : values) {//计算同一个key下，所有value的总和
			count+=itable.get();
		}*/
		context.write(key, new IntWritable(count));
	}
}
