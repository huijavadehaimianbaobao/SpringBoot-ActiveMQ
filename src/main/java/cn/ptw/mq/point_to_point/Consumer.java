package cn.ptw.mq.point_to_point;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * Hello world!
 * 点对点模式
 */
@Service
public class Consumer {
    //设置连接地址
    private static final String url = "tcp://127.0.0.1:61616";
    //设置消息队列名称
    private static final String queueName = "queue-text";
    
    public static void main(String[] args) throws JMSException {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2、创建连接对象
        Connection createConnection = connectionFactory.createConnection();
        // 3、启动连接
        createConnection.start();
        // 4、创建会话　　   createSession第一个参数表示是否支持事务，第二个参数是客户端接收确认模式，Session.AUTO_ACKNOWLEDGE是自动确认，Session.CLIENT_ACKNOWLEDGE 客户端通过调用消息的 acknowledge 方法签收消息。
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
        // 5、创建消息目标 
         Queue createQueue = createSession.createQueue(queueName); 
         // 6 、创建消费者 
         MessageConsumer createConsumer = createSession.createConsumer(createQueue); 
         // 7、设置消费者监听 
         createConsumer.setMessageListener(new MessageListener() { 
             @Override 
             public void onMessage(Message message) { 
                 TextMessage textMessage = (TextMessage) message; 
                 try { 
                     System.out.println("接收的消息为" + textMessage.getText()); 
                 } catch (JMSException e) {
                     // TODO Auto-generated catch block e.printStackTrace();
                 } 
            } 
         });
    }
    public void xiaofei() throws JMSException {
    	String url = "tcp://127.0.0.1:61616";
    	String queueName = "queue-text";
    	System.out.println("153456");
    	// 1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        // 2、创建连接对象
        Connection createConnection = connectionFactory.createConnection();
        // 3、启动连接
        createConnection.start();
        // 4、创建会话　　   createSession第一个参数表示是否支持事务，第二个参数是客户端接收确认模式，Session.AUTO_ACKNOWLEDGE是自动确认，Session.CLIENT_ACKNOWLEDGE 客户端通过调用消息的 acknowledge 方法签收消息。
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
        // 5、创建消息目标 
         Queue createQueue = createSession.createQueue(queueName); 
         // 6 、创建消费者 
         MessageConsumer createConsumer = createSession.createConsumer(createQueue); 
         // 7、设置消费者监听 
         createConsumer.setMessageListener(new MessageListener() { 
             @Override 
             public void onMessage(Message message) { 
                 TextMessage textMessage = (TextMessage) message; 
                 try { 
                     System.out.println("接收的消息为" + textMessage.getText()); 
                 } catch (JMSException e) {
                     // TODO Auto-generated catch block e.printStackTrace();
                 } 
            } 
         });
    }
    
}