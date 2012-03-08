package ClientTest;

import static org.junit.Assert.*;

import org.junit.Test;
import Client.GUIHandler;

/**
 * Tsting GUIHandler for funtionality with other classes in the client system
 * @author Bernard
 *
 */

public class GUIHandlerTest {
	
	/**
	 * testen Lobby access
	 */
	@Test
	public void Change_GUI_window_to_Lobby(){
		GUIHandler changetolobby = new GUIHandler();
		changetolobby.btnLobby.doClick();
	}
	
	/**
	 * testen History access
	 */
	@Test
	public void Change_GUI_window_to_History(){
		GUIHandler changetohistory = new GUIHandler();
		changetohistory.btnHistory.doClick();
	}
	
	/**
	 * testen HighScore access
	 */
	@Test
	public void Change_GUI_window_to_HighScore(){
		GUIHandler changetohighscore = new GUIHandler();
		changetohighscore.btnHighScore.doClick();
	}
	
	/**
	 * testen Game access
	 */
	@Test
	public void Change_GUI_window_to_Game(){
		GUIHandler changetogame = new GUIHandler();
		changetogame.btnGame.doClick();
	}
}
