import java.io.Serializable;
import java.util.Vector;

    public class SocketMessage implements Serializable{

        //MEMBER VARIABLES
        private Employee employee;
        private SystemAdmin systemAdmin;
        private Message message;
        private ChatLog chatlog;
        private Vector<ChatLog> chatlogs;
        private String type; // login, createChatlog, sendMessage
    
        //CONSTRUCTOR
        public SocketMessage(Employee employee, Message message, ChatLog chatlog, Vector<ChatLog> chatlogs, String type){
            this.employee = employee;
            this.message = message;
            this.chatlog = chatlog;
            this.chatlogs = chatlogs;
            this.type = type;
        }

        public SocketMessage(SystemAdmin systemAdmin, Message message, ChatLog chatlog, Vector<ChatLog> chatlogs, String type){
            this.systemAdmin = systemAdmin;
            this.message = message;
            this.chatlog = chatlog;
            this.chatlogs = chatlogs;
            this.type = type;
        }
        
        //SETTERS
        public void setEmployee(Employee employee){
            this.employee = employee;
        }
    
        public void setSystemAdmin(SystemAdmin systemAdmin){
            this.systemAdmin = systemAdmin;
        }
    
        public void setMessage(Message message){
            this.message = message;
        }
    
        public void setChatlog(ChatLog chatlog){
            this.chatlog = chatlog;
        }
    
        public void setChatlogs(Vector<ChatLog> chatlogs){
            this.chatlogs = chatlogs;
        }
    
        public void setType(String type){
            this.type = type;
        }
      
        //GETTERS
        public Employee getEmployee(){
            return this.employee;
        }
    
        public SystemAdmin getSystemAdmin(){
            return this.systemAdmin;
        }
    
        public Message getMessage(){
            return this.message;
        }
    
        public ChatLog getChatlog(){
            return this.chatlog;
        }
    
        public Vector<ChatLog> getChatlogs(){
            return this.chatlogs;
        }
    
        public String getType(){
            return this.type;
        }
      
    }
