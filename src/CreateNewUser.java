package defaultPackage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateNewUser extends JFrame implements ActionListener
{

	JFrame frame = new JFrame();
	JButton submitButton;
	JTextField username,password;
	JLabel usernameLabel,passwordLabel;
	Employee user;

	CreateNewUser()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(100,170,80,20);
		submitButton.addActionListener(this);
		
		username = new JTextField();
		username.setPreferredSize(new Dimension(250,40));
		username.setBounds(40,40,200,30);
		
		usernameLabel = new JLabel();
		usernameLabel.setText("USERNAME");
		usernameLabel.setBounds(110,20,80,20);
		usernameLabel.setForeground(ChatColor.textColor);


		password = new JTextField();
		password.setPreferredSize(new Dimension(250,40));
		password.setBounds(40,120,200,30);
		
		passwordLabel = new JLabel();
		passwordLabel.setText("PASSWORD");
		passwordLabel.setBounds(110,100,80,20);
		passwordLabel.setForeground(ChatColor.textColor);
		
		frame.setTitle("Create A User");
		frame.add(passwordLabel);
		frame.add(usernameLabel);
		frame.add(submitButton);
		frame.add(username);
		frame.add(password);
		frame.pack();
		frame.setSize(300,250);
		frame.setVisible(true);
		frame.getContentPane().setBackground(ChatColor.frameColor);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == submitButton)
		{
			System.out.println("username: " +username.getText()+ "\npassword: " +password.getText());
			user = new Employee(username.getText(),password.getText());
			user.setIsLoggedIn(false);
		}
	}
}
