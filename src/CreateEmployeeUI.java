import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateEmployeeUI{
	Client client;
	JFrame createFrame = ChatAppUI.createFrame(500,500, "Create Employee");
	
	CreateEmployeeUI(Client client){
		this.client = client;
	}
	
	public void run(){
		// TEXT FIELDS
		JTextField usernameField = ChatAppUI.createTextField(15, "Username");
		JTextField passwordField = ChatAppUI.createTextField(15, "Password");
		JCheckBox isSystemEmployeeCheckBox = new JCheckBox("System Admin");
		JTextField fakeField = ChatAppUI.createTextField(0, ""); // used to solve a UI issue
		
		// CREATE BUTTON
		JButton createButton = ChatAppUI.createButton("Create");
		
		createButton.addActionListener((e)-> { 
	    	if(this.client.getEmployeeLoggedIn().getIsSystemAdmin() == true) {
	    		this.client.createEmployee(usernameField.getText(), passwordField.getText(), isSystemEmployeeCheckBox.isSelected());
		    	createFrame.dispose();
		    	ChatUI chatUI = new ChatUI(this.client);
				chatUI.run();
	    	}
	    });
		
		// TITLE
		JLabel title = ChatAppUI.createLabel("Create           ");
		title.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 30));
		title.setForeground(ChatAppUI.accentColor);
		
		// LOGIN
		JPanel createPanel = ChatAppUI.createPanel(0,0);
		createPanel.add(title);
		createPanel.add(Box.createRigidArea(new Dimension(0, 70)));
		createPanel.add(usernameField);
		createPanel.add(Box.createRigidArea(new Dimension(10, 50)));
		createPanel.add(passwordField);
		createPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		createPanel.add(isSystemEmployeeCheckBox);
		createPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		createPanel.add(createButton);
		createPanel.add(Box.createRigidArea(new Dimension(200, 0)));
		
		// LAYOUT
        BoxLayout boxlayout = new BoxLayout(createPanel, BoxLayout.PAGE_AXIS);
        createPanel.setLayout(boxlayout);
        createPanel.setBorder(new EmptyBorder(new Insets(100, 100, 200, 100)));
		
		// FRAME
        createFrame.add(fakeField); // used to solve a UI issue
        createFrame.add(createPanel);
        createFrame.setVisible(true);
	}
	
	
}