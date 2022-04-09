import java.util.Vector;

public class Employee{

        //MEMBER VARIABLES
        private String username;
        private String password;
        private boolean isLoggedIn;
        private boolean isSystemAdmin;
        private Vector<ChatLog> allUserChatLogs;


        //CONSTRUCTOR
        public Employee(String username, String password){
                this.username = username;
                this.password = password;
                this.setIsLoggedIn(false);
                this.setIsSystemAdmin(false);
        }

        //SETTERS
        public void setUsername(String username){
                this.username = username;
        }

        public void setPassword(String password){
                this.password = password;
        }

        public void setIsLoggedIn(boolean isLoggedIn){
                this.isLoggedIn = isLoggedIn;
        }

        public void setIsSystemAdmin(boolean isSystemAdmin){
                this.isSystemAdmin = isSystemAdmin;
        }

        public void setAllUserChatLogs(Vector<ChatLog> allUserChatLogs){
                this.allUserChatLogs = allUserChatLogs;
        }


        //GETTERS
        public String getUsername(){
                return this.username;
        }

        public String getPassword(){
                return this.password;
        }

        public boolean getIsLoggedIn(){
                return this.isLoggedIn;
        }

        public boolean getIsSystemAdmin(){
                return this.isSystemAdmin;
        }

        public Vector<ChatLog> getAllUserChatLogs(){
                return this.allUserChatLogs;
        }

        // MEMBER FUNCTIONS
        public boolean verifyEmployee(String username, String password){
                return true;
        }

        public void createUserChatLog(String chatLogName, Vector<String> allMembers){
                allUserChatLogs.add(new ChatLog(chatLogName, allMembers));
        }

        public void deleteUserChatLog(String chatLogName){

        }

        public void getUserChatLog(String chatLogName){

        }

        public void sendMessage(String chatLogName, Message message){

        }

}