package com.ly.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import sun.security.pkcs11.wrapper.Constants;

import java.util.Arrays;
import java.util.Properties;
public class KafkaConsumerTest {
    private KafkaConsumerTest() {
    }

    void consume() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");//,间隔
        props.put("group.id", "testsssss");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //针对某个topic
//         consumer.subscribe(Arrays.asList(Constants.TOPIC));
        //读具体分区
        TopicPartition partition0 = new TopicPartition("LY-HELLO-WORLD",0);
        consumer.assign(Arrays.asList(partition0));
        boolean flag = true;
        while (flag) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
        }

        consumer.close();
    }

    public static void main(String[] args) {
        new KafkaConsumerTest().consume();
    }
}
