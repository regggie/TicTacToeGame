package com.sjsu.edu;

import java.rmi.Naming;
import java.util.Properties;



public class Server {

	/**
	 * function to start server RMI registry
	 * @param args
	 * default command line arguments
	 */
	public static void main(String[] args) throws Exception {
		
		final Properties prop = System.getProperties();
		//setting up the code base property for RMI registry
		prop.setProperty("java.rmi.server.codebase", "file:E:/Sunbeam/JAVA/eclipse/TicTacToe/bin/");
		//setting up host name  to be accessed
		System.setProperty("java.rmi.server.hostname","10.189.56.49");
		final TicTacToeImpl obj = new TicTacToeImpl(); 
		Runtime.getRuntime().exec("rmiregistry");
		Naming.rebind("dac",obj);
		
	}

}
