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
		else if(tokens[0].equals("newgame"))
			return RequestType.GameStarted;
		else if(tokens[0].equals("illegalmove"))
			return RequestType.IllegalMove;
		else if(tokens[0].equals("yourturn"))
			return RequestType.YourTurn;
		else if(tokens[0].equals("gameend"))
			return RequestType.GameEnd;
		else if(tokens[0].endsWith("times"))
			return RequestType.TimeUpdate;
		else if(tokens[0].endsWith("historydata"))
			return RequestType.HistoryData;
		else if(tokens[0].endsWith("scoredata"))
			return RequestType.HighScoreData;
		
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

	/**
	 * Creates a request string for toggling gaming ready
	 * @return request string
	 */
	public static String CreateToggleGamingReady(int timeLimit) {
		return "gamingredy%" + timeLimit;
	}

	/**
	 * Gets opponents name and if you're the starting player from a request string
	 * @param requestString
	 * @return username[0] password[1]
	 */
	public static String[] GetOpponentStartingTime(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		return tokens;
	}
	
	/**
	 * Creates the request string for sending a new move
	 * @param x
	 * @param y
	 * @return request string
	 */
	public static String CreateNewMove(int x, int y) {
		return "newmove%" + x +";" + y;
	}

	/**
	 * gets the xy cordinates
	 * @param requestString
	 * @return
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
	 * gets the times
	 * @param requestString
	 * @return
	 */
	public static int[] GetTimes(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		int[] outValues = new int[2];
		
		outValues[0] = Integer.parseInt( tokens[0]);
		outValues[1] = Integer.parseInt( tokens[1]);
		
		return outValues;
	}

	/**
	 * gets if you won the game
	 * @param requestString
	 * @return victory
	 */
	public static boolean GetVictory(String requestString) {
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split(";");
		
		return Boolean.parseBoolean(tokens[0]);
	}

	/**
	 * Gets list data from request string
	 * @param requestString
	 * @return
	 */
	public static String[][] GetData(String requestString) {
		System.out.println(requestString);
		String[] tokens = requestString.split("%");
		tokens = tokens[1].split("[|]");
		String[] innerTokens = tokens[0].split(";");
		
		String[][] outData = new String[tokens.length][innerTokens.length];
		
		System.out.println("token1: " + tokens[0]);
		
		for(int i = 0; i < outData.length; i++)
		{
			if(i != 0){
				innerTokens = tokens[i].split(";");
			}
			System.out.println(innerTokens[0]);
			for(int j = 0; j < outData[0].length; j++)
			{
				outData[i][j] = innerTokens[j];
			}
		}
		return outData;
	}
	
	/**
	 * Creates the request string for requesting history data
	 * @return
	 */
	public String CreateHistoryRequest(){
		return "history";
	}
	
	/**
	 * Creates the request string for requesting high score data
	 * @return
	 */
	public String CreateHighScoreRequest(){
		return "highscore";
	}
}
