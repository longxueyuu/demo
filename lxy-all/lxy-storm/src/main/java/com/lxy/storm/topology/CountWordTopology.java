package com.lxy.storm.topology;

import com.lxy.storm.bolts.CountWordBolt;
import com.lxy.storm.bolts.ExtractWordBolt;
import com.lxy.storm.spouts.KafkaSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by lxy on 2017/2/19.
 */
public class CountWordTopology {
    public static void main(String[] args) throws InterruptedException {
        //定义一个Topology
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka-spout",new KafkaSpout());
        builder.setBolt("extract-word", new ExtractWordBolt()).shuffleGrouping("kafka-spout");
        builder.setBolt("count-word", new CountWordBolt(),2).fieldsGrouping("extract-word", new Fields("word"));
        //配置
        Config conf = new Config();
        conf.setDebug(true);
        //提交Topology
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        //创建一个本地模式cluster
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("CountWordTopology", conf,
                builder.createTopology());
        Thread.sleep(300000);
        cluster.shutdown();
    }
}
