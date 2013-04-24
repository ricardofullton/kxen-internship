package com.kxen.han.projection.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * First step of parallel bipartite graph projection
 * This reducer can also be used as a combiner function
 * 
 * @author Han JU
 *
 */
public class ParallelCountingReducer 
extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	@Override
	public void reduce(Text key, Iterable<LongWritable> values,
			Context context) throws IOException, InterruptedException {
		long sum = 0;
		for (LongWritable l : values) {
			sum += l.get();
		}
		context.write(key, new LongWritable(sum));
	}
}