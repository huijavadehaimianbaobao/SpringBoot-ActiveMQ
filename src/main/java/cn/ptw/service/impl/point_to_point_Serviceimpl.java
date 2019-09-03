/**
 * 
 */
package cn.ptw.service.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ptw.service.point_to_point_Service;

/**
 * @author Mr Zhang
 *
 */
@Service
public class point_to_point_Serviceimpl implements point_to_point_Service	{
	
	 private static String url="tcp://127.0.0.1:61616";

	/**
	 * 
	 */
	@Override
	public void producer(String queueName,Object message) throws JMSException {
		System.err.println(url);
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
            TextMessage textMessage = createSession.createTextMessage(message.toString() + i);
            // 8、发布消息
            createProducer.send(textMessage);
            System.err.println("发送的消息为：" + message.toString() + i);
        }
        
        // 9、关闭连接
        createConnection.close();
		
	}

	
	@Override
	public void consumer(String queueName) throws JMSException {
		// TODO Auto-generated method stub
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
