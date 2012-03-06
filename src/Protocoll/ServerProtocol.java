/**
 * 
 */
package Protocoll;

/**
 * @author Mathulus
 *
 */
public class ServerProtocol {

	public static RequestType GetRequestType(String requestString) {
		String[] tokens = requestString.split("%");
		
		if(tokens[0].equals("register"))
			return RequestType.Register;
		
		return RequestType.Unknown;	
	}

	public static String[] GetUsernamePassword(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		return tokens;
	}

}
