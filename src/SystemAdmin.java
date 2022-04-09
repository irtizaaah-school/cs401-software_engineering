import java.util.Vector;

public class SystemAdmin extends Employee{

    //CONSTRUCTOR
    public SystemAdmin(String username, String password, AllLogs allLogs){
        super(username, password);
        super.setIsSystemAdmin(true);
    }

    //MEMBER FUNCTIONS
    //CREATE
    public void createSystemAdmin(String username, String password, AllLogs allLogs){
        Employee newEmployee = new Employee(username, password);
        allLogs.getAllEmployees().add(newEmployee);
    }
    public void createEmployee(String username, String password, AllLogs allLogs){
        Employee newEmployee = new Employee(username, password);
        allLogs.getAllEmployees().add(newEmployee);
    }

    //DELETE
    public void deleteEmployee(String username, AllLogs allLogs){
        
    }
    public void deleteSystemAdmin(String username, AllLogs allLogs){

    }

    //GETTERS
    public Vector<SystemAdmin> getAllSystemAdmins(AllLogs allLogs){
        return allLogs.getAllSystemAdmins();
    } 

    public Vector<Employee> getAllEmployees(AllLogs allLogs){
        return allLogs.getAllEmployees();
    } 

    public Vector<ChatLog> getAllChatLogs(AllLogs allLogs){
        return allLogs.getAllChatLogs();
    } 

}