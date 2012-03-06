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
 * Haven't defined which port number yet.
 */
public class ServerMain {
	final static int PORT = 19345;
			
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(PORT);
			ArrayList<PlayerConnection> connectionList = new ArrayList<PlayerConnection>();
			
			while(true){
				Socket clientSocket = ss.accept();
				
				PlayerConnection newPlayer = new PlayerConnection(clientSocket, connectionList);
				
				synchronized(connectionList){
					connectionList.add(newPlayer);
				
					newPlayer.start();
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
