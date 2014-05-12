package list;

import java.util.ArrayList;

import Classes.Instructor;

public class InstructorList {
	private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
	private ArrayList<Instructor> uniqueInstructors = new ArrayList<Instructor>();
	public InstructorList(){}
	
	private boolean checkDuplicate(Instructor instructor){
		String ID = instructor.getID();
		for(int index = 0; index < uniqueInstructors.size(); index++){
			if(uniqueInstructors.get(index).getID() == ID){
				return true;
			}
		}
		return false;
	}
	
	private void uniqueParse(){
		for(int index = 0; index < instructors.size(); index++){
			if(!checkDuplicate(instructors.get(index))){
				uniqueInstructors.add(instructors.get(index));
			}		
		}
	}
	public String[] getIDArray(){
		String[] i = new String[instructors.size() + 1];
		i[0] = "";
		for(int s=0; s<instructors.size(); s++)
			i[s+1] = instructors.get(s).getID();
		return i;
			
	}
	
	public String[] getArray(){
		String[] i = new String[instructors.size() + 1];
		i[0] = "";
		for(int s=0; s<instructors.size(); s++)
			i[s+1] = instructors.get(s).getName();
		return i;
			
	}
	
	public void generateList(RequestList requests){
		String name, ID;
		boolean Adjunct;
		for(int count = 1; count < requests.getSize(); count++){ //ignore first line, title line
			name = requests.getItem(count, 1);
			ID = requests.getItem(count, 2);
			if(requests.getItem(count, 3) == "yes"){
				Adjunct = true;
			} else {
				Adjunct = false;
			}
			instructors.add(new Instructor(ID, name,Adjunct));
		}
		uniqueParse();
	}
	
	public int getSize(){
		return instructors.size();
	}
	
	public Instructor getInstructor(int index){
		return instructors.get(index);
	}
	
	public void addInstructor(Instructor instructor){
		instructors.add(instructor);
	}
	
	public void removeInstructor(int index){
		instructors.remove(index);
	}
	
}
