
package userInterface;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Classes.Instructor;


public class InstructorTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3245276;
	private Vector<String> cNames;	
	private Vector<Object> data;
	private ArrayList<Instructor> instructors;
	
	public InstructorTableModel(){
		super();
		loadData();
		instructors = new ArrayList<Instructor>();
	} 
	
	public InstructorTableModel(ArrayList<Instructor> l){
		super();
		loadData();
		instructors = l;
	} 

	public void setData(Vector<Object> data){
		this.data = data;
	}
	public void addItem(Instructor instructor ){
		instructors.add(instructor);
		fireTableDataChanged();
	}
	public Instructor getItem(int i){
		return instructors.get(i);
	}
	public Instructor removeItem(int i){
		
		Instructor temp = instructors.remove(i);
		 fireTableRowsDeleted(i, i);
		 return temp;
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
		return instructors.size();
	}

	@Override
	public Object getValueAt(int x, int y) {
		Instructor c = instructors.get(x);
		switch(y){
		case 0 : return c.getName();
		case 1 : return c.getID();

		
		}
		return "";
	}
	

	private void loadData() {
		String[] colums = new String[] {
				"Instructor", "Instructor ID"
			};
		cNames	 = new Vector<String>(); 
		for(int i=0; i<colums.length; i++)
			cNames.add(colums[i]);
		
	}
}