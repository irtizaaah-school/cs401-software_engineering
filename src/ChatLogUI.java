//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.util.Vector;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.ListSelectionModel;
//import javax.swing.border.Border;
//
//public class ChatLogUI {
//	
//	private String chatLogName;
//	private int numOfMessages;
//	private ChatLog chatLog;
//
//	private String user;
//	private Message latestSentMessage;
//	
//	public ChatLogUI(ChatLog chatLog, String user)
//	{
//		this.chatLog = chatLog;
//		this.chatLogName = this.chatLog.getChatLogName();
//		this.numOfMessages = this.chatLog.getAllMessages().size();
//		this.user = user;
//		
//	}
//	
//	public void run() {
//		// HEADER 
//		JLabel headerTitle = createLabel(chatLogName);
//		JPanel header = createPanel(600, 50); 
//		header.add(headerTitle); 
//		
//		// MESSAGE PANE
//		JPanel messageContainer = createPanel(500,55*numOfMessages);
//		for(int i = 0; i < numOfMessages; i++) {
//			String sender = this.chatLog.getAllMessages().get(i).getSender();
//			String content = this.chatLog.getAllMessages().get(i).getContent();
//			String status = "Sent";
//			if(this.chatLog.getAllMessages().get(i).getIsDelivered() == false) {
//				status = "Sent";
//			}
//			else if(this.chatLog.getAllMessages().get(i).getIsDelivered() == true){
//				status = "Delivered";
//			}
//			else if(this.chatLog.getAllMessages().get(i).getReadBy().size() == this.chatLog.getAllMembers().size() ){
//				status = "Read";
//			}
//			JPanel message = createMessage(sender, content, status, ChatColor.getRandomColor());
//			messageContainer.add(message);
//
//
//		}
//		
//		JScrollPane messageScrollContainer = createScrollPane(messageContainer,100,10);
//		
//		
//		// BOTTOM BAR (TEXTBAR, SUBMIT BUTTON)
//		// TEXT BAR
//	    JTextField textBar = createTextField(20);
//	    textBar.setFont(new Font("Courier", Font.PLAIN, 20));
//	    
//	    // SUBMIT BUTTON
//	    JButton sendButton = createButton("Send"); 
//	    sendButton.addActionListener((e)-> { 
//	    	this.latestSentMessage = new Message(textBar.getText(), this.user);
//	    	if(textBar.getText().length() > 19) {
//	    		System.out.print("Message is too long (Can't exceed 19 characters).");
//	    	}
//	    	else {
//	    		System.out.print("Message sent.");
//	    	}
//	    	
//	    });
//				
//		JPanel bottomBar = createPanel(600, 50); 
//		bottomBar.add(textBar); 
//		bottomBar.add(sendButton);
//		
//		// CREATE FRAME
//		JFrame frame = createFrame(600,600);
//		frame.setResizable(false);
//		frame.add(header, BorderLayout.PAGE_START);
//		frame.add(messageScrollContainer, BorderLayout.CENTER);
//		frame.add(bottomBar, BorderLayout.PAGE_END);
//		frame.setVisible(true);
//		
//	
//	}
//
//	private JPanel createMessage(String sender, String content, String status, Color senderColor) {
//		JPanel message = createPanel(500,50);
//		message.setLayout(new BorderLayout());
//		JLabel messageSender = createLabel(sender);
//		messageSender.setFont(new Font("Courier", Font.BOLD,13));
//		messageSender.setForeground(senderColor);
//		JLabel messageContent = createLabel(content);
//		JLabel messageStatus = createLabel(status);
//		messageStatus.setFont(new Font("Courier", Font.ITALIC,13));
//		//JLabel border = createLabel("------------------------------------------------------------------------");
//	
//		message.add(messageSender, BorderLayout.PAGE_START);
//		message.add(messageContent,  BorderLayout.CENTER);
//		message.add(messageStatus,  BorderLayout.LINE_END);
//		//message.add(border,  BorderLayout.PAGE_END);
//		message.setBackground(ChatColor.messageColor);
//		
//		return message;
//	}
//	
//	private JFrame createFrame(int width, int height){
//		JFrame frame = new JFrame(); 
//		frame.setTitle("Chat App");
//		frame.setLayout(new BorderLayout());
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(true);
//		frame.setSize(width,height);
//		frame.getContentPane().setBackground(ChatColor.frameColor);
//		frame.setMinimumSize(new Dimension(600,600));
//		
//		return frame;
//	}
//	
//	private JPanel createPanel(int width, int height){
//		JPanel panel = new JPanel(); 
//		panel.setBackground(ChatColor.panelColor);
//		panel.setPreferredSize(new Dimension(width, height));
//		panel.setLayout(new FlowLayout());
//	
//		return panel;
//	}
//	
//	private JButton createButton(String name){
//		JButton button = new JButton(name); 
//		button.setPreferredSize(new Dimension(80, 25));
//		button.setBackground(ChatColor.buttonColor);
//		button.setOpaque(true);
//		Border buttonBorder = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
//		button.setBorder(buttonBorder);
//		button.setForeground(ChatColor.textColor);
//	
//		return button;
//	}
//	
//	private JTextArea createTextArea(int width, int height){
//		 JTextArea textArea = new JTextArea("",width, height); // default width = 1, height = 20
//		 textArea.setFont(new Font("Courier", Font.PLAIN, 13));
//		 textArea.setForeground(ChatColor.textColor);
//		 textArea.setBackground(ChatColor.textFieldOrAreaColor);
//		 Border textAreaBorder = BorderFactory.createLineBorder(ChatColor.textFieldOrAreaColor, 1);
//		 textArea.setBorder(textAreaBorder);
//	
//		return textArea;
//	}
//	
//	private JTextField createTextField(int width){
//		 JTextField textField = new JTextField("",width);
//		 textField.setFont(new Font("Courier", Font.PLAIN, 13));
//		 textField.setForeground(ChatColor.textColor);
//		 textField.setBackground(ChatColor.textFieldOrAreaColor);
//		 Border textFieldBorder = BorderFactory.createLineBorder(ChatColor.textFieldOrAreaColor, 1);
//		 textField.setBorder(textFieldBorder);
//	
//		return textField;
//	}
//	
//	private JLabel createLabel(String text) {
//		JLabel label = new JLabel(text);
//		label.setFont(new Font("Courier", Font.PLAIN, 13));
//		label.setForeground(ChatColor.textColor);
//		
//		return label;
//	}
//	
//	private JScrollPane createScrollPane(JPanel component, int width, int height) {
//		JScrollPane scrollPane = new JScrollPane(component);
//		scrollPane.setBackground(ChatColor.scrollPaneColor);
//		scrollPane.getVerticalScrollBar().setBackground(ChatColor.scrollBarColor);
//		scrollPane.setPreferredSize(new Dimension(width, height));
//		
//		return scrollPane;
//	}
//	private <T> JList createList(Vector<T> items) {
//		JList list = new JList(items);
//		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		list.setVisibleRowCount(-1);
//		
//		return list;
//	}
//
//}
