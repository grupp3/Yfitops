/**
 * 
 */
package Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
/**
 * @author Mathias
 * Listens for connecting clients, starts a listening thread for each and puts them into a list.
 * 
 * Port 19345.
 */
public class ServerMain {
	final static int PORT = 19345;
			
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(PORT);
			ArrayList<PlayerConnection> connectionList = new ArrayList<PlayerConnection>();
			
			while(true){
				System.out.println("listening");
				Socket clientSocket = ss.accept();
				
				PlayerConnection newPlayer = new PlayerConnection(clientSocket, connectionList);
				
				synchronized(connectionList){
					connectionList.add(newPlayer);
				}
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
