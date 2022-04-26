package defaultPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class ViewAllMessages extends JFrame implements ActionListener
{
	JFrame frame = new JFrame();
	JCheckBox testBox;
	
	ViewAllMessages() 
	{
		testBox = new JCheckBox();
		testBox.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
		
		frame.add(testBox);
		frame.setTitle("All Messages");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.getContentPane().setBackground(ChatColor.frameColor);	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//if(e.getSource() == submitButton)
		{
			
		}
	}
}
	