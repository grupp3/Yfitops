package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	private PrintWriter dataOutputStream;
	private String userName;
	private Game currentGame = null;

	public PlayerConnection(Socket clientSocket,
			ArrayList<PlayerConnection> connectionList) throws IOException {
		socket = clientSocket;
		playerConnectionList = connectionList;
		dbHandler = DBHandler.getDatabase();
		userName = "";
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
		dataOutputStream = new PrintWriter(socket.getOutputStream(), true);

		// PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
		// true);
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(clientSocket.getInputStream()));
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

	/**
	 * Sends the game started notification to the klient
	 * 
	 * @param currentPlayer
	 * @param opponentName
	 */
	public void GameStarted(boolean currentPlayer, String opponentName) {
		gamingReady = false;
		this.Send(ServerProtocol.CreateGameStarted(opponentName, currentPlayer));
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
	public synchronized void Send(String message) {
		try {
			dataOutputStream.println(message);
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
	public void run() {
		String requestString;
		RequestType requestType;
		System.out.println("running ");
		try {
			while ((requestString = dataInputStream.readLine()) != null) {
				System.out.println("Got stuff: " + requestString);

				requestType = ServerProtocol.GetRequestType(requestString);

				if (userName != "" || requestType == RequestType.LoggingIn
						|| requestType == RequestType.Register) {

					switch (requestType) {
					case Unknown:
						break;
					case Register:
						this.Register(requestString);
						break;
					case ToggleRedy:
						this.gamingCheck();
						break;
					case NewMove:
						int[] xy = ServerProtocol.GetXY(requestString);
						this.currentGame.NewMove(userName, xy[0], xy[1]);
						break;
					case LoggingIn:
						this.login(requestString);
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
	 * @param currentGame
	 */
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	/**
	 * setter
	 * 
	 * @param gamingReady
	 */
	public void setGamingRedy(boolean gamingReady) {
		this.gamingReady = gamingReady;
	}

	/**
	 * getter
	 * 
	 */
	public boolean getGamingRedy() {
		return gamingReady;
	}

	/**
	 * Checks for other players to game with
	 */
	public void gamingCheck() {
		if (gamingReady) {
			gamingReady = false;
		} else {
			gamingReady = true;

			synchronized (playerConnectionList) {
				for (PlayerConnection p : playerConnectionList) {
					if (p.gamingReady && p != this) {
						new Game(this, p);
						break;
					}
				}
			}
		}
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

		try {
			socket = new Socket("localhost", 19345); // l�gger till argument i
														// konstruktorn - Niklas
			userName = "";
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

	/**
	 * Just for testing
	 * 
	 * @param testDB
	 */
	public void addTestDataWriter(OutputStream testOS) {
		// dataOutputStream = new DataOutputStream(testOS);
	}

	/**
	 * Just for testing
	 * 
	 * @param playerList
	 */
	public void addTestPlayerConnectionList(
			ArrayList<PlayerConnection> playerList) {
		playerConnectionList = playerList;
	}

	public void sendIllegalMove() {
		this.Send(ServerProtocol.CreateIllegalMove());
	}

	public void sendYourTurn(int x, int y) {
		this.Send(ServerProtocol.CreateYourTurn(x, y));
	}

	public void sendGameEnd(boolean victory) {
		this.Send(ServerProtocol.CreateGameEnd(victory));
	}

	/**
	 * Tries to login the player with the specified requeststring
	 * 
	 * @param requestString
	 *            - The string with the login-info
	 */

	private void login(String requestString) {
		String[] data = ServerProtocol.GetUsernamePassword(requestString);
		if (dbHandler.loginCheck(data[0], data[1])) {
			this.Send(ServerProtocol.CreateLoggedIn());
			this.userName = data[0];
		} else {
			this.Send(ServerProtocol.CreateLoginFailed());
		}
	}
}
