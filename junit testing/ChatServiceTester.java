package junittesting;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;

import mainpackage.ChatService;
import mainpackage.Employee;
import mainpackage.EmployeeList;
import mainpackage.ChatLog;
import mainpackage.ChatLogList;
import mainpackage.Message;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
public class ChatServiceTester {
	@Test
    public void testgetEmployeeLoggedIn() {
       ChatService chatService1 = new ChatService();
       Employee employee1 = new Employee("jake","1234",true);
       chatService1.setEmployeeLoggedIn(employee1);
        
        assertTrue(chatService1.getEmployeeLoggedIn().equals(employee1));
    }
	
	@Test
    public void testgetAllEmployees() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("Ammi", "2345", false));
	    employeeList1.add(new Employee("Ryan", "4567", false));
	    ChatLogList chatLogList1 = new ChatLogList();
	    chatLogList1.add(new ChatLog());
       ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        assertTrue(chatService1.getAllEmployees().equals(employeeList1));
    }
	
	@Test
    public void testgetAllChatLogs() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("ammi", "2345", false));
	    employeeList1.add(new Employee("ryan", "4567", false));
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    chatLogList1.add(new ChatLog("chatLog1",members));
	    Vector<String> members2 = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    chatLogList1.add(new ChatLog("chatLog2",members2));
       ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        assertTrue(chatService1.getAllChatLogs().equals(chatLogList1));
    }
	
	@Test
    public void testgetEmployeeChatLogList() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("ammi", "2345", false));
	    employeeList1.add(new Employee("ryan", "4567", false));
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    chatLogList1.add(new ChatLog("chatLog1",members));
	    ChatLogList employeeChatLogList1 = new ChatLogList();
	    employeeChatLogList1.add(new ChatLog("chatLog1",members));
	
	    Vector<String> members2 = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    chatLogList1.add(new ChatLog("chatLog2",members2));
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
      
        assertTrue(chatService1.getEmployeeChatLogList().get(0).getName().equals(employeeChatLogList1.get(0).getName()));
        assertTrue(chatService1.getEmployeeChatLogList().get(0).getMembers().equals(employeeChatLogList1.get(0).getMembers()));
    }
	
	
	@Test
    public void testgetEmployeeChatLog() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("ammi", "2345", false));
	    employeeList1.add(new Employee("ryan", "4567", false));
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    chatLogList1.add(chatLog1);
	
	    Vector<String> members2 = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    chatLogList1.add(new ChatLog("chatLog2",members2));
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
      
        assertTrue(chatService1.getEmployeeChatLog("chatLog1").equals(chatLog1));
    }
	
	@Test
    public void testgetChatLog() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("ammi", "2345", false));
	    employeeList1.add(new Employee("ryan", "4567", false));
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    chatLogList1.add(chatLog1);
	
	    Vector<String> members2 = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    ChatLog chatLog2 = new ChatLog("chatLog2",members2);
	    chatLogList1.add(chatLog2);
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
      
        assertTrue(chatService1.getChatLog("chatLog2").equals(chatLog2));
    }
	
	@Test
    public void testgetNewMessage() {
		Employee employee1 = new Employee("jake","1234",true);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(new Employee("ammi", "2345", false));
	    employeeList1.add(new Employee("ryan", "4567", false));
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
      
        assertTrue(chatService1.getNewMessage("chatLog1").equals(message1));
    }
	
	
	@Test
    public void testgetEmployee() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
      
        assertTrue(chatService1.getEmployee("ammi").equals(employee2));
    }
	
	@Test
    public void testlogin() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

        ChatService chatService1 = new ChatService(employee2,employeeList1,chatLogList1);
        chatService1.setEmployeeLoggedIn(new Employee());
        chatService1.login("jake", "1234");
      
        assertTrue(chatService1.getEmployeeLoggedIn().equals(employee1));
    }

	
	@Test
    public void testlogout() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

        ChatService chatService1 = new ChatService(employee2,employeeList1,chatLogList1);
        chatService1.logout();
      
        assertTrue(chatService1.getEmployeeLoggedIn().getUsername().equals(new Employee().getUsername()));
    }
	
	@Test
    public void testcreateChatLog() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

	    Vector<String> members2 = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        chatService1.createChatLog("chatLog", members2);
      
        assertTrue(chatService1.getChatLog("chatLog2").getMembers().equals(members2));
    }
	
	@Test
    public void testcreateEmployee() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);

	  
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        chatService1.createEmployee("Daniel","4567",false);
      
        assertTrue(chatService1.getEmployee("Daniel").getPassword().equals("4567"));
    }
	
	
	@Test
    public void testdeleteEmployee() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);


        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        chatService1.createEmployee("Daniel","4567",false);
        
        chatService1.deleteEmployee("Daniel");
      
        assertTrue(chatService1.getEmployee("Daniel").getPassword().equals("NONE"));
    }
	
	@Test
    public void testsendMessage() {
		Employee employee1 = new Employee("jake","1234",true);
		Employee employee2 = new Employee("ammi", "2345", false);
		Employee employee3 = new Employee("ryan", "4567", false);
	    EmployeeList employeeList1 = new EmployeeList();
	    employeeList1.add(employee1);
	    employeeList1.add(employee2);
	    employeeList1.add(employee3);
	    
	    Vector<String> members = new Vector<String>();
	    members.add("ammi");
	    members.add("ryan");
	    members.add("jake");
	    ChatLogList chatLogList1 = new ChatLogList();
	    ChatLog chatLog1 = new ChatLog("chatLog1",members);
	    Message message1 = new Message("Hello everybody","ammi");
	    chatLog1.addMessage(message1);
	    chatLogList1.add(chatLog1);
	    
        ChatService chatService1 = new ChatService(employee1,employeeList1,chatLogList1);
        chatService1.sendMessage("chatLog1",new Message("Hey guys"));
      
        assertTrue(chatService1.getNewMessage("chatLog1").getContent().equals("Hey guys"));
    }
	

	
}
