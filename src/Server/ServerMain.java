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
			ArrayList<PlayerConection> conectionList = new ArrayList<PlayerConection>();
			
			while(true){
				Socket clientSocket = ss.accept();
				
				PlayerConection newPlayer = new PlayerConection(clientSocket, conectionList);
				
				
				conectionList.add(newPlayer);
				
				newPlayer.start();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
