package com.hcr.groupingcomparator;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class Secondarysort {

	static class SecondarysortMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

		OrderBean bean = new OrderBean();

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context)
						throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			String line = value.toString();
			String[] split = line.split(",");
			bean.setItemId(new Text(split[0]));
			bean.setAmount(new DoubleWritable(Double.parseDouble(split[2])));
			context.write(bean, NullWritable.get());

			// super.map(key, value, context);
		}

	}

	static class SecondarysortRecuder extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {

		@Override
		protected void reduce(OrderBean key, Iterable<NullWritable> values,
				Reducer<OrderBean, NullWritable, OrderBean, NullWritable>.Context context)
						throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			context.write(key, NullWritable.get());
			// super.reduce(arg0, arg1, arg2);
		}
	}

	public static void main(String[] args) {

	}

}
