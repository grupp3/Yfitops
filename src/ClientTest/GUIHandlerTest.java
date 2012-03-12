package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Client.ClientMain;
import Client.GUIHandler;

/**
 * Testing GUIHandler for funtionality with other classes in the client system
 * @author Bernard
 *
 */

public class GUIHandlerTest {
	
	private ClientMain mClientMain;
	
	/**
	 * testing Lobby access
	 */
	@Test
	public void Change_GUI_window_to_Lobby(){
		GUIHandler changetolobby = new GUIHandler(mClientMain);
		changetolobby.login();
	}
	
	/**
	 * testing History access
	 */
	@Test
	public void Change_GUI_window_to_History(){
		GUIHandler changetohistory = new GUIHandler(mClientMain);
		changetohistory.HistorySwitch();
	}
	
	/**
	 * testing HighScore access
	 */
	@Test
	public void Change_GUI_window_to_HighScore(){
		GUIHandler changetohighscore = new GUIHandler(mClientMain);
		changetohighscore.HighscoreSwitch();
	}
	
	/**
	 * testing Game access
	 */
	@Test
	public void Change_GUI_window_to_Game(){
		GUIHandler changetogame = new GUIHandler(mClientMain);
		changetogame.NewGame("Göran", true);
	}
}
