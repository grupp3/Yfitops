/**
 * 
 */
package Protocoll;

/**
 * @author Mathias
 * The different types of request
 */
public enum RequestType {
	Register, Unknown, RegisterFailed, LoggedIn, LoggingIn, LoginFailed, ToggleRedy, GameStarted, NewMove, IllegalMove, YourTurn, GameEnd, TimeUpdate, HistoryData, HighScoreData, HistoryRequest, HighScoreRequest

}
