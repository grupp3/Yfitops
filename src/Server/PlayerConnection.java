package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import Client.ClientMain;

public class PlayerConnection extends Thread{
	List<PlayerConnection> playerConnectionList;
	Socket socket;
	boolean gamingReady;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	/**
	 * Creates in- and outputstreams to recieve/send requests from/to a client
	 * 
	 * @throws IOException
	 */
	public void setUpStreams() throws IOException {
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}

	/**
	 * Closes outputstreams to receive/send requests from/to a client. Closes
	 * the socket socket that holds the connection with the client.
	 * 
	 * @throws IOException
	 */
	public void closeAllStreamsAndSocket() throws IOException {
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

	public void GameStarted(boolean currentPlayer, String string) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sends a message to a client via a socket.
	 * 
	 * The method is implemented thread-safely
	 * 
	 * @param message
	 *            , the request to be sent to client
	 * @throws IOException
	 */
	public void Send(String message) throws IOException {
		dataOutputStream.writeUTF(message);
	}

	// I implemented PlayerConnection(), main() and run() only for testing
	public PlayerConnection() throws IOException, UnknownHostException {
		socket = new Socket("localhost", 19345);
		setUpStreams();
		Send("This is a test! Message from Server.");
		closeAllStreamsAndSocket();
	}

	public void run(String[] args) throws IOException, UnknownHostException {
		try {
		ClientMain cm = new ClientMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException{
		PlayerConnection pc = new PlayerConnection();
		pc.start();
	}
}
