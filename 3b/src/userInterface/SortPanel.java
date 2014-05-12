package userInterface;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import scheduler.DbConnection;
import Classes.Course;
import Classes.Instructor;
import Classes.TimeBlock;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import list.CourseList;
import list.InstructorList;
import list.TimeBlockList;

interface SortPanelListener{
	public void sort(String[] s);
}
public class SortPanel extends JPanel {
	private SortPanelListener mListener;

	/**
	 * Create the panel.
	 * @param courseList 
	 * @param timeBlockList 
	 * @param instructorList 
	 */
	public SortPanel(SortPanelListener l, DbConnection db) {
		mListener = l;
		//iniArr();

		setBackground(new Color(193, 4, 53));
		if(db == null)
			return;
		iniPanel(l, db);
	}
	
	public void iniPanel(SortPanelListener l, DbConnection db){
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight()); 
		Dimension d = new Dimension((int) (xSize*.15 -10), 250);
		//Dimension d = new Dimension(200, 200);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		setBackground(new Color(193, 4, 53));
		setLayout(new GridLayout( 10,2));
		
		
		//setLayout(new FlowLayout());
		JLabel sortLabel = new JLabel("Filter By: ");
		sortLabel.setFont(new Font("Serif", Font.BOLD, 16));
		sortLabel.setForeground(Color.WHITE);
		add(sortLabel);
		add(new JLabel());
		
//		
		JLabel courseLabel = new JLabel("Course: ");
		courseLabel.setFont(new Font("Serif", Font.BOLD, 16));
		courseLabel.setForeground(Color.WHITE);
		add(courseLabel);
		final JComboBox sortCourses = new JComboBox();
		sortCourses.setModel(new DefaultComboBoxModel(db.getCourseNames()));
		add(sortCourses);
		
//		JLabel courseCrnLabel = new JLabel("CRN: ");
//		courseCrnLabel.setFont(new Font("Serif", Font.BOLD, 16));
//		courseCrnLabel.setForeground(Color.WHITE);
//		add(courseCrnLabel);
//		final JComboBox sortCoursesCrn = new JComboBox();
//		sortCourses.setModel(new DefaultComboBoxModel(courseCodeArr));
//		add(sortCoursesCrn);
		
		JLabel instructorLabel = new JLabel("Instructor: ");
		instructorLabel.setFont(new Font("Serif", Font.BOLD, 16));
		instructorLabel.setForeground(Color.WHITE);
		add(instructorLabel);
		final JComboBox sortInstructors = new JComboBox();
		sortInstructors.setModel(new DefaultComboBoxModel(db.getInstructorNames()));
		add(sortInstructors);
		
		JLabel instructorIdLabel = new JLabel("Instructor ID: ");
		instructorIdLabel.setFont(new Font("Serif", Font.BOLD, 16));
		instructorIdLabel.setForeground(Color.WHITE);
		add(instructorIdLabel);
		final JComboBox sortInstructorsID = new JComboBox();
		sortInstructorsID.setModel(new DefaultComboBoxModel(db.getInstructorID()));
		add(sortInstructorsID);
		
		JLabel daysLabel = new JLabel("Days: ");
		daysLabel.setFont(new Font("Serif", Font.BOLD, 16));
		daysLabel.setForeground(Color.WHITE);
		add(daysLabel);
		final JComboBox sortDays = new JComboBox();
		sortDays.setModel(new DefaultComboBoxModel(db.getTimeDays()));
		add(sortDays);
		
		JLabel hoursLabel = new JLabel("Hours: ");
		hoursLabel.setFont(new Font("Serif", Font.BOLD, 16));
		hoursLabel.setForeground(Color.WHITE);
		add(hoursLabel);
		final JComboBox sortHours = new JComboBox();
		sortHours.setModel(new DefaultComboBoxModel(db.getTimeHours()));
		add(sortHours);
		
		JLabel textLabel = new JLabel("Search: ");
		textLabel.setFont(new Font("Serif", Font.BOLD, 16));
		textLabel.setForeground(Color.WHITE);
		add(textLabel);
		final JTextField text = new JTextField();
		add(text);
		
		
		//BUTTON FOR TO SORT THE JTABLE AROUND
		final JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Action caught");
				if(a.getSource() == resetButton){
					sortInstructors.setSelectedIndex(0);
					sortDays.setSelectedIndex(0);
					sortHours.setSelectedIndex(0);
					sortCourses.setSelectedIndex(0);
					//sortDepartment.setSelectedIndex(0);
					//sortCoursesCrn.setSelectedIndex(0);
					sortInstructorsID.setSelectedIndex(0);
					text.setText("");
					String[] s = {""};
					mListener.sort(s);
				}
				
			}
		});
		add(resetButton);
		
		//BUTTON FOR TO SORT THE JTABLE AROUND
		JButton sortButton = new JButton("Filter");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Action caught");
				//if(a.getSource() == sortComboBox){
				String[] s = new String[6];
				s[0] = (String) sortInstructors.getSelectedItem();
				s[1] = (String) sortDays.getSelectedItem();
				s[2] = (String) sortHours.getSelectedItem();
				s[3] = (String) sortCourses.getSelectedItem();
			//	s[4] = (String) sortDepartment.getSelectedItem();
				s[4] = (String) sortInstructorsID.getSelectedItem();
				
					s[5] = text.getText();
					//s[5] = (String) sortCoursesCrn.getSelectedItem();
					System.out.println(s);
					mListener.sort(s);
			
			}
		});
		add(sortButton);

	}

}


