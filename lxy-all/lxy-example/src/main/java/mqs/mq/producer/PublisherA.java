package mqs.mq.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PublisherA {

	private static final String brokerURL = "tcp://192.168.1.202:61616";
	private static ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageProducer publisher;
	
	static {
		factory = new ActiveMQConnectionFactory(brokerURL);
	}
	
	PublisherA() throws JMSException{
		connection = factory.createConnection();
		
		try {
			connection.start();
		} catch (JMSException jmse) {
			connection.close();
			throw jmse;
		}
		
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		publisher = session.createProducer(null);
	}
	
	public void sendMessage(String msg, String topic) throws JMSException
	{
		Destination destination = session.createTopic(topic);
		
		TextMessage message = session.createTextMessage();
		message.setText(msg);
		
		publisher.send(destination, message);
		
		
	}
	
	public void close() throws JMSException
	{
		if(connection != null)
		{
			connection.close();
		}
	}
	
	
	public static void main(String[] args) throws JMSException {
		PublisherA pa = new PublisherA();
		pa.sendMessage("topica_msg1", "TopicA");
		pa.sendMessage("topica_msg2", "TopicA");
		pa.sendMessage("topica_msg3", "TopicA");
		
		pa.close();
	}
	
}


























