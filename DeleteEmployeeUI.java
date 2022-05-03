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

public class DeleteEmployeeUI{
	Client client;
	JFrame deleteFrame = ChatAppUI.createFrame(500,500, "Delete Employee");
	
	DeleteEmployeeUI(Client client){
		this.client = client;
	}
	
	public void run(){
		// TEXT FIELDS
		JTextField usernameField = ChatAppUI.createTextField(15, "Username");
		JTextField fakeField = ChatAppUI.createTextField(0, ""); // used to solve a UI issue
		
		// // DELETE BUTTON
		JButton deleteButton = ChatAppUI.createButton("Delete");
		deleteButton.addActionListener((e)-> { 
	    	if(this.client.getEmployeeLoggedIn().getIsSystemAdmin() == true) {
	    		this.client.deleteEmployee(usernameField.getText());
		    	deleteFrame.dispose();
		    	ChatUI chatUI = new ChatUI(this.client);
				chatUI.run();
	    	}
	    });
		
		// TITLE
		JLabel title = ChatAppUI.createLabel("Delete           ");
		title.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 30));
		title.setForeground(ChatAppUI.accentColor);
		
		// DELETE PANEL
		JPanel deletePanel = ChatAppUI.createPanel(0,0);
		deletePanel.add(title);
		deletePanel.add(Box.createRigidArea(new Dimension(0, 100)));
		deletePanel.add(usernameField);
		deletePanel.add(Box.createRigidArea(new Dimension(0, 50)));
		deletePanel.add(deleteButton);
		deletePanel.add(Box.createRigidArea(new Dimension(200, 0)));
		
		// LAYOUT
        BoxLayout boxlayout = new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS);
        deletePanel.setLayout(boxlayout);
        deletePanel.setBorder(new EmptyBorder(new Insets(100, 100, 200, 100)));
		
		// FRAME
        deleteFrame.add(fakeField); // used to solve a UI issue
        deleteFrame.add(deletePanel);
        deleteFrame.setVisible(true);
	}
	
	
}