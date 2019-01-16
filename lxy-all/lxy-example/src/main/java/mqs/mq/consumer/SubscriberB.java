package mqs.mq.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SubscriberB implements MessageListener{
	private static final String brokerURL = "tcp://192.168.1.200:61616";
	private static ConnectionFactory factory;
	private Connection connection;
	private Session session;

	
	static {
		factory = new ActiveMQConnectionFactory(brokerURL);
	}
	
	SubscriberB() throws JMSException
	{
		connection = factory.createConnection();
		try {
			connection.start();
		} catch (JMSException jmse) {
			connection.close();
			throw jmse;
		}
		
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		
	}

	@Override
	public void onMessage(Message msg) {
		
		System.out.println("topic message receiving ...");
		
		TextMessage message = (TextMessage)msg;
		try {
			System.out.println("SubscriberB " + message.getJMSDestination() + " : " + message.getText());
			message.acknowledge();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("topic message received");
		
	}
	
	
	public void receiveMessage(MessageListener ml, String topic) throws JMSException
	{
		Topic tp = session.createTopic(topic);
		MessageConsumer consumer = session.createConsumer(tp);
		consumer.setMessageListener(ml);
		
	}
	
	public Session getSession()
	{
		return session;
	}
	
	public static void main(String[] args) throws JMSException {
		
		SubscriberB sb = new SubscriberB();
		
		sb.receiveMessage(sb, "TopicB");
		
		
		
	}
}
