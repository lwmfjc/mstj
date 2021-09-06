package com.ly.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("group_name");
        //producer.setNamesrvAddr("192.168.2.222:9876;192.168.2.223:9876");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message msg = new org.apache.rocketmq.common.message.Message("TopicTest",              // topic
                        "TagA",                                     // tag
                        ("HelloWorld - RocketMQ" + i).getBytes()    // body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(50);
            }
        }

        producer.shutdown();
    }
}
