package Protocoll;

/**
 * Has all the methods the client need for creating and interpreting request strings
 * @author Mathias
 *
 */
public class ClientProtocol {

	/**
	 * Creates a request string for registrating a new user
	 * @param userName
	 * @param password
	 * @return request string
	 */
	public static String CreateRegister(String userName, String password) {
		return "register%" + userName +";" + password;
	}

	/**
	 * Gets the request type from the request string
	 * @param requestString
	 * @return request type
	 */
	public static RequestType GetRequestType(String requestString) {
		String[] tokens = requestString.split("%");
		
		if(tokens[0].equals("registerfailed"))
			return RequestType.RegisterFailed;
		else if(tokens[0].equals("loggedin"))
			return RequestType.LoggedIn;
		else if(tokens[0].equals("loginfailed"))
			return RequestType.LoginFailed;
		
		return RequestType.Unknown;
	}
	
	/**
	 * Creates a request string for logging in a user
	 * @param userName
	 * @param password
	 * @return request string
	 */
	public static String CreateLogin(String userName, String password) {
		return "login%" + userName +";" + password;
	}

}
