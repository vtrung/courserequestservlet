package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{

	public InfoPanel(){
		super();
		setupPanel();
	}
	private JLabel instructors, courses, timeblocks;
	private JLabel in, c, t;
	
	public void setData(String i, String c, String t){
		instructors.setText(i);
		courses.setText(c);
		timeblocks.setText(t);
	}
	private void setupPanel(){
		setBackground(new Color(193, 4, 53));
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		Dimension d = new Dimension((int) (xSize*.15 -10), 200);
		//Dimension d = new Dimension(200, 200);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		
		setLayout(new GridLayout(6, 2));
		
//		in = new JLabel("  Instructors");
//		c = new JLabel("  Courses");
//		t = new JLabel("  Time Blocks");
		instructors = new JLabel("");
		instructors.setFont(new Font("Serif", Font.BOLD, 16));
		instructors.setForeground(Color.WHITE);
		courses = new JLabel("");
		courses.setFont(new Font("Serif", Font.BOLD, 16));
		courses.setForeground(Color.WHITE);
		timeblocks = new JLabel("");
		timeblocks.setFont(new Font("Serif", Font.BOLD, 16));
		timeblocks.setForeground(Color.WHITE);
		
		
		//add(in);
		add(instructors);
		//add(c);
		add(courses);
		//add(t);
		add(timeblocks);
		
	}
}
