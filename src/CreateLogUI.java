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

public class CreateLogUI{
	
	
	DefaultListModel<Employee> leftusers = new DefaultListModel<Employee>(); // List of users on the left
	DefaultListModel<Employee> rightusers = new DefaultListModel<Employee>();// List of users on the right
	JFrame frame; // Frame
	
	protected Vector<Employee> allEmployees;
	protected Vector<ChatLog> userChatLogs;
	public CreateLogUI(Vector<Employee> employees,Vector<ChatLog> userLogs) {
		allEmployees = employees;
		userChatLogs = userLogs;
		
	}
	
	public void run()
	{
		//Initialize Left list
		for(int i = 0; i < allEmployees.size();i++)
			leftusers.add(i,allEmployees.get(i));
		
		JList leftuserlist = new JList(leftusers);
		setleftrender(leftuserlist);
		JScrollPane leftuserspane = new JScrollPane(leftuserlist);
		leftuserspane.setViewportView(leftuserlist);
		
		
		//Initialize Right List
		JList rightuserlist = new JList(rightusers);
		setrightrender(rightuserlist);
		JScrollPane rightuserspane = new JScrollPane(rightuserlist);
		rightuserspane.setViewportView(rightuserlist);
		
		//Add listeners
		addleftlistener(leftuserlist,leftusers,rightusers);
		addrightlistener(rightuserlist,leftusers,rightusers);
		
		frame = createui(leftuserspane,rightuserspane,"Submit"); //JFrame
		frame.setVisible(true);

   }
	
	
	
	
	

	      	
	// Create UI Elements
	protected JFrame createui(JScrollPane list1, JScrollPane list2,String buttonname)
	{
		JFrame frame = createframe();
		  

	    setbackgroundcolor(list1,list2);
	      
	    //list1.setSize(new Dimension(900,200));
	    
	    JPanel masterpanel = new JPanel();
	    masterpanel.setLayout(new BoxLayout(masterpanel,BoxLayout.Y_AXIS));
	      
	    JPanel top = createPanel(900,600);
	    top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
	    top.add(list1);
	    top.add(list2);
	    
	    JPanel bottom = createPanel(900,40);
	    
	    // Create Button and add listener
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
	
	
	protected static JFrame createframe() 
	{      
		JFrame frame = new JFrame("Create New Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);      
			   
		frame.setLocationRelativeTo(null);  
		frame.setResizable(false);
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
	
	//Changes background colors
	protected void setbackgroundcolor(JScrollPane user, JScrollPane log)
	{
	   user.getViewport().getView().setBackground(ChatColor.panelColor);
	   log.getViewport().getView().setBackground(ChatColor.panelColor);
	}
   

	
	
	
	//Listeners
 @SuppressWarnings({ "unchecked", "serial", "unused", "rawtypes" })
	 
    protected void buttonlistener(JButton button)
	{
	   button.addActionListener(new ActionListener()
	   {
	     public void actionPerformed(ActionEvent e)
	     {
	        if(!rightusers.isEmpty())
	        {
	        	boolean alreadyexist = false;
	        	String ChatLogname = "";
	        	Vector<String> members = new Vector<String>();
	        	
	        	for(int i = 0; i < rightusers.size();i++)
	        		{
	        			members.add(rightusers.get(i).getUsername());
	        			//ChatLogname += rightusers.get(i);
	        		}
	        	Collections.sort(members);
	        
	        	for(int i = 0; i < members.size();i++)
	        	{	
	        		ChatLogname += members.get(i);
	        		if(i != members.size() - 1)
	        			ChatLogname += ",";
	        	}
	        	
	        	ChatLog newlog = new ChatLog(ChatLogname, members);

	        	for(int i = 0; i < userChatLogs.size();i++)
	        	{
	        		if(userChatLogs.get(i).getMembers().equals(newlog.getMembers()))
	        			alreadyexist = true;
	        	}
	        	
	        	if(alreadyexist == false)
	        		userChatLogs.add(newlog);        	
	        }	
        	frame.dispose();
        	new ListUI(allEmployees, userChatLogs).run();
	        
	     }
	   });
	}
	
	
	protected void addrightlistener(JList list,DefaultListModel<Employee> leftlist,DefaultListModel<Employee> rightlist)
	{
		list.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt) 
			{
				JList list = (JList)evt.getSource();
		    	if (evt.getClickCount() == 2) 
		    		removeuserfromtemplog(list.getSelectedValue(),leftlist,rightlist);
			}
				});
	}
		 
	protected void addleftlistener(JList list,DefaultListModel<Employee> leftlist,DefaultListModel<Employee> rightlist)
	{
		   list.addMouseListener(new MouseAdapter() 
		   {
			   public void mouseClicked(MouseEvent evt) 
			   {
				   JList list = (JList)evt.getSource();
			       if (evt.getClickCount() == 2) 
			       {
			    	   adduserfromtemplog(list.getSelectedValue(),leftlist,rightlist);
			    	   
			       }
			    }
			});
	 }
	
	//Functions used by listener
	protected void removeuserfromtemplog(Object employee,DefaultListModel<Employee> leftlist,DefaultListModel<Employee> rightlist)
	{
		rightlist.removeElement((Employee) employee);//adduserfromtemplog(list.getSelectedValue());
	    leftlist.addElement((Employee) employee);
	}
	 
	 protected void adduserfromtemplog(Object employee,DefaultListModel<Employee> leftlist,DefaultListModel<Employee> rightlist)
	 {
		 leftlist.removeElement((Employee) employee);//adduserfromtemplog(list.getSelectedValue());
		 rightlist.addElement((Employee) employee);
		 
	 }
	
	 
	 // Sets UI Renders, needed to change colors
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
		               }
		               return this;
		          }

		     });
	   }
	   @SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	   protected void setrightrender(JList list)
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
		               }
		               return this;
		          }

		     });
	   }
}

