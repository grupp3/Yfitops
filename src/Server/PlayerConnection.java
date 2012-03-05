package Server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;



public class PlayerConnection {
	// Alla Metoder som skall finnas i denna klassen har jag nu skrivit in.. bara att ändra 
	// det som skall ändras tex vilken typ av metod de skall vara och namnet på inparametrarna.
	// underlättar för implementationen om de är klart.
	
	/**
	 * Constructor to PlayerConnection, it takes in a Socket refrence and 
	 * collect Database.
	 * @param DB
	 */
	public PlayerConnection(){
		DBHandler DB;
		ServerSocket listnersocket;
		List <Playerconnection>;
		
	}
	
	public void StartSocket (){
		ServerSocket listnersocket = new ServerSocket(int 1024);
		while(true){
		listnersocket.accept();
		new Aktivitetsobjekt(klientSock);
		}
		listnersocket.close();
		
	}
	
	boolean gamingReady;
	public void GameStarted(boolean currentPlayer, String string) {
		// TODO Auto-generated method stub
		
	}

	public void Register(String[] namn){

	}
	
	public void Send(String nameforsend){
	
	}
	
	public void Login(String[] nameforLogin){
			
	}
	public void GamingStarted(boolean nameforGamingcheckbooliean, String nameforGamingcheckString ){
		
	}
	public void GamingCheck(){
		
	}
	public void MakeMove(String NameforMakeMove){
		
	}
	public void SendIllegalMove(){
		
	}
	public void YourTurn(int Yourturn1,int Yourturn2){
		
	}
	public void CountEnd(boolean countend){
		
	}
	public void GetHistory(){
		
	}
	public void GetHighscore(){
		
	}
}
