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
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import cn.ptw.service.noLast_pub_sub_Service;

/**
 * @author Mr Zhang 非持久性订阅模式
 */
@Service
public class noLast_pub_sub_Serviceimpl implements noLast_pub_sub_Service {
	private static String URL = "tcp://127.0.0.1:61616";

	@Override
	public void producer(String queueName, Object message) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

		Connection createConnection = connectionFactory.createConnection();

		createConnection.start();

		Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 发布/订阅模式
		Topic createTopic = createSession.createTopic(queueName);

		MessageProducer createProducer = createSession.createProducer(createTopic);

		for (int i = 0; i < 100; i++) {
			TextMessage textMessage = createSession.createTextMessage(message.toString() + i);
			createProducer.send(textMessage);
			System.out.println("发送的消息为：" + textMessage.getText());
		}
		createConnection.close();
	}

	@Override
	public void consumer(String queueName) throws JMSException {
		// 创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		// 创建连接
		Connection createConnection = connectionFactory.createConnection();// 打开连接
		createConnection.start();
		// 创建会话
		Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建发布/订阅模式消息
		Topic createTopic = createSession.createTopic(queueName);// 非持久订阅
		// 创建消费者
		MessageConsumer createConsumer = createSession.createConsumer(createTopic);
		// 设置消费者监听
		createConsumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收的消息为：" + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
