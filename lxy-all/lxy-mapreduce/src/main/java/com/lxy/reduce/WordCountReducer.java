package com.lxy.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by lxy on 2017/1/8.
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long wordCount = 0;
        for(LongWritable value : values){
            wordCount += value.get();
        }
        context.write(key, new LongWritable(wordCount));
    }
}
