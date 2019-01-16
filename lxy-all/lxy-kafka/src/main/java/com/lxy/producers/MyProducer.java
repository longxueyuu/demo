package com.lxy.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by lxy on 2017/2/15.
 */
public class MyProducer {
    private static Producer<String, String> producer;

    MyProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.201:9092,192.168.1.202:9092,192.168.1.203:9092,192.168.1.204:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public static void main(String[] args) {
        MyProducer dp = new MyProducer();
        for(int i = 0; i < 5; i++)
            producer.send(new ProducerRecord<String, String>("demo", Integer.toString(i), Integer.toString(i * i)));
        producer.close();
    }
}
