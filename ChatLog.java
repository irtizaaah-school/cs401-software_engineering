import java.util.Vector;

public class ChatLog{

        //MEMBER VARIABLES
        private String chatLogName;
        private Vector<String> allMembers;
        private Vector<Message> allMessages;


        //CONSTRUCTOR
        public ChatLog(String chatLogName, Vector<String> allMembers){
                this.chatLogName = chatLogName;
                this.allMembers = allMembers;
        }

        //SETTERS
        public void setChatLogName(String chatLogName){
                this.chatLogName = chatLogName;
        }
  
        public void setAllMembers(Vector<String> allMembers){
                this.allMembers = allMembers;
        }
  
        public void setAllMessages(Vector<Message> allMessages){
                this.allMessages = allMessages;
        }
  

        //GETTERS
        public String getChatLogName(){
                return this.chatLogName;
        }
  
        public Vector<String> getAllMembers(){
                return this.allMembers;
        }
  
        public Vector<Message> getAllMessages(){
                return this.allMessages;
        }

        // MEMBER FUNCTIONS

        public void addMessage(Message message){

        }
}