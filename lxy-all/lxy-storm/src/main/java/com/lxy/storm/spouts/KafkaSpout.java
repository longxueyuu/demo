package com.lxy.storm.spouts;

import com.lxy.storm.kafka.consumers.MyConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Set;

/**
 * Created by lxy on 2017/2/19.
 */
public class KafkaSpout implements IRichSpout {
    private MyConsumer kafkaConsumer;
    private SpoutOutputCollector collector;
    private boolean completed = false;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

        // 消费kafka消息
        this.kafkaConsumer = new MyConsumer("group1", "flume");
        //初始化发射器
        this.collector = collector;

    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void nextTuple() {
        if (completed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing
            }
            return;
        }
        try {
            while ( true) {
                Map<Long, String> map = kafkaConsumer.getTopicRecords(200);
                if(map == null){
                    continue;
                }
                /**
                 * 发射每一行，Values是一个ArrayList的实现
                 */
                Set<Long> keys = map.keySet();
                for(Long key : keys)
                {
                    String str = map.get(key);
                    this.collector.emit(new Values(str), str);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading tuple", e);
        } finally {
            completed = true;
        }
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("OK:" + msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("FAIL:" + msgId);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }



}
