/**
 * 
 */
package activeMQ;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ptw.App;
import cn.ptw.mq.config.JmsProducer;
import cn.ptw.service.last_pub_sub_Service;
import cn.ptw.service.noLast_pub_sub_Service;
import cn.ptw.service.point_to_point_Service;

/**
 * @author Mr Zhang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // 让junit与spring环境进行整合
@SpringBootTest(classes = { App.class }) // 自动加载spring相关的配置文件
public class TestActiveMQ {
	@Autowired
	private JmsProducer jmsProducer;
	@Autowired
	private point_to_point_Service ptps;
	@Autowired
	private noLast_pub_sub_Service npss;
	@Autowired
	private last_pub_sub_Service lpss;
	
	private static final String name="Test_queue";

	/**
	 * 点对点模式生产者
	 * @throws JMSException
	 */
	@Test
	public void t1() throws JMSException {
		ptps.producer(name, "helloworld");
	}
	/**
	 * 点对点模式消费者
	 * @throws JMSException
	 * @throws InterruptedException 
	 * 启动多个消费者 会平分生产者
	 */
	@Test
	public void t2() throws JMSException, InterruptedException {
		ptps.consumer(name);
		Thread.sleep(60000);
	}
	/**
	 * 订阅模式非持久生产者
	 * @throws JMSException
	 */
	@Test
	public void t3() throws JMSException {
		npss.producer(name, "helloworld");
	}
	/**
	 * 订阅模式非持久消费者
	 * @throws JMSException
	 * @throws InterruptedException 
	 * 启动多个消费者  每个都会分到一样的生产者
	 */
	@Test
	public void t4() throws JMSException, InterruptedException {
		npss.consumer(name);
		Thread.sleep(60000);
		
	}

	
	
	/**
	 * 订阅模式持久生产者
	 * @throws JMSException
	 */
	@Test
	public void t5() throws JMSException {
		lpss.producer(name, "helloworld");
	}
	/**
	 * 订阅模式持久消费者
	 * @throws JMSException
	 * @throws InterruptedException 
	 * 先启动消费者进入    当生产者生产后  都会接受到
	 */
	@Test
	public void t6() throws JMSException, InterruptedException {
		lpss.consumer(name,"fenghung");
		Thread.sleep(10000);
		lpss.consumer(name,"jiubo");
		Thread.sleep(30000);
		
	}

	
	
	
	
	
	@Test
	public void t15() {

		jmsProducer.delaySend("topic-name", "123456", 5 * 60 * 1000L);
	}
}
