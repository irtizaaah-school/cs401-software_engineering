import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{
	Container container = getContentPane();
	JLabel userLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JCheckBox showPassword = new JCheckBox("Show password");
	
	Frame(){
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		}
	
	public void setLayoutManager(){
		container.setLayout(null);
		}
	
	public void setLocationAndSize(){
		userLabel.setBounds(5, 10, 100, 30);
		passwordLabel.setBounds(5, 40, 100, 30);
		userTextField.setBounds(75, 10, 150, 30);
		passwordField.setBounds(75, 40, 150, 30);
		showPassword.setBounds(95, 70, 150, 30);
		loginButton.setBounds(5, 100, 220, 30);
		}
	
	public void addComponentsToContainer(){
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(showPassword);
		container.add(loginButton);
		}
	
	public void addActionEvent(){
		loginButton.addActionListener(this);
		showPassword.addActionListener(this);
		}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == loginButton){
			String userText;
			String passText;
			userText = userTextField.getText();
			passText = passwordField.getText();
			if (userText.equalsIgnoreCase("admin") && passText.equalsIgnoreCase("admin")){
				JOptionPane.showMessageDialog(this, "Login success");
				}
			else{
				JOptionPane.showMessageDialog(this, "Invalid username/password");
				}
			}
		if (e.getSource() == showPassword){
			if (showPassword.isSelected()){
				passwordField.setEchoChar((char) 0);
				}
			else{
				passwordField.setEchoChar('*');
				}
			}
		}
	}