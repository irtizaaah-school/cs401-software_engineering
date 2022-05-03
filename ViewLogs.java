import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ViewLogs {
	
    
	Client client;
    JFrame frame;
    
    public ViewLogs(Client _client)
    {
    	client = _client;

    }  
    
    
	public void run()
	{
		//Initialize chatlogs
		DefaultListModel<ChatLog> logs = new DefaultListModel<>();
		
		if(!client.getAllChatLogList().getChatLogs().isEmpty())//userChatLogs.isEmpty())
		{
			for(int i = 0; i < client.getAllChatLogList().getChatLogs().size();i++)
			{
				logs.add(i, client.getAllChatLogList().getChatLogs().get(i));
			}
		}
		
		JList chatlist = new JList(logs);
		setrightrender(chatlist);
		addrightlistener(chatlist);
		JScrollPane chatlogpane = new JScrollPane();
		chatlogpane.setViewportView(chatlist);
		

		//Create Frame and display
		frame = createui(chatlogpane,"Back"); //JFrame
		frame.setVisible(true);
   }
	
	
	// Create UI Elements
	protected JFrame createui(JScrollPane list,String buttonname)
	{
		JFrame frame = createframe();
		  

	    setbackgroundcolor(list);
	      
	
	    
	    JPanel masterpanel = new JPanel();
	    masterpanel.setLayout(new BoxLayout(masterpanel,BoxLayout.Y_AXIS));
	      
	    JPanel top = createPanel(900,600);
	    top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
	    top.add(list);
	    
	    JPanel bottom = createPanel(900,40);
	    JButton back = createButton(buttonname); 
	    buttonlistener(back);
	    
	    bottom.setBackground(ChatAppUI.panelColor);
	    bottom.add(back,BorderLayout.SOUTH);
	      
	    masterpanel.add(top);
	    masterpanel.add(bottom);
	    
	    frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
	    frame.add(masterpanel);
	    
		return frame;
	}
	
	


	protected JPanel createPanel(int width, int height){
		JPanel panel = new JPanel(); 
		panel.setBackground(ChatAppUI.panelColor);
		panel.setMaximumSize(new Dimension(width, height));
		panel.setLayout(new FlowLayout());
	
		return panel;
	}
	
	protected JButton createButton(String name){
		JButton button = new JButton(name); 
		button.setPreferredSize(new Dimension(80, 25));
		button.setBackground(ChatAppUI.subtleColor);
		button.setOpaque(true);
		Border buttonBorder = BorderFactory.createLineBorder(ChatAppUI.buttonColor, 1);
		button.setBorder(buttonBorder);
		
		return button;
	}
	

   protected static JFrame createframe() 
   {      
		   JFrame frame = new JFrame("Users and ChatLogs");
	   	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setSize(900, 600);      
		   
		   frame.setLocationRelativeTo(null);  
		   frame.setResizable(false);
		   return frame; 
   }
      
	protected void setbackgroundcolor(JScrollPane list)
	{
		list.getViewport().getView().setBackground(ChatAppUI.panelColor);
		
	}
	   
   //Listeners
   @SuppressWarnings({ "unchecked", "serial", "unused", "rawtypes" })
   
   protected void addrightlistener(JList list)
   {
	   list.addMouseListener(new MouseAdapter() 
	   {
	          public void mouseClicked(MouseEvent evt) 
	          {
	              JList list = (JList)evt.getSource();
	              if (evt.getClickCount() == 2) 
	              {
	            	  list.getSelectedValue();
	            	  openlog(list.getSelectedValue());
	            	  System.out.println(list.getSelectedValue());
	              }
	          }
	   });
   }
 
   protected void openlog(Object log)
   {
	   JFrame viewFrame = ChatAppUI.createFrame(500,500, "View Log");
	   viewFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	   Vector<Message> messages = client.getChatLog(log.toString()).getMessages();
	  
	   JScrollPane scrollableMessagesContainer = ChatAppUI.createScrollableMessagesContainer(messages);
	   viewFrame.add(scrollableMessagesContainer);
	   viewFrame.setVisible(true);
   }
   
   protected void buttonlistener(JButton button)//,JFrame frame)
   {
	   button.addActionListener(new ActionListener()
	   {
		   public void actionPerformed(ActionEvent e)
		   {
		      
		       SettingsUI settingui = new SettingsUI(client);
		       settingui.run();
			   frame.dispose();

		    }
		});
	}
   
   //Sets UI Renders, needed to change colors
   @SuppressWarnings({ "rawtypes", "unchecked", "serial" })
   protected void setrightrender(JList list)
   {
	   list.setCellRenderer(new DefaultListCellRenderer() 
	   {

	          @Override
	          public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	          {
	        	  ChatLog log = (ChatLog) value;
	              setText(log.getName());
	              setForeground(Color.BLUE);  
	              if (isSelected) 
	              {
	                 setBackground(ChatAppUI.isselectedColor);
	                 setBorder(new LineBorder(ChatAppUI.isselectedborderColor, 1, true));
	              }
	              else
	              {
	            	  setBackground(ChatAppUI.panelColor);
	                  setBorder(null);
	              }
	              return this;
	          }

	    });   
   }
 
}
