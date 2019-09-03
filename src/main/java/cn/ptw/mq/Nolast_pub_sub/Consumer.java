package cn.ptw.mq.Nolast_pub_sub;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消费者
 * 订阅模式（非持久订阅）
 */
public class Consumer {
    private static final String URL = "tcp://127.0.0.1:61616";
    //订阅模式名称
    private static final String topicName = "topic-name";
    
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection createConnection = connectionFactory.createConnection();//打开连接
        createConnection.start();
        //创建会话
        Session createSession = createConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建发布/订阅模式消息
        Topic createTopic = createSession.createTopic(topicName);//        非持久订阅
        //创建消费者
        MessageConsumer createConsumer = createSession.createConsumer(createTopic);
        //设置消费者监听
        createConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收的消息为："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}