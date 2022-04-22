import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ListUI {
	
    private Vector<SystemAdmin> allSystemAdmins;
    private Vector<Employee> allEmployees;
    private Vector<ChatLog> userChatLogs;
    
    
    public ListUI(Vector<SystemAdmin> admins, Vector<Employee> employees, Vector<ChatLog> logs)
    {
    	allSystemAdmins = admins;
    	allEmployees = employees;
    	userChatLogs = logs;
    }  
 
	public void run()
	{

	  JFrame frame = createframe();
	  
      JScrollPane users = new JScrollPane(createuserJlist());     
      JScrollPane logs = new JScrollPane(createchatlogJlist());
      setbackgroundcolor(users,logs);
      
      users.setSize(new Dimension(900,200));
      frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
      frame.add(users);
      frame.add(logs);
      frame.setVisible(true);
      
   }
   
   private void setbackgroundcolor(JScrollPane user, JScrollPane log)
   {
	   user.getViewport().getView().setBackground(ChatColor.panelColor);
	   log.getViewport().getView().setBackground(ChatColor.panelColor);
   }
   
   private static JFrame createframe() 
   {      
		   JFrame frame = new JFrame("Users and ChatLogs");
	   	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		   frame.setSize(900, 600);      
		   
		   frame.setLocationRelativeTo(null);  // Center on screen
		   frame.setResizable(false);
		   frame.setVisible(true);	// make visible
		   return frame;
		   
   }
   
   private Vector<Employee> createusers()
   {
	   Vector<Employee> users = allEmployees;
	   users.addAll(allSystemAdmins);
	   Collections.sort(users,Comparator.comparing(Employee::getIsLoggedIn).reversed().thenComparing(Employee::getUsername));  
	   return users;
   }	   
   
   @SuppressWarnings({ "unchecked", "serial", "unused", "rawtypes" })
   
   private JList createchatlogJlist()
   {
	   JList userchatlog = new JList(userChatLogs);
	   userchatlog.addMouseListener(new MouseAdapter() 
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
	   
	   userchatlog.setCellRenderer(new DefaultListCellRenderer() 
	   {

	          @Override
	          public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	          {
	        	  ChatLog log = (ChatLog) value;
	              setText(log.getChatLogName());
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
	   
	   return userchatlog;
	   
   }
   
   @SuppressWarnings({ "unchecked", "serial", "unused", "rawtypes" })
   
   private JList createuserJlist()
   {
	   JList users = new JList(createusers());
	   users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
	      users.addMouseListener(new MouseAdapter() {
	          public void mouseClicked(MouseEvent evt) {
	              JList list = (JList)evt.getSource();
	              if (evt.getClickCount() == 2) {
	            	  System.out.println(list.getSelectedValue().toString());
	              }
	             }
	      });
	      users.setCellRenderer(new DefaultListCellRenderer() {

	          @Override
	          public Component getListCellRendererComponent(JList list, Object value, int index,
	                    boolean isSelected, boolean cellHasFocus) {
	        	  
	               if (value instanceof Employee || value instanceof SystemAdmin) {
	            	   Employee user = (Employee) value;
	                    setText(user.getUsername());
	                   
	                    if (user.getIsLoggedIn() == true) {
	                    	setForeground(Color.GREEN);
	                    } else {
	                         setForeground(Color.RED);
	                    }
	                    if (isSelected) {
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
	   
	   return users;
   }
}
