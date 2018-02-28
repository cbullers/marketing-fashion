package me.cbullers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Display extends JFrame {


	private static final long serialVersionUID = 1L;
	
	public Display(ArrayList<String[]> items) throws Exception
	{
		int numItems = items.size();
		
		String[][] itemDim = items.toArray(new String[items.size()][items.size()]);
		
		sort(itemDim);
		
		String[][] itemsDim = (String[][]) reverse(itemDim);
		
		this.setTitle(Main.APP_NAME + " Data Display");
		this.setSize(500, 500);
		
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-250, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-250);
		
		JPanel content = new JPanel();
		content.setLayout(null);

		content.setPreferredSize(new Dimension(450, 100+(numItems*25)));
		content.setLocation(0,0);

		JLabel title = new JLabel("Here is what we offer...");
		title.setFont(new Font("as",0,32));
		title.setSize(500,50);
		title.setLocation(65,10);
		content.add(title);
		
		JLabel sub = new JLabel("In order of most purchased");
		sub.setFont(new Font("as",0,24));
		sub.setSize(500,50);
		sub.setLocation(70,45);
		content.add(sub);
		
		int adder = 100;
	    for(int i = 0; i < itemsDim.length; i++)
	    {
	    	JLabel lbl = new JLabel(itemsDim[i][0]);
	    	lbl.setSize(250, 25);
	    	lbl.setLocation(10, (adder)+(25*i));
	    	content.add(lbl);
	    	
	    	JLabel qnt = new JLabel(itemsDim[i][1]);
	    	qnt.setSize(250,25);
	    	qnt.setLocation(400,(adder)+(25*i));
	    	content.add(qnt);
	    }
		
	    JScrollPane scroll = new JScrollPane (content);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          
	    scroll.setSize(500,500);
	    scroll.getVerticalScrollBar().setUnitIncrement(16);
	    
		this.add(scroll);
		this.setVisible(true);
	}
	
	 public static void sort(String[][] hand)
	 {
		 Arrays.sort(hand, new Comparator<String[]>() {
			  public int compare(String[] o1, String[] o2) {
//			    if (o1[0] == o2[0]) {
			      return Integer.compare(Integer.valueOf(o1[1]), Integer.valueOf(o2[1]));
//			    } else {
//			      return Integer.compare(o1[0], o2[0]);
//			    }
			  }
			});
	 }
	 
	   public static Object[] reverse(Object[] arr) {
	        List<Object> list = Arrays.asList(arr);
	        Collections.reverse(list);
	        return list.toArray();
	    }
	
}
