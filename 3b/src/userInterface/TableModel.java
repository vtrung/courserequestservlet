package userInterface;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import list.CourseList;
import list.RequestList;
import Classes.Course;
import Classes.Instructor;
import Classes.TimeBlock;


public class TableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1953946214652644314L;
	String[] colums = new String[] {
			"Course", "CRN", "Days", "Hours", "Instructor", "Instructor ID", "Notes", "Priority", "Adjunct"
		};
	private ArrayList<Course> courses;
	
	public TableModel(ArrayList<Course> l){
		super();
		courses = l;
	} 

	public void setData(ArrayList<Course> dat){
		courses = dat;
		fireTableDataChanged();
	}
	public void addItem(Course course ){
		courses.add(course);
		fireTableDataChanged();
	}
	@Override
	public Class getColumnClass(int c) {
		 if(c == 8)
			 return Boolean.class;
		 
           //return getValueAt(0, c).getClass();
		 return String.class;
     }

	@Override
	public String getColumnName(int c){
		return colums[c];
	}
	@Override
	public int getColumnCount() {
		
		return colums.length;
	}

	@Override
	public int getRowCount() {
		
		return courses.size();
	}

	public void swapCourses(int i, int i2){
		Instructor temp = courses.get(i).getInstructor();
		TimeBlock t = courses.get(i).getTimeBlock();
		courses.get(i).swapInstructor(courses.get(i2).getInstructor());
		courses.get(i).swapTimeBlocks(courses.get(i2).getTimeBlock());
		courses.get(i2).swapInstructor(temp);
		courses.get(i2).swapTimeBlocks(t);
		fireTableDataChanged();
	}
	public void swapInstructors(int i, int i2){
		Instructor temp = courses.get(i).getInstructor();
		courses.get(i).swapInstructor(courses.get(i2).getInstructor());
		courses.get(i2).swapInstructor(temp);
		fireTableDataChanged();
	}
	public Course removeItem(int i){
		
		Course c = courses.remove(i);
		fireTableDataChanged();
		return c;
		
	}

	@Override
	public Object getValueAt(int x, int y) {
		Course c = courses.get(x);
		switch(y){
		case 0 : return c.getName();
		case 1 : return c.getCode();
		case 2 : return c.getTimeBlock().getDay();
		
		case 3 : TimeBlock time = c.getTimeBlock();
			return time.getBeginTime() + " - " + time.getEndTime();
		case 4 : return c.getInstructor().getName();
		case 5 : return  c.getInstructor().getID();
		case 6 : return c.getNotes();
		case 7 : return c.getPriority();
		case 8 : return c.getInstructor().isAdjunct();
		
		}
		return "";
	}
	
	//no direct editing yet. too buggy
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex){
//		if(!isCellEditable(rowIndex, columnIndex))
//			return;
//			
//		Course c = courses.get(rowIndex);
//		switch(columnIndex){
//		case 0 :  c.setName((String)aValue);
//		case 1 :  c.setCode((String) aValue);
//		case 2 :  c.getTimeBlock().setDay((String)aValue);
//		
//		//case 3 : TimeBlock time = c.getTimeBlock();
//			// time.getBeginTime() + " - " + time.getEndTime() + " " + time.getAMPM();
//		case 4 :  c.getInstructor().setName((String) aValue);
//		case 5 :   c.getInstructor().setID((String) aValue);
//		case 6 :  c.setNotes((String) aValue);
//		case 7 :  c.setRequestPriority(Integer.parseInt((String)aValue));
//		case 8 : c.getInstructor().setAdjunct(Boolean.parseBoolean((String)aValue));
//		
//		}
//	}
//	@Override
//	public boolean	isCellEditable(int rowIndex, int columnIndex){
//		if(columnIndex == 3)
//		return false;
//		
//		return true;
//	}

}