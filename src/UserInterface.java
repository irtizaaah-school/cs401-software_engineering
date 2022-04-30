import java.util.Scanner;
import java.util.Vector;

public class UserInterface {
	private Client client;
	
	UserInterface(){
		client = new Client();
	}
	
	public void run(){
    	
		// LOGIN
		client.login("jo","12");
		
		// CREATE AND DELETE
		Vector<String> members = new Vector<String>();
		members.add("jo");
		members.add("lu");
			
		client.createChatLog("jo,lu", members);
		client.createEmployee("pi", "90", false); // create new employee (requires sys admin privilege)
		client.deleteEmployee("lu"); // delete existing employee (requires sys admin privilege)
		
		// MESSAGE
		client.sendMessage("jo,ky", new Message("Hi")); // send message
		
		// SEND NEW MESSAGE
		Scanner sc = new Scanner(System.in);
		String say = "";
		
		while(!say.equals("0")) {
			System.out.print("Enter message: ");
	    	say = sc.nextLine();
	    	if(!say.equals("0")) {
	    		client.sendMessage("jo,ky", new Message(say)); // send message
	    	}
	    	System.out.println(client.getNewMessage("jo,ky").getContent()); // retrieve last (newest) message from chat log
		}
		
		// GETTERS
		client.getAllChatLogs(); // get all chat logs (requires sys admin privilege)
		client.getChatLog("ty,lu"); // get any chat log (requires sys admin privilege)
		client.getEmployeeChatLog("jo,ky"); // get chat log from the chat logs employee is in
    	client.getEmployeeChatLogList(); // get all chat logs employee is in
    	client.getAllEmployees(); // get all employees
    	client.getEmployeeLoggedIn(); // get the employee logged in 
    	client.getEmployee("ty"); // get any employee
    	
    	// LOGOUT
    	client.logout();
	}
	
	public void runLogin(){
		
	}
	
	public void runMenu(){
		
	}
	
	public void runSettings(){
		
	}
	
	public void runChatLoglist(){
		
	}
	
	public void runChatLog(){
		
	}
	
}
