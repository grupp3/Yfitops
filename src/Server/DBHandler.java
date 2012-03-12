
	package Server;

	import java.sql.Connection;
	import java.sql.DriverManager;
    import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
    import java.sql.Statement;

	/**
	 * Class that sets up a connection to the database
	 * controls that only one instance of the class can be set up
	 * 
	 * @author Jeanie
	 *
	 */
	public class DBHandler {
		
		private static DBHandler instance;
		
		//membervariable for keeping the connection
		private Connection mConnection;
		
		//constants describing the database values
		private final String DATABASE_NAME = "yfitops";
		private final String DATABASE_USER = "root";
		private final String DATABASE_USER_PASSWORD = "";
		private final String DATABASE_SERVER = "localhost";
		private final String DATABASE_MANAGER = "mysql";
		
		//constants describing the tablenames in the DB
		private final String TABLE_PLAYER = "player";
		private final String TABLE_GAME = "game";
		
		//constants describing the columnnames in the table player
		private final String COLUMN_PLAYER_NAME = "username";
		private final String COLUMN_PLAYER_USER_PASSWORD = "user_password";
		
		//constants describing the columnnames in the table game
		private final String COLUMN_GAME_GAMENR = "gamenr";
		private final String COLUMN_GAME_WINNER = "winner";
		private final String COLUMN_GAME_LOSER = "loser";
		private final String COLUMN_GAME_DATETIMEGAMEPLAYED = "dateTimeGamePlayed";
		private final String COLUMN_GAME_TIMELIMIT = "timeLimit";
		
		
		/**
		 * Hidden constructor for test only
		 * @param test
		 */
		protected DBHandler(boolean test) {
		}
		
		/**
		 * Hidden constructor
		 * @author Jeanie
		 */
		private DBHandler() {
			//local varible thats holds the connection object
			mConnection = null;
			
			try {
				//loads the driver explicitly
				Class.forName("com.mysql.jdbc.Driver");
		
			// setsup the DBconnection using hardcoded values
			// jdbc:mysql://localhost/yfitops?user=root&password=""
			mConnection = DriverManager.getConnection("jdbc:"
					+ DATABASE_MANAGER + "://" + DATABASE_SERVER + "/"
					+ DATABASE_NAME + "?" + "user=" + DATABASE_USER
					+ "&password=" + DATABASE_USER_PASSWORD);
			
			//prints out a message if the connection succeeds
			if (!mConnection.isClosed())
				System.out.println("Successfully connected to MySQL server");

			} catch (ClassNotFoundException e) {
				//closes the application if the class couldn�t be found
				System.out.println("Couldn�t find the class.");
				e.printStackTrace();
				System.exit(0);
			} catch (SQLException e) {
				//closes the application if the DB couldn�t be found
				System.out.println("Couldn�t connect to the database.");
				e.printStackTrace();
				System.exit(0);
			} finally {
				//catches SQLexception if it�s thrown 
				//when the connection shuts down
		      try {
		        if(mConnection != null)
		        	mConnection.close();
		      } catch(SQLException e) {}
		    }
			
		}
		/**
		 * Creates one and only one instance 
		 * of the DBHandler
		 * 
		 * @return instance of the DBHandler
		 * @author Jeanie					
		 */
		public static DBHandler getDatabase(){
			if (instance == null){
				instance = new DBHandler();
			}
			return instance;
		}
		
		

		/**
		 * Tries to store the user + password in the database
		 * @param userName
		 * @param password
		 * @return false if the user already exists or another error
		 * @author Pernilla
		 */
		public boolean registerUser(String userName, String password) {
			boolean success = false;
			// !userNameExists(userName))
			
			if(userName != null || userName != "" && !userNameExists(userName)){
				// SQL to register the user
				try {
				String QueryString = "INSERT INTO TABLE_PLAYER" +
						"(COLUMN_PLAYER_NAME, COLUMN_PLAYER_USER_PASSWORD)" +" VALUES(?,?)";
			           
						PreparedStatement pstmt = mConnection.prepareStatement(QueryString);
						pstmt.setString(1, userName);
						pstmt.setString(2, password);
						
						pstmt.executeUpdate(QueryString);
					} catch (SQLException e) {
						System.out.println("Problem with SQL");
					}
				
				success = true;
			}
			return success;
		}
		private boolean userNameExists(String userName) {
			boolean userExists = false;
			try{
			Statement stmt = mConnection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COLUMN_PLAYER_NAME");
			int numberOfResult =0;
			String Value = rs.getString(numberOfResult);
			
			for(int i = 0; i == numberOfResult; i++){
			i++;
				if(Value == userName){
				userExists = true;
			}
			else{
				userExists = false;
			}
			}
			
			}
			catch(SQLException e) {
				
			}
			return userExists;
		}

		/**
		 * Inserts the winner and looser into the table game
		 * 
		 * NOT IMPLEMENETED YET:
		 * 						inserting date and time for the game
		 * 						inserting timelimit for the game
		 * 
		 * @param winner
		 * 				the username of the winner
		 * @param loser
		 * 				the username of the looser
		 * @return gameSaved
		 * 					true if the game is saved successfully
		 * 					false otherwise
		 * @author Jeanie
		 */
		public boolean saveGame(String winner, String loser) {
			boolean gameSaved = false;
		    PreparedStatement statement = null;
		    int count = 0;
			try {
				statement = mConnection.prepareStatement (
				               "INSERT INTO " + DATABASE_NAME + "." + TABLE_GAME 
				               + " (" + COLUMN_GAME_WINNER + "," + COLUMN_GAME_LOSER + ")"
							   + " VALUES(?,?)");
				statement.setString (1, winner);
				statement.setString (2, loser);
				count = statement.executeUpdate ();
				
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
			if (count == 1){
				gameSaved = true;
			} else {
				gameSaved = false;
			}
			return gameSaved ;
		}
		/**
	 	 * Checks the DB for matching user/pass
	 	 * @param userName - the user to login
	 	 * @param password - the password associated with the user
	 	 * @return true if login succeeded, otherwise false
	 	 */
	 	 public boolean loginCheck(String userName, String password){
	 		 if(userName != null && password.trim() != "" && password == getPassword(userName))
	 	     return true;
	 	  
	 	     return false;  
	 	  }
	 	/**
	 	  * gets the password for the specified user
	 	  * @param userName - The user associated with the password
	 	  * @return - The password of the user if the user exists. Otherwise, or upon sqlexception, an empty string.
	 	  */
	 	   private String getPassword(String userName){
	 	    try {
	 	     Statement statement = mConnection.createStatement();
	 	     ResultSet resultSet = statement.executeQuery("SELECT " + COLUMN_PLAYER_USER_PASSWORD + 
	 	     "FROM " + TABLE_PLAYER + " WHERE " + COLUMN_PLAYER_NAME + "=" + userName);
	 	     if(!resultSet.first())
	 	     return "";
	 	     return resultSet.getString(COLUMN_PLAYER_USER_PASSWORD);
	 	     } catch (SQLException e) {
	 	     return "";
	 	     }
	 	     }
		
		/**
		 * main method that launches the application
		 * is left to try the class out
		 * @param args
		 */
		/*public static void main(String[] args) {
			// Get an instance of this databasehandler
			DBHandler databaseHandler = DBHandler.getDatabase();
		}*/
		
	}

	
	
	
	
	
	
	

