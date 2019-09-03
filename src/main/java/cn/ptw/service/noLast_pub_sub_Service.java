/**
 * 
 */
package cn.ptw.service;

import javax.jms.JMSException;

/**
 * @author Mr Zhang
 *
 */
public interface noLast_pub_sub_Service {
	

	void producer(String queueName, Object message) throws JMSException;


	void consumer(String queueName) throws JMSException;
}
