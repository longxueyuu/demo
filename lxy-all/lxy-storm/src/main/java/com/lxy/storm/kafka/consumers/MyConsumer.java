package com.lxy.storm.kafka.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lxy on 2017/2/15.
 */
public class MyConsumer {
    private final KafkaConsumer<String, String> consumer;
    private String topic;

    public MyConsumer(String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.201:9092,192.168.1.202:9092,192.168.1.203:9092,192.168.1.204:9092");
        props.put("group.id", groupId);
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }

    public Map<Long, String> getTopicRecords(int qty) {
        Map<Long, String> items = new HashMap<Long, String>();
        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (ConsumerRecord<String, String> record : records) {
            items.put(record.offset(), record.value());
            System.out.println(record.offset() + "" + record.value());
        }
        return items;
    }

    public static void main(String[] args) {
        String topic = "flume";
        MyConsumer consumer = new MyConsumer("testgroup", topic);
        consumer.getTopicRecords(1);
    }
}
