import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
	
public class ChatUI {
	Client client;
	JFrame chatFrame = ChatAppUI.createFrame(500,500, "Chat App");
	JScrollPane scrollableMessagesContainer;
	String currentChatLogName = "";
	boolean shouldStop = false;
	int time = 1000;
	int delay = time + 1;
	
	ChatUI(Client client){
		this.client = client;
	}
	
	public void run(){
		

		Border buttonBorder = BorderFactory.createLineBorder(ChatAppUI.buttonColor, 5);
		
		// SETTINGS BUTTON
		JButton settingsButton = ChatAppUI.createButton("Settings");
		settingsButton.setBorder(buttonBorder);
		settingsButton.addActionListener((e)-> { 
			SettingsUI settingsUI = new SettingsUI(this.client);
			chatFrame.dispose();
			shouldStop = true;
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	settingsUI.run();
			            }
			        },delay 
				);
		
		
			//settingsUI.run();
			
		});

		// CREATE NEW CHAT BUTTON
		JButton createChatLogButton = ChatAppUI.createButton("New Chat");
		createChatLogButton.setBorder(buttonBorder);
		createChatLogButton.addActionListener((e)-> { 
			CreateLogUI createlog = new CreateLogUI(client);
	        shouldStop = true;
			chatFrame.dispose();
			createlog.run();
	    });
		
		// REFRESH BUTTON
		JButton refreshButton = ChatAppUI.createButton("Refresh");
		refreshButton.setBorder(buttonBorder);
		refreshButton.addActionListener((e)-> { 
			ChatUI chatUI = new ChatUI(this.client);
			chatFrame.dispose();
			shouldStop = true;
			
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	chatUI.run();
			            }
			        },delay 
				);	
		});
		
		// TITLE
		String space = "                                                     ";
		String username = client.getEmployeeLoggedIn().getUsername();
		JLabel title = ChatAppUI.createLabel(username.toUpperCase() + space);
		title.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 15));
		title.setForeground(ChatAppUI.frameColor);

		// HEADER (TITLE, BUTTONS)
		JPanel header = ChatAppUI.createPanel(100,40);
		header.setBackground(ChatAppUI.accentColor);
		header.add(title, new FlowLayout());
		
		if(client.getEmployeeLoggedIn().getIsSystemAdmin() == true) {
			header.add(settingsButton, new FlowLayout());
		}
		header.add(createChatLogButton, new FlowLayout());
		header.add(refreshButton, new FlowLayout());
		
		// CHATLOGS & MESSAGES
		JScrollPane scrollableChatLogsContainer = createScrollableChatLogsContainer(client.getEmployeeChatLogList().getChatLogs());
	
		Vector<Message> messages = new Vector<Message>();
		JScrollPane scrollableMessagesContainer = ChatAppUI.createScrollableMessagesContainer(messages);
	    
		// TEXT BAR
	    JTextField textBar = ChatAppUI.createTextField(20, "Enter Message...");
	    textBar.setFont(new Font(ChatAppUI.fontName, Font.PLAIN, 12));
	    
	    // SEND BUTTON
 		JButton sendButton = ChatAppUI.createButton("Send"); 
 	    sendButton.addActionListener((e)-> { 
 	    	if(textBar.getText().length() > 19) {
 	    		System.out.print("Message is too long (Can't exceed 19 characters).");
 	    	}
 	    	else {
 	    		client.sendMessage(this.currentChatLogName, new Message(textBar.getText(), client.getEmployeeLoggedIn().getUsername()));
 	    		textBar.setText("");
 	    		System.out.print("Message sent.");
 	    	}
 	    	
 	    });
	    
 	    //BOTTOM BAR (TEXTBAR, SEND BUTTON)
		JPanel bottomBar = ChatAppUI.createPanel(200, 50); 
		bottomBar.add(textBar); 
		bottomBar.add(sendButton);
		
		JPanel bottomBarContainer = ChatAppUI.createPanel(400, 50);
		bottomBarContainer.add(bottomBar, BorderLayout.LINE_END);
		
		// CHAT FRAME
		chatFrame.add(header, BorderLayout.PAGE_START);
		chatFrame.add(scrollableChatLogsContainer, BorderLayout.LINE_START);
		chatFrame.add(scrollableMessagesContainer, BorderLayout.CENTER);
		chatFrame.add(bottomBar, BorderLayout.PAGE_END);
		chatFrame.setVisible(true);
	}
	
	// UPDATE CHATLOG MESSAGES
	public void update(String chatLogName){
		this.currentChatLogName = chatLogName;
		Vector<Message> messages = client.getEmployeeChatLog(currentChatLogName).getMessages();
		
		for(int i =0; i < messages.size(); i++) {
			messages.get(i).addReader(client.getEmployeeLoggedIn().getUsername());
        	Vector<String> members = client.getEmployeeChatLog(currentChatLogName).getMembers();
			Vector<String> readers = messages.get(i).getReaders();
			if(readers.equals(members)) {
				messages.get(i).setStatus("READ");
			}
		}
		
        shouldStop = false;
        Thread newThread = new Thread(() -> {
            while (!shouldStop) {
                SwingUtilities.invokeLater(() -> {
                	client.getEmployeeChatLog(currentChatLogName).getMessages();
                	Message newMessage = client.getNewMessage(currentChatLogName);
                	
                	newMessage.addReader(client.getEmployeeLoggedIn().getUsername());
                	Vector<String> members = client.getEmployeeChatLog(currentChatLogName).getMembers();
        			Vector<String> readers = newMessage.getReaders();
        			if(readers.equals(members)) {
        				newMessage.setStatus("READ");
        			}
                	
                	if(messages.size() == 0){
                		messages.add(new Message("START A NEW CONVERSATION", "SYSTEM", "NONE"));
            		}
                	else if(!(newMessage.getSender().equals("NONE"))){
                    	if(newMessage != messages.get(messages.size()-1)){
                    		messages.add(newMessage);
                		}
                	}
                	
                	scrollableMessagesContainer = ChatAppUI.createScrollableMessagesContainer(messages);
            		System.out.println("chatLog Pressed: " + this.currentChatLogName);
            		
            		chatFrame.add(scrollableMessagesContainer, BorderLayout.CENTER);
            		chatFrame.setVisible(true);
                });
                
                try {

                   	Thread.sleep(time);
                } catch (InterruptedException ex) {
               }
            }
        });
        newThread.start();
	}
	
	public JScrollPane createScrollableChatLogsContainer(Vector<ChatLog> chatLogs){ // imported from ChatAppUI for easier access to ChatUI member variables
		int numOfChatLogs = chatLogs.size();
		
		// CHATLOG CONTAINER
		JPanel chatLogContainer = ChatAppUI.createPanel(180,50*numOfChatLogs);
		for(int i = 0; i < numOfChatLogs; i++) {
			String name = chatLogs.get(i).getName();
			
			JButton chatLog = ChatAppUI.createButton(name);
			chatLog.setName(name);
			buttonlistener(chatLog);
			
			chatLog.setBackground(ChatAppUI.chatLogContainerColor);
			chatLog.setForeground(ChatAppUI.textColor);
		
			JPanel chatLogButtonContainer = ChatAppUI.createChatLogButtonContainer();
			chatLogButtonContainer.add(chatLog);
			chatLog.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 15));
			chatLog.setForeground(ChatAppUI.accentColor);
			chatLog.setBorder( new MatteBorder(0, 0, 1, 0, ChatAppUI.chatLogBorderColor));
			
			chatLogContainer.add(chatLogButtonContainer);
		}
		
		// CHATLOG SCROLLABLE CONTAINER
		JScrollPane scrollableChatLogContainer = ChatAppUI.createScrollPane(chatLogContainer,220,200);
		Border scrollPaneBorder = BorderFactory.createLineBorder(ChatAppUI.scrollPaneColor, 0);
		scrollableChatLogContainer.setBorder(scrollPaneBorder);
		return scrollableChatLogContainer;
	}

	public void buttonlistener(JButton button) // to enter the desired chat log
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JButton chatLogButton = (JButton)e.getSource();
				shouldStop = true;
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	update(chatLogButton.getName());
				            }
				        },delay 
				);
			}
		});
	}
	
	
}
