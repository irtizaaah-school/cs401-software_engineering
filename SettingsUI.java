import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
	
public class SettingsUI {
	Client client;
	JFrame settingsFrame = ChatAppUI.createFrame(500,500, "Settings");

	SettingsUI(Client client){
		this.client = client;
	}
	
	public void run(){
		// TEXT FIELDS
		JTextField usernameField = ChatAppUI.createTextField(15, "Username");
		JTextField passwordField = ChatAppUI.createTextField(15, "Password");
		JTextField fakeField = ChatAppUI.createTextField(0, ""); // used to solve a UI issue
		
		// BUTTONS
		JButton createEmployeeButton = ChatAppUI.createButton("Create");
		JButton deleteEmployeeButton = ChatAppUI.createButton("Delete");
		JButton viewEmployeeButton = ChatAppUI.createButton("View");
		JButton backButton = ChatAppUI.createButton("Back");
		backButton.setPreferredSize(new Dimension(500, 35));
		backButton.setBackground(ChatAppUI.subtleColor);
		
		// HANDLE BUTTONs
		createEmployeeButton.addActionListener((e)->{ 
			CreateEmployeeUI createEmployeeUI = new CreateEmployeeUI(this.client);
			createEmployeeUI.run();
			settingsFrame.dispose();
	    });
		deleteEmployeeButton.addActionListener((e)-> { 
			DeleteEmployeeUI deleteEmployeeUI = new DeleteEmployeeUI(this.client);
			deleteEmployeeUI.run();
			settingsFrame.dispose();
	    });
		viewEmployeeButton.addActionListener((e)-> { 
			ViewLogs list = new ViewLogs(client);
			
			list.run();
			settingsFrame.dispose();
	    });
		backButton.addActionListener((e)-> { 
			ChatUI chatUI = new ChatUI(client);
			chatUI.run();
			settingsFrame.dispose();
	    });
		
		// TITLE
		JLabel title = ChatAppUI.createLabel("Settings  ");
		title.setFont(new Font(ChatAppUI.fontName, Font.BOLD, 30));
		title.setForeground(ChatAppUI.accentColor);
		
		// SETTINGS PANEL
		JPanel optionPanel = ChatAppUI.createPanel(0,0);
		optionPanel.add(title);
		optionPanel.add(Box.createRigidArea(new Dimension(100, 100)));
		optionPanel.add(createEmployeeButton, new FlowLayout());
		optionPanel.add(deleteEmployeeButton, new FlowLayout());
		optionPanel.add(viewEmployeeButton, new FlowLayout());
		optionPanel.add(backButton);
		
		// SETTINGS FRAME
		settingsFrame.add(fakeField); // used to solve a UI issue
		settingsFrame.add(optionPanel);
		settingsFrame.setVisible(true);
	}
	
}
