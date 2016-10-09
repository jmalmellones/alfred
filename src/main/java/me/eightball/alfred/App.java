package me.eightball.alfred;

import akka.actor.ActorSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App
// implements CommandLineRunner
{

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("AkkaJavaSpring");

	}


}
