import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class UserInterface {
	private Client client;
	
	UserInterface(){
		client = new Client();
	}
	
	public void run(){
		LoginUI loginUI = new LoginUI(this.client);
		loginUI.run();
	}
	
}
