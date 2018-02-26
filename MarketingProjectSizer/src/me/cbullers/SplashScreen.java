package me.cbullers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SplashScreen extends JFrame
{

	private static final long serialVersionUID = 1L;

	public SplashScreen()
	{
		super();
		this.setSize(500, 250);
		// center
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2)-(this.getWidth()/2), (Toolkit.getDefaultToolkit().getScreenSize().height/2)-(this.getHeight()/2));
		this.setTitle(Main.APP_NAME);
		this.setLayout(null);
		
		SplashScreenFiller filler = new SplashScreenFiller();
		filler.setLocation(0, 0);
		filler.setSize(this.getWidth(), this.getHeight());
		

		this.setContentPane(filler);
		this.getContentPane().setLayout(null);
		this.validate();
		this.revalidate();
		
		this.setVisible(true);
		
	}
	
}

class SplashScreenFiller extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	public SplashScreenFiller()
	{
		super();
		
		
		
		JButton male = new JButton();
		male.setLocation(50+70, 80);
		male.setSize(100, 50);
		male.setText("MALE");
		
		JButton female = new JButton();
		female.setLocation(200+70, 80);
		female.setSize(100, 50);
		female.setText("FEMALE");
		
		this.add(female);
		this.add(male);
		this.validate();
		this.revalidate();
	}

	public void paint(Graphics g)
	{
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.BLACK);
		g.drawString("Welcome to "+Main.APP_NAME, (this.getWidth()/2)-75, 30);
		g.drawString("Firstly, choose your gender", (this.getWidth()/2)-80, 50);
	}
	
}
