import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class ChatService implements Serializable{

	//MEMBER VARIABLES
	private Employee employeeLoggedIn;
	private static EmployeeList allEmployees = new EmployeeList();
	private static ChatLogList allChatLogs = new ChatLogList();


	//CONSTRUCTOR
	public ChatService(){
		this.loadChatService();
	}

	public ChatService(Employee employeeLoggedIn, EmployeeList allEmployees, ChatLogList allChatLogs){
		this.setEmployeeLoggedIn(employeeLoggedIn);
		this.setAllEmployees(allEmployees);
		this.setAllChatLogs(allChatLogs);
	}

	//SETTERS
  	public void setEmployeeLoggedIn(Employee employeeLoggedIn){
  		this.employeeLoggedIn = employeeLoggedIn;
  	}
  
  	private void setAllEmployees(EmployeeList allEmployees){
        ChatService.allEmployees = allEmployees;
  	}
  
  	private void setAllChatLogs(ChatLogList allChatLogs){
        ChatService.allChatLogs = allChatLogs;
  	}
  

	//GETTERS
  	public Employee getEmployeeLoggedIn(){
  		return this.employeeLoggedIn;
  	}
  
  	public EmployeeList getAllEmployees(){
  		return ChatService.allEmployees;
  	}
  
  	public ChatLogList getAllChatLogs(){
		if(employeeLoggedIn.getIsSystemAdmin() == true){
			return ChatService.allChatLogs;
		}

		return new ChatLogList();
  	}
  	
    // MORE GETTERS
	// GET CHATLOGS
    public ChatLogList getEmployeeChatLogList(){
		return ChatService.allChatLogs.getMemberChatLogs(employeeLoggedIn.getUsername());
    }

	public ChatLog getEmployeeChatLog(String name){
		for(int i = 0; i < ChatService.allChatLogs.size(); i++){ 
			for(int j = 0; j < ChatService.allChatLogs.get(i).getMembers().size(); j++){ 

				String chatLogMemberUsername = ChatService.allChatLogs.get(i).getMembers().get(j);

				if(chatLogMemberUsername.equals(employeeLoggedIn.getUsername())){ 
					return ChatService.allChatLogs.get(name);
				}
			}
		}

		return new ChatLog();
    }

    public ChatLog getChatLog(String name){
		if(employeeLoggedIn.getIsSystemAdmin() == true){
			return ChatService.allChatLogs.get(name);
		}

		return new ChatLog();
    }
    
    // GET NEWEST MESSAGE
    public Message getNewMessage(String chatLogName) {
    	Vector<Message> messages = ChatService.allChatLogs.get(chatLogName).getMessages();
    	
    	if(messages != null && messages.size() > 0) {
        	return messages.get(messages.size()-1);
    	}
    	else {
    		return new Message();
    	}
    }

	// GET EMPLOYEE
	public Employee getEmployee(String username){
		username = username.toLowerCase();
		return ChatService.allEmployees.get(username);
    }

    //MEMBER FUNCTIONS
	// LOGIN AND LOGOUT
    public boolean login(String username, String password){
    	username = username.toLowerCase();
    	
		if(ChatService.allEmployees.contains(username)){ 
			if (ChatService.allEmployees.get(username).validate(password) == true) { // validate password
				setEmployeeLoggedIn(allEmployees.get(username));
				return true;
			}
		}

		return false;
    }
    
    public void logout(){
    	this.setEmployeeLoggedIn(new Employee());
    }
    
	// CREATE AND DELETE
    public void createChatLog(String name, Vector<String> members){
    		ChatLog newChatLog = new ChatLog(name, members);
			ChatService.allChatLogs.add(newChatLog);
			this.saveChatService();
    }
    
    public void createEmployee(String username, String password, boolean isSystemAdmin){
    	username = username.toLowerCase();
    	
    	if(employeeLoggedIn.getIsSystemAdmin() == true){
			Employee newEmployee = new Employee(username, password, isSystemAdmin);
			ChatService.allEmployees.add(newEmployee);
			this.saveChatService();
			
		}
    	
    }

    public void deleteEmployee(String username){
    	username = username.toLowerCase();
    	
		if(employeeLoggedIn.getIsSystemAdmin() == true){
			ChatService.allEmployees.remove(username);
			this.saveChatService();
		}
    }

	// SEND MESSAGE
    public void sendMessage(String chatLogName, Message message, String status){
		ChatLog chatLog = ChatService.allChatLogs.get(chatLogName);
		message.setSender(this.employeeLoggedIn.getUsername());
		message.setStatus(status);
		
		for(int i = 0; i < chatLog.getMembers().size(); i++){  // check if employeeLoggedIn is a part of ChatLog

			String chatLogMemberUsername = chatLog.getMembers().get(i);

			if(chatLogMemberUsername.equals(employeeLoggedIn.getUsername())){ 
				ChatService.allChatLogs.get(chatLogName).addMessage(message);
				this.saveChatService();
			}
		}
    }

	// LOAD DATA
	public void loadChatService(){
		// EMPLOYEE LOGGED IN
		this.setEmployeeLoggedIn(new Employee());

		// EMPLOYEES
		this.setAllEmployees(new EmployeeList());

		// CHATLOGS
		this.setAllChatLogs(new ChatLogList());
		this.setEmployeeLoggedIn(new Employee());
        
        //file format username/password/trueorfalse
        //every line has one employee
        try {
        	File datafile = new File("userdata.txt");
        	Scanner scanner = new Scanner(datafile);
	
			while (scanner.hasNextLine()) {
					String data = scanner.nextLine();
					String[] str = data.split("/");
					if(str[2].equals("true")) {
						allEmployees.add(new Employee(str[0],str[1],true));
					}
					else {
						allEmployees.add(new Employee(str[0],str[1],false));
					}
				}
			//chatlog file will be in this format
			//"address of the file with the data for that chatlog"
			File datafile2 = new File("chatlogs.txt");
			Scanner scanner2 =  new Scanner(datafile2);
			
			while(scanner2.hasNextLine()) {
				String data2 = scanner2.nextLine();
						File datafile3 = new File(data2);
						Scanner scanner3 = new Scanner(datafile3);
						String name = scanner3.nextLine();
						String[] str2 = scanner3.nextLine().split(",");
						Vector <String> members = new Vector<String>();
							for(int i = 0; i < str2.length; i++) {
								members.add(str2[i]);
							}
						
						ChatLog chatlog = new ChatLog(name,members);
						while(scanner3.hasNextLine()) {
							String data3 = scanner3.nextLine();
							String[] str3 = data3.split(":");
							chatlog.addMessage(new Message (str3[1],str3[0]));
							
						}
						allChatLogs.add(chatlog);
						scanner3.close();
				}
			
			scanner2.close();
			scanner.close();
			
		
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
  
	public void saveChatService() {
		
   	 //save
   	 
   	 try {
   		 	File file = new File("userdata.txt"); 
				FileWriter myWriter = new FileWriter(file); 
				if(file.exists()) {
					file.createNewFile();
				}
				for(int i = 0; i < allEmployees.size(); i++) {
					if(allEmployees.get(i).getIsSystemAdmin()) {
						myWriter.write(allEmployees.get(i).getUsername()+"/"+allEmployees.get(i).getPassword()+"/"+ "true");
					}
					else {
						myWriter.write(allEmployees.get(i).getUsername()+"/"+allEmployees.get(i).getPassword()+"/"+ "false");
					}
					if(i != allEmployees.size()-1){
						myWriter.write("\n");
					}
				}
				
				myWriter.close(); //close the writer
				
				File file2 = new File("chatlogs.txt");
				FileWriter myWriter2 = new FileWriter(file2); 
				for(int i = 0; i < allChatLogs.size(); i++) {
					//write chatlog file name to chatlogs file
					myWriter2.write(allChatLogs.get(i).getName()+".txt");
					if(i != allChatLogs.size()-1) {
						myWriter2.write("\n");
					}
					
					// individual chatlog files
					File file3 = new File(allChatLogs.get(i).getName()+".txt");
					FileWriter myWriter3 = new FileWriter(file3); //Create a writer
					
					myWriter3.write(allChatLogs.get(i).getName()+"\n");
					for(int j = 0; j < allChatLogs.get(i).getMembers().size();j++) {
						myWriter3.write(allChatLogs.get(i).getMembers().get(j));
						if(i != allChatLogs.get(i).getMembers().size()-1) {
							myWriter3.write(",");
						}
					}
					myWriter3.write("\n");
					for(int j = 0; j < allChatLogs.get(i).getMessages().size();j++) {
						myWriter3.write(allChatLogs.get(i).getMessages().get(j).getSender()+":"+allChatLogs.get(i).getMessages().get(j).getContent()+":"+allChatLogs.get(i).getMessages().get(j).getStatus());
						if(i != allChatLogs.get(i).getMessages().size()-1) {
							myWriter3.write("\n");
						}
					}
					
					myWriter3.close();
				}
				myWriter2.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	
}