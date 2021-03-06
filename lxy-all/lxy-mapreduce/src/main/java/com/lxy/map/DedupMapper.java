package com.lxy.map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//map将输入中的value复制到输出数据的key上，并直接输出
public class DedupMapper extends Mapper<Object,Text,Text,Text> {
    private static Text line=new Text();//每行数据

    //实现map函数
    public void map(Object key,Text value,Context context)
            throws IOException,InterruptedException{

        line=value;
        context.write(line, new Text(""));
    }

}
