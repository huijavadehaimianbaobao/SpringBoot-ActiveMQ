package cn.ptw.service;

import javax.jms.JMSException;

/**
 * 点对点模式
 * 
 */
public interface point_to_point_Service {

	void producer(String queueName,Object message)throws JMSException ;


	void consumer(String queueName)throws JMSException;

}