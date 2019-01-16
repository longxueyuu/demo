package com.lxy.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by lxy on 2017/1/8.
 */
public class WordCountMapper extends Mapper<Object, Text, Text, LongWritable>{
    private String line;

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        line = value.toString();
        if(line != null && !"".equals(line))
        {
            String[] wordArray = line.split(" ");
            for(String word : wordArray){
                context.write(new Text(word.trim()), new LongWritable(1));
            }
        }
    }
}
