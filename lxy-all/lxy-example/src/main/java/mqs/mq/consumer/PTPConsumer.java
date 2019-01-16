package mqs.mq.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PTPConsumer implements MessageListener{

	private static String brokerURL = "tcp://192.168.1.200:61616";
	private static ConnectionFactory factory;
	private Connection connection;
	private Session session;
	
	PTPConsumer() throws JMSException{
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		try{
			connection.start();
		} catch (JMSException jmse)
		{
			connection.close();
			throw jmse;
		}
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		
	}

	public void close() throws JMSException
	{
		if(connection != null)
		{
			connection.close();
		}
	}
	
	public Session getSession()
	{
		return this.session;
	}
	
	public static void main(String[] args) throws JMSException {
		PTPConsumer ptpConsumer = new PTPConsumer();
		Destination destination = ptpConsumer.getSession().createQueue("firstQueue");
		MessageConsumer consumer = ptpConsumer.getSession().createConsumer(destination);
		consumer.setMessageListener(ptpConsumer);
	    
	}
	
	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		
		System.out.println("receiving message ...");
		
		String name = "";
		String age = "";
		String gender = "";
		
		MapMessage map = (MapMessage)msg;
		try {
			name = map.getString("name");
			age = map.getString("age");
			gender = map.getString("gender");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			msg.acknowledge();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("message received ...");
		System.out.println(name + " : " + age + " : " + gender);
		
		
		
	}
	
}
















