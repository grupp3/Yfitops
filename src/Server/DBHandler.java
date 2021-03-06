package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that sets up a connection to the database controls that only one
 * instance of the class can be set up
 * 
 * @author Jeanie
 * 
 */
public class DBHandler {

	private static DBHandler instance;

	// membervariable for keeping the connection
	private Connection mConnection;

	// constants describing the database values
	private final String DATABASE_NAME = "yfitops";
	private final String DATABASE_USER = "root";
	private final String DATABASE_USER_PASSWORD = "";
	private final String DATABASE_SERVER = "localhost";
	private final String DATABASE_MANAGER = "mysql";

	// constants describing the tablenames in the DB
	private final String TABLE_PLAYER = "player";
	private final String TABLE_GAME = "game";

	// constants describing the columnnames in the table player
	private final String COLUMN_PLAYER_NAME = "username";
	private final String COLUMN_PLAYER_USER_PASSWORD = "user_password";

	// constants describing the columnnames in the table game
	private final String COLUMN_GAME_GAMENR = "gamenr";
	private final String COLUMN_GAME_WINNER = "winner";
	private final String COLUMN_GAME_LOSER = "loser";
	private final String COLUMN_GAME_DATETIMEGAMEPLAYED = "dateTimeGamePlayed";
	private final String COLUMN_GAME_TIMELIMIT = "timeLimit";

	/**
	 * Hidden constructor for test only
	 * 
	 * @param test
	 */
	protected DBHandler(boolean test) {
	}

	/**
	 * Hidden constructor
	 * 
	 * @author Jeanie
	 */
	private DBHandler() {
		// local varible thats holds the connection object
		mConnection = null;

		try {
			// loads the driver explicitly
			Class.forName("com.mysql.jdbc.Driver");

			// setsup the DBconnection using hardcoded values
			// jdbc:mysql://localhost/yfitops?user=root&password=""
			mConnection = DriverManager.getConnection("jdbc:"
					+ DATABASE_MANAGER + "://" + DATABASE_SERVER + "/"
					+ DATABASE_NAME + "?" + "user=" + DATABASE_USER
					+ "&password=" + DATABASE_USER_PASSWORD);

			// prints out a message if the connection succeeds
			if (!mConnection.isClosed())
				System.out.println("Successfully connected to MySQL server");

		} catch (ClassNotFoundException e) {
			// closes the application if the class couldn�t be found
			System.out.println("Couldn�t find the class.");
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			// closes the application if the DB couldn�t be found
			System.out.println("Couldn�t connect to the database.");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * Creates one and only one instance of the DBHandler
	 * 
	 * @return instance of the DBHandler
	 * @author Jeanie
	 */
	public static DBHandler getDatabase() {
		if (instance == null) {
			instance = new DBHandler();
		}
		return instance;
	}

	/**
	 * Tries to store the user + password in the database
	 * 
	 * @param userName
	 * @param password
	 * @return false if the user already exists or another error
	 * @author Pernilla
	 */
	public boolean registerUser(String userName, String password) {
		try {
			if (userName != null && userName.trim() != ""
					&& !userNameExists(userName) && password != null
					&& password.trim() != "") {
				// SQL to register the user
				String QueryString = "INSERT INTO " + TABLE_PLAYER + " VALUES ('" 
						+ userName + "', '" + password + "')";
					
				PreparedStatement statement = mConnection.prepareStatement(QueryString);
				statement.executeUpdate(QueryString);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Checks if the username exist
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	private boolean userNameExists(String userName) throws SQLException {
		Statement stmt = mConnection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_PLAYER
				+ " WHERE " + COLUMN_PLAYER_NAME + " = '" + userName + "'");
		if (!rs.first())
			return false;

		return true;
	}

	/**
	 * Inserts the winner and looser into the table game
	 * 
	 * @param winner
	 *            the username of the winner
	 * @param loser
	 *            the username of the looser
	 * @param timeLimit
	 * 			  the time limit in minutes for the game
	 * @return gameSaved true if the game is saved successfully false otherwise
	 * @author Jeanie
	 */
	public boolean saveGame(String winner, String loser, int timeLimit) {
		boolean gameSaved = false;
		PreparedStatement statement = null;
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		int count = 0;
		try {
			statement = mConnection.prepareStatement("INSERT INTO "
					+ DATABASE_NAME + "." + TABLE_GAME + " ("
					+ COLUMN_GAME_WINNER + "," + COLUMN_GAME_LOSER + "," + COLUMN_GAME_DATETIMEGAMEPLAYED + "," + COLUMN_GAME_TIMELIMIT + ")"
					+ " VALUES(?,?,?,?)");
			statement.setString(1, winner);
			statement.setString(2, loser);
			statement.setTimestamp(3, timeStamp);
			statement.setInt(4, timeLimit);
			count = statement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Problem with SQL");

			sqle.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (count == 1) {
			gameSaved = true;
		} else {
			gameSaved = false;
		}
		return gameSaved;
	}

	/**
	 * Checks the DB for matching user/pass
	 * 
	 * @param userName
	 *            - the user to login
	 * @param password
	 *            - the password associated with the user
	 * @return true if login succeeded, otherwise false
	 */
	public boolean loginCheck(String userName, String password) {
		System.out.println("Angivet pass: " + password);
		if (userName != null && password.trim() != ""
				&& password.equals(getPassword(userName)))
			return true;

		return false;
	}

	/**
	 * gets the password for the specified user
	 * 
	 * @param userName
	 *            - The user associated with the password
	 * @return - The password of the user if the user exists. Otherwise, or upon
	 *         sqlexception, an empty string.
	 */
	private String getPassword(String userName) {
		try {
			Statement statement = mConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT "
					+ COLUMN_PLAYER_USER_PASSWORD + " FROM " + TABLE_PLAYER
					+ " WHERE " + COLUMN_PLAYER_NAME + " = '" + userName + "'");
			if (!resultSet.first())
				return "";
			System.out.println("H�mtad pass fr�n DB: "
					+ resultSet.getString(COLUMN_PLAYER_USER_PASSWORD));
			return resultSet.getString(COLUMN_PLAYER_USER_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Gets history
	 * 
	 * NOT IMPLEMENTED
	 * @param userName
	 * @return
	 */
	public String[][] getHistory(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the list of highscores
	 * @return
	 */
	public String[][] getHighScore() {
		try {
			String[][] highScores = new String[10][2];
			ArrayList<String> winners = new ArrayList<String>();
			Statement statementGetAllWinners = mConnection.createStatement();
			ResultSet resultSetGetAllWinners = statementGetAllWinners.executeQuery("SELECT "
					+ COLUMN_GAME_WINNER + " FROM " + TABLE_GAME + " GROUP BY "
					+ COLUMN_GAME_WINNER);
			
			while(resultSetGetAllWinners.next())
				winners.add(resultSetGetAllWinners.getString(1));
			
			for(int i = 0; i < winners.size(); i++)
			{
				Statement statementGetNumberOfWins = mConnection.createStatement();
				
				ResultSet resultSetGetNumberOfWins = statementGetNumberOfWins.executeQuery("SELECT COUNT(" + COLUMN_GAME_WINNER + ")"
						+ " FROM " + TABLE_GAME + " WHERE " + COLUMN_GAME_WINNER + " = '" + winners.get(i) + "'");
				
				if(!resultSetGetNumberOfWins.first())
					return null;
				
				highScores[i][0] = winners.get(i);
				highScores[i][1] = resultSetGetNumberOfWins.getString(1);
			}
			return highScores;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
