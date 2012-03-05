package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Estabilishes connection with server, communicates with it, creates GUI-handler class which handles other windows
 *  
 * @author Kamil
 *
 */
public class ClientMain {
	//VARIABLES
	Socket socket;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	
	/**
	 * Sends a request to server via a socket.
	 * 
	 * The method is implemented thread-safely
	 * 
	 * @param request, the request to be sent to server
	 * @throws IOException 
	 */
	
	//METHODS
	/**
	 * Creates in- and outputstreams to recieve/send requests from/to server
	 * @throws IOException 
	 */
	public void SetUpStreams() throws IOException{
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}
		
	public void SendRequest(String request) throws IOException{
		
		
		
	}
}
