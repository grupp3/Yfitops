package Protocoll;

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
		
		return RequestType.Unknown;
	}

}
