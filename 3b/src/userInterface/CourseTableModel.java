
package userInterface;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Classes.Course;
import Classes.TimeBlock;


public class CourseTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3245276;
	private Vector<String> cNames;	
	private Vector<Object> data;
	private ArrayList<Course> courses;
	
	public CourseTableModel(){
		super();
		loadData();
		courses = new ArrayList<Course>();
	} 
	
	public CourseTableModel(ArrayList<Course> l){
		super();
		loadData();
		courses = l;
	} 

	public void setData(Vector<Object> data){
		this.data = data;
	}
	public void addItem(Course instructor ){
		courses.add(instructor);
		fireTableDataChanged();
	}
	public Course getItem(int i){
		return courses.get(i);
	}
	public Course removeItem(int i){
		Course c = courses.remove(i);
		fireTableDataChanged();
		return c;
	}
	
	@Override
	public String getColumnName(int c){
		return cNames.get(c);
	}
	@Override
	public int getColumnCount() {
		
		return cNames.size();
	}

	@Override
	public int getRowCount() {
		
		//return data.size();
		//return requests.getSize();
		return courses.size();
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
		case 4 : return c.getNotes();
		case 5 : return c.getPriority();
		
		
		}
		return "";
	}
	

	private void loadData() {
		String[] colums = new String[] {
				"Course", "CRN", "Days", "Hours", "Notes", "Priority"
			};
		cNames	 = new Vector<String>(); 
		for(int i=0; i<colums.length; i++)
			cNames.add(colums[i]);
		
	}
}