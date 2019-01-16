package com.lxy.producers;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by lxy on 2017/2/15.
 */
public class DemoProducer {
    private static Producer<Integer, String> producer;
    private final Properties props = new Properties();

    DemoProducer() {
        //定义连接的broker list
        props.put("metadata.broker.list", "192.168.1.201:9092,192.168.1.202:9092,192.168.1.203:9092,192.168.1.204:9092");
        //定义序列化类（Java对象传输前要序列化）
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
    }

    public static void main(String[] args) {
        DemoProducer dp = new DemoProducer();
        String topic = "demo";
        for(int i = 0; i < 10; i++) {
            String message = "the first message from java client " + i;
            KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, message);
            producer.send(data);
        }
        producer.close();
    }
}
