package Classes;

public class Course {
	
	private String Dept;
	public void setDept(String dept) {
		Dept = dept;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setProf(Instructor prof) {
		Prof = prof;
	}

	public void setBlock(TimeBlock block) {
		Block = block;
	}

	public void setRequestPriority(int requestPriority) {
		this.requestPriority = requestPriority;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	private String Code;
	private String Name;
	private Instructor Prof;
	private TimeBlock Block;
	private int requestPriority;
	private String notes;
	
	public Course()
	{
		Dept = "";
		Name = "";
	}
	
	public Course(String department, String name, TimeBlock block, Instructor prof, int priority, String notes)
	{
		Dept = department;
		Name = name;
		Block = block;
		Prof = prof;
		requestPriority = priority;
		this.notes = notes;
	}
	
	public String getNotes(){
		return notes;
	}
	public int getPriority(){
		return requestPriority;
	}
	public String getName(){
		return Name;
	}
	public TimeBlock getTimeBlock(){
		return Block;
	}
	public String getDept()
	{
		return Dept; //return the department, such as Math and CS
	}
	
	public String getCode()
	{
		return Code; //return the course number
	}
	public void setCode(String code){
		Code = code;
	}
	public Instructor removeInstructor(){
		Instructor temp = Prof;
		Prof = null;
		return temp;
	}
	public Instructor getInstructor(){
		return Prof;
	
	}
	public void setInstructor(Instructor i){
		Prof = i;
	}
	//sets new instructor and returns previous one
	public Instructor swapInstructor(Instructor newProf){
		Instructor temp = Prof;
		Prof = newProf;
		return temp;
	}
	public TimeBlock swapTimeBlocks(TimeBlock newBlock){
		TimeBlock temp = Block;
		Block = newBlock;
		return temp;
	}
	
}
