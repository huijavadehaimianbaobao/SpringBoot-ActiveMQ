package cn.ptw.mq.last_pub_pub;
import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 生产者
 *
 */
public class Producer {

    private static final String URL = "tcp://127.0.0.1:61616";
    //发布/订阅模式名称
    private static final String topicName = "topic-name";
    
    public static void main(String[] args) throws JMSException {
        
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        
        Connection createConnection = connectionFactory.createConnection();
        
        createConnection.start();
        
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //发布/订阅模式
        Topic createTopic = createSession.createTopic(topicName);
        
        MessageProducer createProducer = createSession.createProducer(createTopic);
      //设置为持久订阅模式
        createProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = createSession.createTextMessage("666 " + i);
            createProducer.send(textMessage);
            System.out.println("发送的消息为：" + textMessage.getText());
        }
        createConnection.close();
    }
}