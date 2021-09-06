package com.ly.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerTest {
    private KafkaProducerTest(){
    }

    void produce() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");//,间隔
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 3844; i < 1000000; i++){
            System.out.printf("produce %d message\n",i);
            producer.send(new ProducerRecord<String, String>("LY-HELLO-WORLD", i%4 , "hello"+Integer.toString(i), "hello"+Integer.toString(i)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }


        producer.close();
    }

    public static void main( String[] args )
    {
        new KafkaProducerTest().produce();
    }
}
