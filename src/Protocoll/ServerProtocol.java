/**
 * 
 */
package Protocoll;

/**
 * Has all the methods the server need for creating and interpreting request strings
 * @author Mathias
 *
 */
public class ServerProtocol {

	/**
	 * Gets the request type from the request string
	 * @param requestString
	 * @return request type
	 */
	public static RequestType GetRequestType(String requestString) {
		String[] tokens = requestString.split("%");
		
		if(tokens[0].equals("register"))
			return RequestType.Register;
		else if(tokens[0].equals("login"))
				return RequestType.LoggingIn;
		else if(tokens[0].equals("gamingredy"))
			return RequestType.ToggleRedy;
		else if(tokens[0].equals("newmove"))
			return RequestType.NewMove;	
		
		return RequestType.Unknown;	
	}
	
	/**
	 * Gets username and password from a request string
	 * @param requestString
	 * @return username[0] password[1]
	 */
	public static String[] GetUsernamePassword(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		return tokens;
	}

	/**
	 * Creates the string for a register failed response
	 * @return request string
	 */
	public static String CreateRegisterFailed() {
		return "registerfailed";
	}

	/**
	 * Creates the string for a logged in response
	 * @return request string
	 */
	public static String CreateLoggedIn() {
		return "loggedin";
	}

	/**
	 * Creates the string for a login failed response
	 * @return request string
	 */
	public static String CreateLoginFailed() {
		return "loginfailed";
	}
	
	
	/**
	 * Creates the string for game started notification
	 * @param opponentName
	 * @param starting
	 * @return request string
	 */
	public static String CreateGameStarted(String opponentName, Boolean starting) {
		return "newgame%" + opponentName +";" + starting;
	}
	
	/**
	 * Gets the xy positions from a new move request string
	 * @param requestString
	 * @return xy values
	 */
	public static int[] GetXY(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		int[] outValues = new int[2];
		
		outValues[0] = Integer.parseInt( tokens[0]);
		outValues[1] = Integer.parseInt( tokens[1]);
		
		return outValues;
	}
	
}
