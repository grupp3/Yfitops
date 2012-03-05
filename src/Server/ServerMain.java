/**
 * 
 */
package Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
/**
 * @author Mathulus
 *
 */
public class ServerMain {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket();
			ArrayList<PlayerConnection> conectionList = new ArrayList<PlayerConnection>();
			
			while(true){
				Socket clientSocket = ss.accept();
				
				PlayerConnection newPlayer = new PlayerConnection(clientSocket, conectionList);
				
				
				conectionList.add(newPlayer);
				
				newPlayer.start();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
