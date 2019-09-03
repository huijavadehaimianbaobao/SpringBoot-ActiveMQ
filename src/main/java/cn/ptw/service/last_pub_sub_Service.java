/**
 * 
 */
package cn.ptw.service;

import javax.jms.JMSException;

/**
 * @author Mr Zhang
 *
 */
public interface last_pub_sub_Service {
	/**
	 * 
	 * @param queueName
	 * @param message
	 * @throws JMSException
	 */
	void producer(String queueName, Object message) throws JMSException;
	
	void consumer(String queueName,String clientId) throws JMSException;
}
