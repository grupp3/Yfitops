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
		else if(tokens[0].equals("history"))
			return RequestType.HistoryRequest;
		else if(tokens[0].equals("highscore"))
			return RequestType.HighScoreRequest;
		
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
	 * Creates requeststring for sending history data
	 * @param historyData
	 * @return
	 */
	public static String CreateHistoryData(String[][] historyData){
		String outString = "historydata%";
		
		for(int i = 0; i < historyData.length; i++)
		{
			for(int j = 0; j < historyData[0].length; j++)
			{
				outString += historyData[i][j];
				outString += ";";
			}
			outString += "|";
		}
		
		return outString;
	}
	
	/**
	 * creates request string for sending high score data
	 * @param highScoreData
	 * @return
	 */
	public static String CreateHighScoreData(String[][] highScoreData){
		String outString = "scoredata%";
		for(int i = 0; i < highScoreData.length; i++)
		{
			for(int j = 0; j < highScoreData[0].length; j++)
			{
				outString += highScoreData[i][j];
				outString += ";";
			}
			outString += "|";
		}
		return outString;
	}
	
	/**
	 * Creates the string for game started notification
	 * @param opponentName
	 * @param starting
	 * @return request string
	 */
	public static String CreateGameStarted(String opponentName, Boolean starting, int timeLimit) {
		return "newgame%" + opponentName +";" + starting + ";" + timeLimit;
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
	
	/**
	 * Gets the time limit from an request string
	 * @param requestString
	 * @return time limit
	 */
	public static int GetTimelimit(String requestString){
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		return Integer.parseInt( tokens[0]);
	}
	
	/**
	 * Creates the string for an illegal move notification
	 * @return request string
	 */
	public static String CreateIllegalMove() {
		return "illegalmove";
	}

	/**
	 * Creates the string for an your turn notification
	 * @param x
	 * @param y
	 * @return request string
	 */
	public static String CreateYourTurn(int x, int y) {
		return "yourturn%" + x + ";" + y;
	}

	/**
	 * Creates the string for an game end notification
	 * @param victory
	 * @return request string
	 */
	public static String CreateGameEnd(boolean victory) {
		return "gameend%" + victory;
	}

	/**
	 * Creates the string for an time update notification
	 * @param yourtTime
	 * @param opponentTime
	 * @return request string
	 */
	public static String CreateTimeUpdate(int yourtTime, int opponentTime) {
		return "times%" + yourtTime +";" + opponentTime;
	}
	
}
