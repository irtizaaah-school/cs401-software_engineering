import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.Border;

public class Main {
	public static void main(String[] args) {
		
		Vector <Message> allMessages = new Vector();
		for(int i = 0; i <= 10; i++) {
			allMessages.add(new Message("Hi", "John Doe"));
		}
		
		Vector <String> allMembers = new Vector();
		allMembers.add("John Doe");
		allMembers.add("Jane Doe");
		allMembers.add("Joe Doe");
		
		ChatLog chatLogData = new ChatLog("Accounting Department", allMembers);
		chatLogData.setAllMessages(allMessages);
		ChatLogUI chatLogComponent = new ChatLogUI(chatLogData);
		chatLogComponent.run();
	}
}