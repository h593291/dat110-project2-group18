package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting...");
		
		Client display = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		display.connect();
		display.createTopic(Common.TEMPTOPIC);
		display.subscribe(Common.TEMPTOPIC);
		
		for(int i = 0; i < COUNT; i++) {
			Message message = display.receive();
			if(!(message instanceof PublishMsg)) {
				throw new RuntimeException("Message was not a publish message.");
			}
			PublishMsg print = (PublishMsg)message;
			System.out.println(print.getMessage());	
		}
		display.unsubscribe(Common.TEMPTOPIC);
		display.disconnect();
		
		System.out.println("Display stopping...");
		
	}
}
