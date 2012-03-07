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
	private DBHandler dbHandler;
	private boolean gamingReady;
	private BufferedReader dataInputStream;
	private DataOutputStream dataOutputStream;
	private String userName;

	public PlayerConnection(Socket clientSocket,
			ArrayList<PlayerConnection> connectionList) throws IOException {
		socket = clientSocket;
		playerConnectionList = connectionList;
		dbHandler = DBHandler.getDatabase();
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
	public void Send(String message) {
		try {
			dataOutputStream.writeUTF(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (dataOutputStream == null)
				System.out.println("What!");
		}
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
				requestType = ServerProtocol.GetRequestType(requestString);

				if (userName != "" || requestType == RequestType.LoggingIn
						|| requestType == RequestType.Register) {
					
					switch (requestType) {
					case Unknown:
						break;
					case Register:
						this.Register(requestString);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * setter
	 * 
	 * @param gamingRedy
	 */
	public void setGamingRedy(boolean gamingRedy) {
		this.gamingReady = gamingRedy;
	}

	/**
	 * Tries to register the user and handles the result
	 * 
	 * @param requestString
	 */
	public void Register(String requestString) {
		String[] data = ServerProtocol.GetUsernamePassword(requestString);

		if (dbHandler.registerUser(data[0], data[1])) {
			this.Send(ServerProtocol.CreateLoggedIn());
			userName = data[0];
		} else {
			this.Send(ServerProtocol.CreateRegisterFailed());
		}
	}

	/**
	 * Empty constructor just for testing
	 */
	public PlayerConnection() {
		socket = new Socket();
		try {
			this.setUpStreams();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Just for testing
	 * 
	 * @param testDB
	 */
	public void addTestDBHandler(DBHandler testDB) {
		dbHandler = testDB;
	}
}
