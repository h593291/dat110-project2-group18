package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		System.out.println("Temperature device starting...");
		
		Client sensor = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		TemperatureSensor temperature = new TemperatureSensor();
		sensor.connect();
		sensor.createTopic(Common.TEMPTOPIC);
		
		for(int i = 0; i < COUNT; i++) {
			String tempRead = Integer.toString(temperature.read());
			sensor.publish(Common.TEMPTOPIC, tempRead);
			try {
				Thread.sleep(422);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sensor.disconnect();
		
		System.out.println("Temperature device stopping...");

	}
}
