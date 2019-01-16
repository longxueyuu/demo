package com.lxy.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

/**
 * Created by lxy on 2017/2/15.
 */
public class MyConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final String topic;

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
        this.topic = topic;
    }

    public void testConsumer() {
        consumer.subscribe(Arrays.asList("flume"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(5);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    public static void main(String[] args) {
        String topic = "demo";
        MyConsumer consumer = new MyConsumer("testgroup", topic);
        consumer.testConsumer();
    }
}
