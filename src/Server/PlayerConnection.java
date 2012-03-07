package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import Protocoll.*;

import Client.ClientMain;

public class PlayerConnection extends Thread {
	private List<PlayerConnection> playerConnectionList;
	private Socket socket;
	private boolean gamingReady;
	private BufferedReader dataInputStream;
	private DataOutputStream dataOutputStream;
	private String userName;

	public PlayerConnection(Socket clientSocket,
			ArrayList<PlayerConnection> connectionList) throws IOException {
		socket = clientSocket;
		playerConnectionList = connectionList;
		setUpStreams();

		this.start();
	}

	/**
	 * Creates in- and outputstreams to recieve/send requests from/to a client
	 * 
	 * @throws IOException
	 */
	public void setUpStreams() throws IOException {
		dataInputStream = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
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

	/**
	 * Listens to the socket
	 * 
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public void run(String[] args) throws IOException, UnknownHostException {
		String requestString;
		RequestType requestType;

		try {
			while (true) {
				requestString = dataInputStream.readLine();
				requestType = ClientProtocol.GetRequestType(requestString);

				switch (requestType) {
				case Unknown:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Empty constructor just for testing
	 */
	public PlayerConnection() {
	}

	public String getUserName() {
		return userName;
	}

	public void setGamingRedy(boolean gamingRedy) {
		this.gamingReady = gamingRedy;
	}
}
