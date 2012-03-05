package Server;

import java.net.Socket;
import java.util.ArrayList;

public class PlayerConnection extends Thread {

	boolean gamingReady;

	public PlayerConnection(Socket clientSocket, ArrayList<PlayerConnection> conectionList) {
		// TODO Auto-generated constructor stub
	}
	
	public void StartSocket () {
		/*ServerSocket listnersocket = new ServerSocket(1024);
		while(true){
		listnersocket.accept();
		new Aktivitetsobjekt(klientSock);
		}
		listnersocket.close();*/
		
	}
	public void GameStarted(boolean yourTurn, String opponentName) {
		// TODO Auto-generated method stub
		
	}

	public void Register(String[] namn){

	}
	
	public void Send (String requestString){
	
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

