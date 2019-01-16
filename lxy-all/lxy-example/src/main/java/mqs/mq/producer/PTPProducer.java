package mqs.mq.producer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


public class PTPProducer {

	private static String brokerURL = "tcp://192.168.1.200:61616";
	private static ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	
	PTPProducer() throws JMSException{
		
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		
		try{
			connection.start();
		} catch (JMSException jmse){
			connection.close();
			throw jmse;
		}
		
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		producer = session.createProducer(null);
	}
	
	
	public void sendMessage(Map<String, String> msg) throws JMSException
	{
		Destination destination = session.createQueue("firstQueue");
		
		MapMessage message = session.createMapMessage();
		Set<String> keys = msg.keySet();
		for(String key : keys)
		{
			message.setString(key, msg.get(key));
		}
		System.out.println("start to send message ...");
		producer.send(destination, message);
		System.out.println("message sended ...");
	}
	
	public void close() throws JMSException
	{
		if(connection != null)
		{
			connection.close();
		}
	}
	
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		PTPProducer ptpProducer = new PTPProducer();
		
		Map map = new HashMap<String, String>();
		map.put("name", "lxy");
		map.put("age", "27");
		map.put("gender", "male");
		
		ptpProducer.sendMessage(map);	
		
		//Thread.sleep(5000);
		ptpProducer.close();
	}

}
