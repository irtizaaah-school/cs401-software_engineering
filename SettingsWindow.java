/*package defaultPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class SettingsWindow extends JFrame implements ActionListener
{
	JFrame frame = new JFrame();
	JButton newUserButton,viewMessagesButton;
	
	SettingsWindow()
	{
		newUserButton = new JButton();
		newUserButton.setBounds(100,100,200,100);
		newUserButton.addActionListener(this); 
		newUserButton.setText("Create New User");
		newUserButton.setFocusable(false);
		newUserButton.setFont(new Font("Times New Roman",Font.PLAIN,25));
		newUserButton.setForeground(ChatColor.textColor); // text color
		newUserButton.setBackground(ChatColor.buttonColor); // button color
		Border userButtonBorder = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
		newUserButton.setBorder(userButtonBorder);
		
		
		viewMessagesButton = new JButton();
		viewMessagesButton.setBounds(100,250,200,100);
		viewMessagesButton.addActionListener(this); 
		viewMessagesButton.setText("View All Messages");
		viewMessagesButton.setFocusable(false);
		viewMessagesButton.setFont(new Font("Times New Roman",Font.PLAIN,25));
		viewMessagesButton.setForeground(ChatColor.textColor); // text color
		viewMessagesButton.setBackground(ChatColor.buttonColor); // button color
		Border messagesButtonBorder2 = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
		viewMessagesButton.setBorder(messagesButtonBorder2);

		
		frame.setTitle("Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(400,500);
		frame.setVisible(true);
		frame.getContentPane().setBackground(ChatColor.frameColor);
		frame.add(newUserButton);
		frame.add(viewMessagesButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newUserButton)
		{
			this.dispose();
			CreateNewUser userWindow = new CreateNewUser();		
		}
		else if(e.getSource() == viewMessagesButton)
		{
			this.dispose();
			ListUI listUI = new ListUI(Vector<Employee> employees, Vector<ChatLog> logs);
			listUI.run();
		}
	}
}
*/