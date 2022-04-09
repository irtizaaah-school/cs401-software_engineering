import java.util.Vector;

public class ChatService{

    //MEMBER VARIABLES
    private AllLogs allLogs;

    //CONSTRUCTOR
    public ChatService(AllLogs allLogs){ // overloaded method
        this.allLogs = allLogs;
    }
    
    public ChatService(Vector<SystemAdmin> allSystemAdmins, Vector<Employee> allEmployees, Vector<ChatLog> allChatLogs){ // overloaded method
        this.allLogs = new AllLogs(allSystemAdmins, allEmployees, allChatLogs);
    }

    // UTILITY FUNCTIONS
    private boolean isSessionExpired(Employee employee){ // overloaded method
        if(employee.getIsLoggedIn() == true){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean isSessionExpired(SystemAdmin systemAdmin){ // overloaded method
        if(systemAdmin.getIsLoggedIn() == true){
            return false;
        }
        else{
            return true;
        }
    }

    // EMPLOYEE WRAPPERS
    public boolean login(Employee employee, String username, String password){
        if(isSessionExpired(employee) == false){
            return employee.verifyEmployee(username, password);
        }
        else{
            return false;
        }
    }
    public boolean login(SystemAdmin systemAdmin, String username, String password){ // overloaded method
        if(isSessionExpired(systemAdmin) == false){
            return systemAdmin.verifyEmployee(username, password);
        }
        else{
            return false;
        }
    }

    // OVERLOADED EMPLOYEE WRAPPER METHODS (for employees)
    public void createUserChatLog(String chatLogName, Vector<String> allMembers, Employee employee){
        if(isSessionExpired(employee) == false){
            employee.createUserChatLog(chatLogName, allMembers);
        }
    }

    public void deleteUserChatLog(String chatLogName, Employee employee){
        if(isSessionExpired(employee) == false){
            employee.deleteUserChatLog(chatLogName);
        }
    }

    public void retrieveAllUserChatLogs(String chatLogName, Employee employee){
        if(isSessionExpired(employee) == false){
            employee.getAllUserChatLogs();
        }
    }

    public void sendMessage(String chatLogName, Message message, Employee employee){
        if(isSessionExpired(employee) == false){
            employee.sendMessage(chatLogName, message);
        }
    }

    // OVERLOADED EMPLOYEE WRAPPER METHODS (for system admins)
    public void createUserChatLog(String chatLogName, Vector<String> allMembers, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            systemAdmin.createUserChatLog(chatLogName, allMembers);
        }
    }

    public void deleteUserChatLog(String chatLogName, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            systemAdmin.deleteUserChatLog(chatLogName);
        }
    }

    public void retrieveAllUserChatLogs(String chatLogName, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            systemAdmin.getAllUserChatLogs();
        }
    }

    public void sendMessage(String chatLogName, Message message, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            systemAdmin.sendMessage(chatLogName, message);
        }
    }


    // SYSTEM ADMIN WRAPPERS
    public void createUser(String username, String password, boolean isSystemAdmin, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            if(isSystemAdmin == true){
                systemAdmin.createSystemAdmin(username, password, this.allLogs);
            }
            else{
                systemAdmin.createEmployee(username, password, this.allLogs);
            }
        }
    }

    public void deleteUser(String username, boolean isSystemAdmin, SystemAdmin systemAdmin){
        if(isSessionExpired(systemAdmin) == false){
            if(isSystemAdmin == true){
                systemAdmin.deleteSystemAdmin(username, allLogs);
            }
            else{
                systemAdmin.deleteEmployee(username, allLogs);
            }
        }
    }

    // ALL LOGS WRAPPER
    public Vector<SystemAdmin> retrieveAllSystemAdmins(SystemAdmin systemAdmin){
        return systemAdmin.getAllSystemAdmins(this.allLogs);
    }
    public Vector<Employee> retrieveAllEmployees(SystemAdmin systemAdmin){
        return systemAdmin.getAllEmployees(this.allLogs);
    }
    public Vector<ChatLog> retrieveAllChatLogs(SystemAdmin systemAdmin){
        return systemAdmin.getAllChatLogs(this.allLogs);
    }
}