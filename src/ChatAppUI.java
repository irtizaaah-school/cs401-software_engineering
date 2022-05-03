import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ChatAppUI {
	// COLORS
	public static Color frameColor = new Color(0xffffff);
	public static Color accentColor = new Color(0x205375);
	public static Color subtleColor = new Color(0xBCBCBC);
	public static Color textColor = new Color(0x212121);
	public static Color textColorAlt = new Color(0xEFEFEF);
	public static Color textFieldOrAreaColor = new Color(0xEFEFEF);
	public static Color buttonColor = new Color(0x205375);
	public static Color panelColor = new Color(0xffffff);
	public static Color scrollPaneColor = new Color(0xffffff);
	public static Color scrollBarColor = new Color(0xffffff);
	public static Color messageColor = new Color(0x5584AC);
	public static Color isselectedColor = new Color(0xb8cfe5);
	public static Color isselectedborderColor = new Color(0x6382bf);
	public static Color chatLogBorderColor = new Color(0xEFEFEF);
	public static Color messageContainerColor = new Color(0xEFEFEF);
	public static Color chatLogContainerColor = new Color(0xffffff);
	public static Color onlineColor = new Color(0x9FC088);
	public static Color offlineColor = new Color(0xCC704B);
	public static Color getRandomColor() {
	    Random rand = new Random();
	    Vector<Integer> givenList = new Vector();
	    int randomElement = 0x000000;
	    
	    givenList.add(0xFFF56D);
	    givenList.add(0xEEEEEE);
	    givenList.add(0xF24A72);
	    givenList.add(0x00C897);
	    givenList.add(0xFF7BA9);
	    givenList.add(0xFFAD60);
	    givenList.add(0x92A9BD);

	    int numberOfElements = 2;

	    for (int i = 0; i < numberOfElements; i++) {
	        int randomIndex = rand.nextInt(givenList.size());
	        randomElement = givenList.get(randomIndex);
	    }
	    
		return new Color(randomElement);
	}
	
	// DEFAULT
	public static String fontName = "Tahoma";
	public static int fontSize = 13;
	public static int borderPadding = 10;
	
	
	public static JPanel createMessage(String content, String sender, String status) {
		JPanel message = createPanel(300,50);
		message.setLayout(new BorderLayout());
		
		JLabel messageSender = createLabel(sender);
		messageSender.setFont(new Font(fontName, Font.BOLD,fontSize));
		messageSender.setForeground(ChatAppUI.textColorAlt);
		
		JLabel messageContent = createLabel(content);
		messageContent.setForeground(ChatAppUI.textColorAlt);
		
		JLabel messageStatus = createLabel(status);
		messageStatus.setForeground(ChatAppUI.textColorAlt);
		messageStatus.setFont(new Font(fontName, Font.ITALIC,fontSize));

		message.add(messageSender, BorderLayout.PAGE_START);
		message.add(messageContent,  BorderLayout.CENTER);
		message.add(messageStatus,  BorderLayout.LINE_END);
		//message.add(border,  BorderLayout.PAGE_END);
		message.setBackground(ChatAppUI.messageColor);
		message.setBorder(new TextBubbleBorder(ChatAppUI.messageContainerColor,1,ChatAppUI.borderPadding,0));
		
		
		return message;
	}
	
	public static JScrollPane createScrollableMessagesContainer(Vector<Message> messages){
		int numOfMessages = messages.size();
			
		//MESSAGE CONTAINER 
		JPanel messagesContainer = ChatAppUI.createPanel(180,50*numOfMessages);
		messagesContainer.setBackground(ChatAppUI.messageContainerColor);
		
		for(int i = 0; i < numOfMessages; i++) {
			
			String content = messages.get(i).getContent();
			String sender = messages.get(i).getSender();
			String status = messages.get(i).getStatus();
			
			// MESSAGE PANEL
			JPanel message = ChatAppUI.createMessage(content, sender, status);
			messagesContainer.add(message);
		}
		
		//MESSAGE SCROLLABLE CONTAINER
		JScrollPane scrollableMessageContainer = ChatAppUI.createScrollPane(messagesContainer,220,200);
		Border scrollPaneBorder = BorderFactory.createLineBorder(ChatAppUI.scrollPaneColor, 0);
		scrollableMessageContainer.setBorder(scrollPaneBorder);
		
		return scrollableMessageContainer;
	}
	
	public static JPanel createChatLogButtonContainer() {
		JPanel chatLogButtonContainer = ChatAppUI.createPanel(180,40);
		chatLogButtonContainer.setBorder(new TextBubbleBorder(ChatAppUI.frameColor,1,2,0));
		chatLogButtonContainer.setBorder( new MatteBorder(0, 0, 1, 0, ChatAppUI.chatLogBorderColor));
		chatLogButtonContainer.setBackground(ChatAppUI.frameColor);
		
		return chatLogButtonContainer;
	}
	
	public static JFrame createFrame(int width, int height, String name){
		JFrame frame = new JFrame(); 
		frame.setTitle(name);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width,height);
		frame.getContentPane().setBackground(ChatAppUI.frameColor);
		frame.setMinimumSize(new Dimension(600,600));
		frame.setVisible(true);
		return frame;
	}
	
	public static JPanel createPanel(int width, int height){
		JPanel panel = new JPanel(); 
		panel.setBackground(ChatAppUI.panelColor);
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(new FlowLayout());
	
		return panel;
	}
	
	public static JButton createButton(String name){
		JButton button = new JButton(name); 
		button.setPreferredSize(new Dimension(80, 35));
		button.setBackground(ChatAppUI.buttonColor);
		button.setOpaque(true);
		Border buttonBorder = BorderFactory.createLineBorder(ChatAppUI.buttonColor, borderPadding);
		button.setBorder(buttonBorder);
		button.setForeground(ChatAppUI.textColorAlt);
		button.setBorder(new TextBubbleBorder(ChatAppUI.buttonColor,0,ChatAppUI.borderPadding,0));
	
		return button;
	}
	
	public static JTextArea createTextArea(int width, int height, String text){
		 JTextArea textArea = new JTextArea(text,width, height); // default width = 1, height = 20
		 textArea.setFont(new Font(fontName, Font.ITALIC, fontSize));
		 textArea.setForeground(ChatAppUI.subtleColor);
		 textArea.setBackground(ChatAppUI.textFieldOrAreaColor);
		 //Border textAreaBorder = BorderFactory.createLineBorder(ChatAppUI.textFieldOrAreaColor, borderPadding);
		 //textArea.setBorder(textAreaBorder);
		 textArea.setBorder(new TextBubbleBorder(ChatAppUI.textFieldOrAreaColor,0,ChatAppUI.borderPadding,0));
		 textArea.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		        	textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
		        	textArea.setForeground(ChatAppUI.textColor);
		        	textArea.setText("");
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
		    });
	
		return textArea;
	}
	
	public static JTextField createTextField(int width, String text){
		 JTextField textField = new JTextField(text,width);
		 textField.setFont(new Font(fontName, Font.ITALIC, fontSize));
		 textField.setForeground(ChatAppUI.subtleColor);
		 textField.setBackground(ChatAppUI.textFieldOrAreaColor);
		 //Border textFieldBorder = BorderFactory.createLineBorder(ChatAppUI.textFieldOrAreaColor, borderPadding);
		 //textField.setBorder(textFieldBorder);
		 textField.setBorder(new TextBubbleBorder(ChatAppUI.textFieldOrAreaColor,0,ChatAppUI.borderPadding,0));
		 textField.addFocusListener(new FocusListener(){
		        @Override
		        public void focusGained(FocusEvent e){
		   		 	textField.setFont(new Font(fontName, Font.PLAIN, fontSize));
		   		 	textField.setForeground(ChatAppUI.textColor);
		            textField.setText("");
		        }

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
		    });
		 
		return textField;
	}
	
	public static JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font(fontName, Font.PLAIN, fontSize));
		label.setForeground(ChatAppUI.textColor);
		
		return label;
	}
	
	public static JScrollPane createScrollPane(JPanel component, int width, int height) {
		JScrollPane scrollPane = new JScrollPane(component);
		scrollPane.setBackground(ChatAppUI.scrollPaneColor);
		scrollPane.getVerticalScrollBar().setBackground(ChatAppUI.scrollBarColor);
		scrollPane.setPreferredSize(new Dimension(width, height));
		Border scrollPaneBorder = BorderFactory.createLineBorder(ChatAppUI.scrollPaneColor, borderPadding);
		scrollPane.setBorder(scrollPaneBorder);
		
		return scrollPane;
	}
	public static <T> JList createList(Vector<T> items) {
		JList list = new JList(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		return list;
	}
}