/*package defaultPackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class SettingsGUI extends JFrame implements ActionListener
{
	JButton settingsButton, messagesButton;
	
	SettingsGUI()
	{
		
	}
	
	public void run(){
		
		ImageIcon icon1 = new ImageIcon("settings3.png");

		settingsButton = new JButton();
		settingsButton.setBounds(100,100,250,250);
		settingsButton.addActionListener(this); 
		settingsButton.setText("Settings");
		settingsButton.setFocusable(false);
		settingsButton.setIcon(icon1);
		settingsButton.setHorizontalTextPosition(JButton.CENTER);
		settingsButton.setVerticalTextPosition(JButton.BOTTOM);
		settingsButton.setFont(new Font("Times New Roman",Font.PLAIN,25));
		settingsButton.setIconTextGap(20);
		settingsButton.setForeground(ChatColor.textColor); // text color
		settingsButton.setBackground(ChatColor.buttonColor); // button color
		Border settingsButtonBorder = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
		settingsButton.setBorder(settingsButtonBorder);
		
		ImageIcon icon2 = new ImageIcon("messages3.png");

		messagesButton = new JButton();
		messagesButton.setBounds(400,100,250,250);
		messagesButton.addActionListener(this); 
		messagesButton.setText("Messages");
		messagesButton.setFocusable(false);
		messagesButton.setIcon(icon2);
		messagesButton.setHorizontalTextPosition(JLabel.CENTER);
		messagesButton.setVerticalTextPosition(JLabel.BOTTOM);
		messagesButton.setFont(new Font("Times New Roman",Font.PLAIN,25));
		messagesButton.setIconTextGap(20);
		messagesButton.setForeground(ChatColor.textColor); // text color
		messagesButton.setBackground(ChatColor.buttonColor); // button color
		Border messagesButtonBorder = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
		messagesButton.setBorder(messagesButtonBorder);
		
		this.setTitle("Settings");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(800,500);
		this.setVisible(true);
		this.getContentPane().setBackground(ChatColor.frameColor);
		this.add(settingsButton);
		this.add(messagesButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == settingsButton)
		{
			this.dispose();
			SettingsWindow settingsWindow = new SettingsWindow();
		}
		else if(e.getSource() == messagesButton)
		{
			System.out.println("implementation needed to call messagesUI");
		}
	}
}
*/