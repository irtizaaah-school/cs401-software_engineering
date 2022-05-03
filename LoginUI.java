import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginUI{
	Client client;
	JFrame loginFrame = ChatAppUI.createFrame(500,500, "Log In");
	
	LoginUI(Client client){
		this.client = client;
	}
	
	public void run(){
		// TEXT FIELDs
		JTextField usernameField = ChatAppUI.createTextField(15, "Username");
		JTextField passwordField = ChatAppUI.createTextField(15, "Password");
		JTextField fakeField = ChatAppUI.createTextField(0, ""); // used to solve a UI issue
		
		// LOGIN BUTTON
		JButton loginButton = ChatAppUI.createButton("Log In");
		loginButton.addActionListener((e)-> { 
	    	if(this.client.login(usernameField.getText(), passwordField.getText()) == true) {
	    		ChatUI chatUI = new ChatUI(client);
		    	chatUI.run();
		    	loginFrame.dispose();
	    	}
	    	
	    	System.out.print("LoginUI: " + "[username: " + usernameField.getText() + " password: " + passwordField.getText() + "]");
	    });
		
		// TITLE
		JLabel title = ChatAppUI.createLabel("Chat App  ");
		title.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 30));
		title.setForeground(ChatAppUI.accentColor);
		
		// LOGIN PANEL
		JPanel loginPanel = ChatAppUI.createPanel(0,0);
		loginPanel.add(title);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		loginPanel.add(usernameField);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		loginPanel.add(passwordField);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		loginPanel.add(loginButton);
		loginPanel.add(Box.createRigidArea(new Dimension(200, 0)));
		
		// LAYOUT
        BoxLayout boxlayout = new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS);
		loginPanel.setLayout(boxlayout);
		loginPanel.setBorder(new EmptyBorder(new Insets(100, 100, 200, 100)));
		
		// FRAME
		loginFrame.add(fakeField); // used to solve a UI issue
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true);
	}
	
	
}