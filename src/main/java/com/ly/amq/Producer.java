package com.ly.amq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class Producer {
    public static void main(String[] args) throws JMSException {
        // ActiveMQConnectionFactory.DEFAULT_USER 与 ActiveMQConnectionFactory.DEFAULT_PASSWORD 是连接AMQ的默认用户名与密码，可以在AMQ的配置文件里修改
        // 地址连接使用 tcp 端口是61616
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 如果session不支持事件，就是FALSE，支持事务就是true，如果开启事务要在关闭连接之前 commit() 一下，否则消息不会进入到 AMQ
        // AUTO_ACKNOWLEDGE 表示自动消费，除此还有手动消费
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("amq-demo");

        MessageProducer producer = session.createProducer(null);

        for (int i = 0; i < 100; i++) {
            TextMessage msg = session.createTextMessage(new Date()+"提供消息" + i);
            producer.send(destination, msg);
        }

        connection.close();
    }
}
