/**
 * 
 */
package ProtocollTest;

import static org.junit.Assert.*;

import org.junit.Test;
import Protocoll.*;

/**
 * @author Mathulus
 *
 */
public class RegisterAndLoginTest {

	@Test
	public void RegisterRequestStringTest() {
		String requestString = ClientProtocol.CreateRegister("håKan", "lövbiff13");
		
		assertEquals("register%håKan;lövbiff13", requestString);
	}
	
	@Test
	public void RegisterCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateRegister("håKan", "lövbiFF13");
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.Register, requestType);
	}
	
	@Test
	public void RegisterCorrectValues()
	{
		String password = "löavefBBiiFF";
		String userName = "MarkUs15";
		String requestString = ClientProtocol.CreateRegister(userName, password);
		
		String[] recevedValues = ServerProtocol.GetUsernamePassword(requestString);
		
		assertEquals("wrong username", userName, recevedValues[0]);
		assertEquals("wrong password", password, recevedValues[1]);
	}
	
	@Test
	public void RegisterFailedRequestStringTest()
	{
		String requestString = ServerProtocol.CreateRegisterFailed();
		
		assertEquals("registerfailed", requestString);
	}
	
	@Test
	public void RegisterFailedCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateRegisterFailed();
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.RegisterFailed, requestType);
	}
	
	@Test
	public void LoggedInRequestStringTest()
	{
		String requestString = ServerProtocol.CreateLoggedIn();
		
		assertEquals("loggedin", requestString);
	}
	
	@Test
	public void LoggedInCorrectRequestType()
	{
		String requestString = ServerProtocol.CreateLoggedIn();
		RequestType requestType = ClientProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.LoggedIn, requestType);
	}
	
	@Test
	public void LoginRequestStringTest() {
		String requestString = ClientProtocol.CreateLogin("håKan", "lövbiff13");
		
		assertEquals("login%håKan;lövbiff13", requestString);
	}
	
	@Test
	public void LoginCorrectRequestType()
	{
		String requestString = ClientProtocol.CreateLogin("håKan", "lövbiFF13");
		RequestType requestType = ServerProtocol.GetRequestType(requestString);
		
		assertEquals(RequestType.LoggingIn, requestType);
	}
	
	@Test
	public void LoginCorrectValues()
	{
		String password = "löavefBBiiFF";
		String userName = "MarkUs15";
		String requestString = ClientProtocol.CreateLogin(userName, password);
		
		String[] recevedValues = ServerProtocol.GetUsernamePassword(requestString);
		
		assertEquals("wrong username", userName, recevedValues[0]);
		assertEquals("wrong password", password, recevedValues[1]);
	}
}
