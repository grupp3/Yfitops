/**
 * 
 */
package ProtocollTest;

import static org.junit.Assert.*;
import Protocoll.*;
import org.junit.Test;

/**
 * @author Mathulus
 *
 */
public class HistoryHighscoreTest {

	@Test
	public void HistoryTransferTest() {
		boolean correct = true;
		String[][] testData =	{{"Håkan", "3min", "Win", "2020-12-12 15:20"}
	  							,{"Bert", "3min", "Loss", "2020-12-12 15:30"}
	  							,{"Håkan", "Unlimited", "Loss", "2020-12-13 11:21"}
							  	,{"Lisa", "5min", "Win", "2020-12-13 12:10"}
							  	,{"Erik", "Unlimited", "Loss", "2020-12-13 12:23"}};
		
		String requestString = ServerProtocol.CreateHistoryData(testData);
		String[][] recivedData = ClientProtocol.GetData(requestString); 
		
		for(int i = 0; i < testData.length; i++)
		{
			for(int j = 0; j < testData[0].length; j++)
			{
				if(!testData[i][j].equals(recivedData[i][j])){
					correct = false;
					break;
				}
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void HighScoreTransferTest() {
		boolean correct = true;
		String[][] testData =	{{"Håkan", "50"}
	  							,{"Bert", "47"}
	  							,{"Burt", "33"}
							  	,{"Lisa", "32"}
							  	,{"Erik", "20"}};
		
		String requestString = ServerProtocol.CreateHighScoreData(testData);
		String[][] recivedData = ClientProtocol.GetData(requestString); 
		
		for(int i = 0; i < testData.length; i++)
		{
			for(int j = 0; j < testData[0].length; j++)
			{
				if(!testData[i][j].equals(recivedData[i][j])){
					correct = false;
					break;
				}
			}
		}
		
		assertTrue(correct);
	}

}
