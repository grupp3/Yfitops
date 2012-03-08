
	package Server;

	import java.sql.Connection;
	import java.sql.DriverManager;
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
		 * Hidden constructor
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
				//closes the application if the class couldn´t be found
				System.out.println("Couldn´t find the class.");
				e.printStackTrace();
				System.exit(0);
			} catch (SQLException e) {
				//closes the application if the DB couldn´t be found
				System.out.println("Couldn´t connect to the database.");
				e.printStackTrace();
				System.exit(0);
			} finally {
				//catches SQLexception if it´s thrown 
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
		 * 					
		 */
		public static DBHandler getInstance(){
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
		 */
		public boolean registerUser(String userName, String password) {
			// TODO Auto-generated method stub
			return false;
		}
		
		
		/**
		 * main method that lounches the application
		 * is left to try the class out
		 * @param args
		 */
		/*public static void main(String[] args) {
			// Get an instance of this databasehandler
			DBHandler databaseHandler = DBHandler.getInstance();
		}*/
		
	}

	
	
	
	
	
	
	

