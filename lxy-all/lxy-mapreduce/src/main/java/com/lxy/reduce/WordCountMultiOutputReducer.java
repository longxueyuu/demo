package com.lxy.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created by lxy on 2017/1/8.
 */
public class WordCountMultiOutputReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

    private MultipleOutputs mos;

    public void setup(Context context) {
        mos = new MultipleOutputs(context);
    }

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long wordCount = 0;
        for(LongWritable value : values){
            wordCount += value.get();
        }
        mos.write("countword", key, new LongWritable(wordCount), key + "/" + "words");

    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
