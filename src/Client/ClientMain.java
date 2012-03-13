package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Protocoll.ClientProtocol;
import Protocoll.RequestType;

/**
 * Establishes connection with server, communicates with it, creates GUI-handler
 * class which handles other windows
 * 
 * @author Kamil
 * 
 */

public class ClientMain {

	// VARIABLES
	Socket socket;
	BufferedReader dataInputStream;
	PrintWriter dataOutputStream;
	GUIHandler guiHandler;
	// CONSTANTS

	final static int PORT = 19345;
	final static String HOSTADRESS = "localhost";

	// METHODS

	/**
	 * Creates in- and outputstreams to recieve/send requests from/to server
	 * 
	 * @throws IOException
	 */
	public void setUpStreams() throws IOException {
		dataInputStream = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		dataOutputStream = new PrintWriter(socket.getOutputStream(), true);
		
		
	}

	/**
	 * Closes outputstreams to receive/send requests from/to server Closes the
	 * socket that holds the connection to server.
	 * 
	 * @throws IOException
	 */
	public void closeAllStreamsAndSocket() throws IOException {
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

	/**
	 * Sends a request to server via a socket.
	 * 
	 * The method is implemented thread-safely
	 * 
	 * @param request
	 *            , the request to be sent to server
	 * @throws IOException
	 */
	public void sendRequest(String request) {
		System.out.println("Skicka: " + request);
		dataOutputStream.println(request);
	}

	// I implemented ClientMain() and main() only for testing
	public ClientMain() throws IOException, UnknownHostException {
		socket = new Socket(HOSTADRESS, PORT);
		setUpStreams();
		// sendRequest("This is a test! Message from Client.");
		// closeAllStreamsAndSocket();

	}

	public static void main(String[] args) {

		ClientMain clientMain;
		try {
			clientMain = new ClientMain();
		

		clientMain.guiHandler = new GUIHandler(clientMain);

		RequestType requestType;
		String requestString;

		while ((requestString = clientMain.dataInputStream.readLine()) != null) {
			requestType = ClientProtocol.GetRequestType(requestString);
			switch (requestType) {
			case RegisterFailed:
				clientMain.guiHandler.registerFailed();
				break;
			case LoggedIn:
				clientMain.guiHandler.login();
				break;
			case LoginFailed:
				clientMain.guiHandler.LoginFailed();
				break;
			case GameStarted:
				String[] params = ClientProtocol
						.GetOpponentStartingTime(requestString);
				boolean opponentStarting;
				if ("true" == params[1])
					opponentStarting = true;
				else
					opponentStarting = false;
				clientMain.guiHandler.NewGame(params[0], opponentStarting);
				break;
			case YourTurn:
				clientMain.guiHandler.yourTurn(ClientProtocol.GetXY(requestString));
				break;
			default:
				System.out.println("Unknown message from server");
				break;
			}
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Here comes the login information about user
	 * 
	 * @param userName
	 * @param password
	 */
	public void loginUsr(String userName, String password) {
		String requestString = ClientProtocol.CreateLogin(userName, password);
		this.sendRequest(requestString);
	}

	/**
	 * fetches the gameReady string from clientProtocol sends the string to the
	 * server
	 * 
	 * NOT IMPLEMENTED YET catching the IOException
	 * 
	 * @author Jeanie
	 * @param timeLimit
	 */
	public void ToggleGamingReady(int timeLimit) {
		String gameReady = ClientProtocol.CreateToggleGamingReady(timeLimit);
		sendRequest(gameReady);

	}
}
