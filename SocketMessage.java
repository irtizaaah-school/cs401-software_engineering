import java.io.Serializable;

public class SocketMessage implements Serializable{

	//MEMBER VARIABLES
	private String type;
	private String status; // "PENDING", "FAILED", "SUCCESS"
	private Employee employeeLoggedIn;
	private Employee employee;
	private Message message;
	private ChatLog chatLog;
	private EmployeeList employeeList;
	private ChatLogList chatLogList;


	//CONSTRUCTOR
    public SocketMessage(){
		this.setType("NONE");
		this.setStatus("PENDING");
		this.setEmployee(new Employee());
		this.setMessage(new Message());
		this.setChatLog(new ChatLog());
		this.setEmployeeList(new EmployeeList());
		this.setChatLogList(new ChatLogList());
	}

    public SocketMessage(String type){
		this.type = type;
		this.setStatus("PENDING");
		this.setEmployee(new Employee());
		this.setMessage(new Message());
		this.setChatLog(new ChatLog());
		this.setEmployeeList(new EmployeeList());
		this.setChatLogList(new ChatLogList());
	}

	public SocketMessage(String type, Employee employee, Message message, ChatLog chatLog, EmployeeList employeeList, ChatLogList chatLogList){
		this.setType(type);
		this.setStatus("PENDING");
		this.setEmployee(employee);
		this.setMessage(message);
		this.setChatLog(chatLog);
		this.setEmployeeList(employeeList);
		this.setChatLogList(chatLogList);
	}

	//SETTERS
  	public void setType(String type){
  		this.type = type;
  	}
  
  	public void setStatus(String status){
  		this.status = status.toUpperCase();
  	}
  
  	public void setEmployee(Employee employee){
  		this.employee = employee;
  	}
  	
  	public void setEmployeeLoggedIn(Employee employeeLoggedIn){
  		this.employeeLoggedIn = employeeLoggedIn;
  	}
  
  	public void setMessage(Message message){
  		this.message = message;
  	}
  
  	public void setChatLog(ChatLog chatLog){
  		this.chatLog = chatLog;
  	}
  
  	public void setEmployeeList(EmployeeList employeeList){
  		this.employeeList = employeeList;
  	}
  
  	public void setChatLogList(ChatLogList chatLogList){
  		this.chatLogList = chatLogList;
  	}
  

	//GETTERS
  	public String getType(){
  		return this.type;
  	}
  
  	public String getStatus(){
  		return this.status;
  	}
  
  	public Employee getEmployee(){
  		return this.employee;
  	}
  	
  	public Employee getEmployeeLoggedIn(){
  		return this.employeeLoggedIn;
  	}
  
  	public Message getMessage(){
  		return this.message;
  	}
  
  	public ChatLog getChatLog(){
  		return this.chatLog;
  	}
  
  	public EmployeeList getEmployeeList(){
  		return this.employeeList;
  	}
  
  	public ChatLogList getChatLogList(){
  		return this.chatLogList;
  	}

    //MEMBER FUNCTIONS
    public void print(){
       /* System.out.println("type: " + this.type);
        System.out.println("status: " + this.status);
        
        System.out.println("employe: ");
        System.out.println("\tusername: " + this.employee.getUsername());
        System.out.println("\tpassword: " + this.employee.getPassword());
        System.out.println("\tisSystemAdmin: " + this.employee.getIsSystemAdmin());
        System.out.println("\tisOnline: " + this.employee.getIsOnline());

        System.out.println("message: ");
        System.out.println("\tsender: " + this.message.getSender());
        System.out.println("\tcontent: " + this.message.getContent());
        System.out.println("\tisDelivered: " + this.message.getStatus());

        System.out.println("chatlog: ");
        System.out.println("\tname: " + this.chatLog.getName());
        System.out.println("\tmembers: " + this.chatLog.getMembers());
        
        if(this.chatLog.getMessages().size() == 0) {
        	System.out.println("messages: []");
        }
        else {
        	System.out.println("messages: ");
        	for(int i = 0; i < this.chatLog.getMessages().size(); i++) {
            	System.out.println("\t\t" + 
            			this.chatLog.getMessages().get(i).getSender() + ": " +
            			this.chatLog.getMessages().get(i).getContent() + " (" + 
            			this.chatLog.getMessages().get(i).getStatus() + ")");
            }
        }
        
        if(this.chatLogList.getChatLogs().size() == 0) {
        	System.out.println("chatlog list: []");
        }
        else {
        	System.out.println("chatlog list: ");
        	for(int i = 0; i < this.chatLogList.getChatLogs().size(); i++) {
            	System.out.println("\t\t" + this.chatLogList.getChatLogs().get(i).getName());
    		} 
        }
		
        if(this.employeeList.getEmployees().size() == 0) {
        	System.out.println("employee list: []");
        }
        else {
    		System.out.println("employee list: ");
        	for(int i = 0; i < this.employeeList.getEmployees().size(); i++) {
            	System.out.println("\t\t" + this.employeeList.getEmployees().get(i).getUsername());
    		}
        }
        */
		
    }
  
}
