import java.awt.Color;
import java.util.Random;
import java.util.Vector;

public class ChatColor {
	public static Color frameColor = new Color(0x333C83);
	public static Color textColor = new Color(0xEEEEEE);
	public static Color textColorAlt = new Color(0x333C83);
	public static Color textFieldOrAreaColor = new Color(0x6770b8);
	public static Color buttonColor = new Color(0xF24A72);
	public static Color panelColor = new Color(0x333C83);
	public static Color scrollPaneColor = new Color(0x333C83);
	public static Color scrollBarColor = new Color(0xF24A72);
	public static Color messageColor = new Color(0x333C83);
	public static Color isselectedColor = new Color(0xb8cfe5);
	public static Color isselectedborderColor = new Color(0x6382bf);
	public static Color getRandomColor() {
	    Random rand = new Random();
	    Vector<Integer> givenList = new Vector();
	    int randomElement = 0x000000;
	    
	    givenList.add(0xF55353);
	    givenList.add(0xEEEEEE);
	    givenList.add(0x77D970);
	    givenList.add(0xFF0075);
	    givenList.add(0xFA9905);
	    givenList.add(0x7579E7);

	    int numberOfElements = 2;

	    for (int i = 0; i < numberOfElements; i++) {
	        int randomIndex = rand.nextInt(givenList.size());
	        randomElement = givenList.get(randomIndex);
	    }
	    
		return new Color(randomElement);
	}
	
}