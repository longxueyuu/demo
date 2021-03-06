package com.lxy.job;

import com.lxy.map.DedupMapper;
import com.lxy.reduce.DedupReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class DedupJob {

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

        Job job = Job.getInstance(conf, "Data Deduplication!");
        job.setJarByClass(DedupJob.class);

        //设置Map、Combine和Reduce处理类
        job.setMapperClass(DedupMapper.class);
        job.setCombinerClass(DedupReducer.class);
        job.setReducerClass(DedupReducer.class);

        //设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //设置输入和输出目录
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        // FileInputFormat.addInputPath(job, new Path("../PersonWorkSpace/java/mapreduce/src/main/resources/input"));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        // FileOutputFormat.setOutputPath(job, new Path("../PersonWorkSpace/java/mapreduce/src/main/resources/output"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}