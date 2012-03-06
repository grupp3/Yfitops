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

}
