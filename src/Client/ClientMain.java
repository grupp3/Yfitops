package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Establishes connection with server, communicates with it, creates GUI-handler class which handles other windows
 *  
 * @author Kamil
 *
 */

public class ClientMain {
	//VARIABLES
	Socket socket;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	
	//CONSTANTS
	
	final static int PORT = 19345;
	final static String HOSTADRESS = "localhost";
		
	//METHODS
	
	/**
	 * Creates in- and outputstreams to recieve/send requests from/to server
	 * @throws IOException 
	 */	
	public void setUpStreams() throws IOException{
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}
		
	/**
	 * Closes outputstreams to receive/send requests from/to server
	 * Closes the socket that holds the connection to server.
	 * 
	 * @throws IOException 
	 */	
	public void closeAllStreamsAndSocket() throws IOException{
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}
	
	/**
	 * Sends a request to server via a socket.
	 * 
	 * The method is implemented thread-safely
	 * 
	 * @param request, the request to be sent to server
	 * @throws IOException 
	 */
	public void sendRequest(String request) throws IOException{
				dataOutputStream.writeUTF(request);
	}
	
	//I implemented ClientMain() and main() only for testing
	public ClientMain() throws IOException, UnknownHostException {
		socket = new Socket(HOSTADRESS, PORT);
		setUpStreams();
		sendRequest("This is a test! Message from Client.");
		closeAllStreamsAndSocket();

	}

	public static void main(String[] args) throws IOException,
			UnknownHostException {
		try {
			ClientMain cm = new ClientMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
