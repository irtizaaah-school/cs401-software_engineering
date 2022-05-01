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

public class ListUI {
	
    protected Vector<Employee> allEmployees;
    protected Vector<ChatLog> userChatLogs;
    
    JFrame frame;
    
    public ListUI(Vector<Employee> employees, Vector<ChatLog> logs)
    {
    	allEmployees = employees;
    	userChatLogs = logs;
    }  
    
    public ListUI(Vector<Employee> employees)
    {
    	allEmployees = employees;
    	userChatLogs = new Vector<ChatLog>();
    }  
    
	public void run()
	{
		//Initialize users
		DefaultListModel<Employee> users = new DefaultListModel<Employee>();
		updateuser(users);
		JList userlist = new JList(users);
		setleftrender(userlist);
		JScrollPane userspane = new JScrollPane(userlist);
		userspane.setViewportView(userlist);
		
		//Initialize chatlogs
		DefaultListModel<ChatLog> logs = new DefaultListModel<>();
		
		if(!userChatLogs.isEmpty())
		{
			for(int i = 0; i < userChatLogs.size();i++)
			{
				logs.add(i, userChatLogs.get(i));
			}
		}
		
		JList chatlist = new JList(logs);
		setrightrender(chatlist);
		JScrollPane chatlogpane = new JScrollPane();
		chatlogpane.setViewportView(chatlist);
		

		//Create Frame and display
		frame = createui(userspane,chatlogpane,"+"); //JFrame
		frame.setVisible(true);
   }
	
	//Update users to UI
	protected void updateuser(DefaultListModel<Employee> users)
	{
		users.removeAllElements();
		Vector<Employee> tempusers = allEmployees;
		if(!tempusers.isEmpty())
		{
			Collections.sort(tempusers,Comparator.comparing(Employee::getIsOnline).reversed().thenComparing(Employee::getUsername));     
		    for(int i = 0; i < tempusers.size();i++)
		    	users.add(i,tempusers.get(i));
		}
	}
	
	// Create UI Elements
	protected JFrame createui(JScrollPane list1, JScrollPane list2,String buttonname)
	{
		JFrame frame = createframe();
		  

	    setbackgroundcolor(list1,list2);
	      
	    list1.setSize(new Dimension(900,200));
	    
	    JPanel masterpanel = new JPanel();
	    masterpanel.setLayout(new BoxLayout(masterpanel,BoxLayout.Y_AXIS));
	      
	    JPanel top = createPanel(900,600);
	    top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
	    top.add(list1);
	    top.add(list2);
	    
	    JPanel bottom = createPanel(900,40);
	    JButton createchat = createButton(buttonname); 
	    buttonlistener(createchat);
	    
	    bottom.setBackground(ChatColor.panelColor);
	    bottom.add(createchat,BorderLayout.SOUTH);
	      
	    masterpanel.add(top);
	    masterpanel.add(bottom);
	    
	    frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
	    frame.add(masterpanel);
	    
		return frame;
	}
	
	


	protected JPanel createPanel(int width, int height){
		JPanel panel = new JPanel(); 
		panel.setBackground(ChatColor.panelColor);
		panel.setMaximumSize(new Dimension(width, height));
		panel.setLayout(new FlowLayout());
	
		return panel;
	}
	
	protected JButton createButton(String name){
		JButton button = new JButton(name); 
		button.setPreferredSize(new Dimension(80, 25));
		button.setBackground(ChatColor.buttonColor);
		button.setOpaque(true);
		Border buttonBorder = BorderFactory.createLineBorder(ChatColor.buttonColor, 1);
		button.setBorder(buttonBorder);
		button.setForeground(ChatColor.textColor);
	
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
      
	protected void setbackgroundcolor(JScrollPane user, JScrollPane log)
	{
		user.getViewport().getView().setBackground(ChatColor.panelColor);
		log.getViewport().getView().setBackground(ChatColor.panelColor);
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
	            	  System.out.println(list.getSelectedValue());
	              }
	          }
	   });
   }
 
   protected void buttonlistener(JButton button)//,JFrame frame)
   {
	   button.addActionListener(new ActionListener()
	   {
		   public void actionPerformed(ActionEvent e)
		   {
		      
			   CreateLogUI createlog = new CreateLogUI(allEmployees,userChatLogs);//,thelog.getAllChatLogs());
		       frame.dispose();
		       createlog.run();

		       //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
	              setForeground(Color.WHITE);  
	              if (isSelected) 
	              {
	                 setBackground(ChatColor.isselectedColor);
	                 setBorder(new LineBorder(ChatColor.isselectedborderColor, 1, true));
	              }
	              else
	              {
	            	  setBackground(ChatColor.panelColor);
	                  setBorder(null);
	              }
	              return this;
	          }

	    });   
   }
 
   @SuppressWarnings({ "unchecked", "serial", "unused", "rawtypes" })
   
   protected void setleftrender(JList list)
   {
	   list.setCellRenderer(new DefaultListCellRenderer() {

	          @Override
	          public Component getListCellRendererComponent(JList list, Object value, int index,
	                    boolean isSelected, boolean cellHasFocus) {
	        	  
	               if (value instanceof Employee) {
	            	   Employee user = (Employee) value;
	                    setText(user.getUsername());
	                   
	                    if (user.getIsOnline() == true) {
	                    	setForeground(Color.GREEN);
	                    } else
	                         setForeground(Color.RED);
	                   
	                    setBackground(ChatColor.panelColor);
	                    setBorder(null);
	               }
	               return this;
	          }

	     });
   }
}