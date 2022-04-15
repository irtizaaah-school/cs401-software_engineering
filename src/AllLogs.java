import java.util.Vector;

public class AllLogs{

    //MEMBER VARIABLES

    private Vector<SystemAdmin> allSystemAdmins;
    private Vector<Employee> allEmployees;
    private Vector<ChatLog> allChatLogs;


    //CONSTRUCTOR

    public AllLogs(Vector<SystemAdmin> allSystemAdmins, Vector<Employee> allEmployees, Vector<ChatLog> allChatLogs){
            this.allSystemAdmins = allSystemAdmins;
            this.allEmployees = allEmployees;
            this.allChatLogs = allChatLogs;
    }

    //SETTERS

    public void setAllSystemAdmins(Vector<SystemAdmin> allSystemAdmins){
            this.allSystemAdmins = allSystemAdmins;
    }

    public void setAllEmployees(Vector<Employee> allEmployees){
            this.allEmployees = allEmployees;
    }

    public void setAllChatLogs(Vector<ChatLog> allChatLogs){
            this.allChatLogs = allChatLogs;
    }


    //GETTERS

    public Vector<SystemAdmin> getAllSystemAdmins(){
            return this.allSystemAdmins;
    }

    public Vector<Employee> getAllEmployees(){
            return this.allEmployees;
    }

    public Vector<ChatLog> getAllChatLogs(){
            return this.allChatLogs;
    }

    //METHODS
    public void addEmployee(Employee newEmployee)
    {
    	if(allEmployees.contains(newEmployee))
    	{
    		System.out.println("Employee already exists");
    		System.out.println("Added + " + newEmployee.getUsername() + " to allEmployees");
    	}
    	else
    		allEmployees.add(newEmployee);
    };

    public void addSystemAdmin(SystemAdmin newSystemAdmin)
    {
    	if(allSystemAdmins.contains(newSystemAdmin))
    		System.out.println("System Admin already exists");
    	else
    		{
    		allSystemAdmins.add(newSystemAdmin);
    		System.out.println("Added + " + newSystemAdmin.getUsername() + " allSystemAdmin");
    		}
    
    };
    

    public void removeEmployee(String username)
    {
    	for(int i = 0; i < allEmployees.size();i++)
    	{
    		if(allEmployees.get(i).getUsername().equals(username))//getusername().equals(username))
	    		{
    				allEmployees.remove(allEmployees.get(i));
    				System.out.println("Employee " + username + " removed sucessfully");
    				return;
    			}
    	}
    	System.out.println("Employee doesn't exist");
    };
    
    public void removeSystemAdmin(String username)
    {
    	for(int i = 0; i < allSystemAdmins.size();i++)
    	{
    		if(allSystemAdmins.get(i).getUsername().equals(username))
	    		{
    				allSystemAdmins.remove(allSystemAdmins.get(i));
    				System.out.println("System Admin " + username + " removed sucessfully");
    				return;
    			}
    	}
    	System.out.println("System Admin doesn't exist");
    };
    
    public void updateChatLog(ChatLog log, Message message)
    {
    	int position = allChatLogs.indexOf(log);
    	allChatLogs.get(position).addMessage(message);
    }
}