package Client;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Protocoll.*;

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
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
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
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
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
	public void sendRequest(String request) throws IOException {
		dataOutputStream.writeUTF(request);
	}

	// I implemented ClientMain() and main() only for testing
	public ClientMain() throws IOException, UnknownHostException {
		socket = new Socket(HOSTADRESS, PORT);
		setUpStreams();
		//sendRequest("This is a test! Message from Client.");
		//closeAllStreamsAndSocket();

	}

	public static void main(String[] args) throws IOException,
			UnknownHostException {
		try {
			final ClientMain clientMain = new ClientMain();

			clientMain.guiHandler = new GUIHandler();
			clientMain.guiHandler.setVisible(false);

			Login frame2 = new Login();
			frame2.setVisible(true);

			clientMain.socket = new Socket(HOSTADRESS, PORT); // Creates a
																// socket to
																// communicate
																// with the
																// server

			// Launches the application.
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					RequestType requestType;
					while (true) {
						try {
							String requestString = clientMain.dataInputStream
									.readUTF();
							requestType = ClientProtocol
									.GetRequestType(requestString);
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
										.GetOpponentStarting(requestString);
								boolean opponentStarting;
								if ("true" == params[1])
									opponentStarting = true;
								else
									opponentStarting = false;
								clientMain.guiHandler.NewGame(params[0],
										opponentStarting);
							default:
								System.out
										.println("Unknown message from server");
								break;
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Here comes the login information about user
	 * 
	 * @param userName
	 * @param password
	 */
	public static void loginUsr(String userName, String password) {
		// TODO Auto-generated method stub

	}
}
