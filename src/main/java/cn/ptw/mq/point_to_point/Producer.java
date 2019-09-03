package cn.ptw.mq.point_to_point;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 *点对点模式
 */
@Service
public class Producer 
{
    private static final String url = "tcp://127.0.0.1:61616";
    
    private static final String queueName = "queue-text";
    
    public static void main( String[] args ) throws JMSException
    {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2、创建连接对象
        Connection createConnection = connectionFactory.createConnection();
        // 3、启动连接
        createConnection.start();
        // 4、创建会话
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、创建消息目标
        Queue createQueue = createSession.createQueue(queueName);
        // 6、创建生产者
        MessageProducer createProducer = createSession.createProducer(createQueue);
        for (int i=0;i<100;++i) {
            // 7、创建消息
            TextMessage textMessage = createSession.createTextMessage("666  " + i);
            // 8、发布消息
            createProducer.send(textMessage);
            System.out.println("发送的消息为：" + "777  " + i);
        }
        
        // 9、关闭连接
        createConnection.close();
        
    }
}