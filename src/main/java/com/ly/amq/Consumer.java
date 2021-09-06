package com.ly.amq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        // 跟Producer一样创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("amq-demo");
        MessageConsumer consumer = session.createConsumer(destination);

        while(true) {
            // receive() 方法当没有消息的时候会阻塞在这，等待提供者提供消息，后面介绍使用监听来获取消息
            TextMessage msg = (TextMessage) consumer.receive();
            System.out.println("消费消息：" + msg.getText());
        }
    }
}
