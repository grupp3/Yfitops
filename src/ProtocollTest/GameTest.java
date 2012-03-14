/**
 * 
 */
package ProtocollTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Protocoll.ClientProtocol;
import Protocoll.RequestType;
import Protocoll.ServerProtocol;

/**
 * @author Mathulus
 *
 */
public class GameTest {

	@Test
	public void ToggleGameingRedyRequestStringTest() {
		String requestString = ClientProtocol.CreateToggleGamingReady(3);
		
		assertEquals("gamingredy%3", requestString);
	}
	
	@Test
	public void ToggleGameingRedyCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateToggleGamingReady(7);
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.ToggleRedy, requestType);
	}
	
	@Test
	public void GameStartedRequestStringTest() {
		String requestString = ServerProtocol.CreateGameStarted("håKan", true,4);
		
		assertEquals("newgame%håKan;"+ true +";4" , requestString);
	}
	
	@Test
	public void GameStartedCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateGameStarted("håKan", false,7);
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.GameStarted, requestType);
	}
	
	@Test
	public void GameStartedCorrectValues()
	{
		Boolean starting = true;
		String opponentName = "MarkUs15";
		String requestString = ServerProtocol.CreateGameStarted(opponentName, starting,3);
		
		String[] recevedValues = ClientProtocol.GetOpponentStartingTime(requestString);
		
		assertEquals("wrong oponent name", opponentName, recevedValues[0]);
		assertEquals("wrong firstplayer state", starting.toString(), recevedValues[1]);
		assertEquals("wrong time limit", "3", recevedValues[2]);
	}
	
	@Test
	public void NewMoveRequestStringTest() {
		String requestString = ClientProtocol.CreateNewMove(10, 3);
		
		assertEquals("newmove%10;3", requestString);
	}
	
	@Test
	public void NewMoveCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateNewMove(11, 2);
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.NewMove, requestType);
	}
	
	@Test
	public void NewMoveCorrectValues()
	{
		int x = 9;
		int y = 13;
		String requestString = ClientProtocol.CreateNewMove(x, y);
		
		int[] recevedValues = ServerProtocol.GetXY(requestString);
		
		assertEquals("wrong x koordinate", x, recevedValues[0]);
		assertEquals("wrong y koordinate", y, recevedValues[1]);
	}
	
	@Test
	public void IllegalMoveRequestStringTest() {
		String requestString = ServerProtocol.CreateIllegalMove();
		
		assertEquals("illegalmove", requestString);
	}
	
	@Test
	public void IllegalMoveCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateIllegalMove();
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.IllegalMove, requestType);
	}
	
	@Test
	public void YourTurnRequestStringTest() {
		String requestString = ServerProtocol.CreateYourTurn(3, 4);
		
		assertEquals("yourturn%3;4", requestString);
	}
	
	@Test
	public void YourTurnCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateYourTurn(3, 14);
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.YourTurn, requestType);
	}
	
	@Test
	public void YourTurnCorrectValues()
	{
		int x = 13;
		int y = 4;
		String requestString = ServerProtocol.CreateYourTurn(x, y);
		
		int[] recevedValues = ClientProtocol.GetXY(requestString);
		
		assertEquals("wrong x value", x, recevedValues[0]);
		assertEquals("wrong y value", y, recevedValues[1]);
	}
	
	@Test
	public void GameEndRequestStringTest() {
		String requestString = ServerProtocol.CreateGameEnd(false);
		
		assertEquals("gameend%false", requestString);
	}
	
	@Test
	public void GameEndCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateGameEnd(false);
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.GameEnd, requestType);
	}
	
	@Test
	public void GameEndCorrectValues()
	{
		Boolean won = true;
		String requestString = ServerProtocol.CreateGameEnd(won);
		
		boolean recevedValue = ClientProtocol.GetVictory(requestString);
		
		assertEquals("wrong victory value", won, recevedValue);
	}
}
