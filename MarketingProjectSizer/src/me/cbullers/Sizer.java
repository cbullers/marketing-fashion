package me.cbullers;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sizer extends JFrame {

	private static final long serialVersionUID = 1L;
	public String temp;
	
	public Sizer()
	{
		this.setTitle(Main.APP_NAME + " Sizer");
		this.setSize(500, 500);
		this.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-250, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-250);
		this.setLayout(null);
		
		JButton b = new JButton("Click Here For Sizes");
		b.setBounds(150,10,200,50);
		String loc = (Main.male ? "http://www.sizeguide.net/size-guide-men-size-chart.html" : "http://www.sizeguide.net/size-guide-women-size-chart.html");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Main.male)
					{
						Desktop.getDesktop().browse(new URI(loc));
					}
					else
					{
						Desktop.getDesktop().browse(new URI(loc));
						Desktop.getDesktop().browse(new URI("http://www.sizeguide.net/womens-clothing-sizes-international-conversion-chart.html"));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		for(int i = 0; i < Main.SIZES.length; i++)
		{
			JButton hoo = new JButton(Main.SIZES[i]);
			temp = Main.SIZES[i];
			hoo.setBounds(150,145+(45*i),200,25);
			hoo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.roadWorkAheadUhYeahISureHopeItDoes = hoo.getText();
					try {
						getTheDataADAM();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						new Display(Main.thingsInCategory);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
					
				}
			});
			add(hoo);
		}
		
		JLabel select = new JLabel("Select your size");
		select.setFont(new Font("asj",0,32));
		select.setBounds(145,75,300,50);
		
		add(b);
		add(select);
		
		this.setVisible(true);
		
	}
	
	private void getTheDataADAM() throws Exception
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String content = "";

				try {
					content = new String(Files.readAllBytes(Paths.get("data.txt")));
				} catch (IOException e1) { e1.printStackTrace(); }
				
				for(String line : content.split("\n"))
				{
					/*
					 * data variable structure
					 * 0: name
					 * 1: gender
					 * 2+: sizing purchase orders
					 */
					String[] data = line.split(",");
					String genString = (Main.male ? "Male" : "Female");
					if(!(data[1].equals(genString) || data[1].equals("Unisex"))) continue;
					if(Integer.valueOf(data[2+Arrays.asList(Main.SIZES).indexOf(Main.roadWorkAheadUhYeahISureHopeItDoes)]) == 0) continue;
					String[] poop = {data[0],data[2+Arrays.asList(Main.SIZES).indexOf(Main.roadWorkAheadUhYeahISureHopeItDoes)]};
					Main.thingsInCategory.add(poop);
				}
				Main.yallReadyForThis = true;
			}
		}).run();
	}
}
