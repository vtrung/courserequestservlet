package Classes;

public class Instructor extends Person {
	
	private String ID;
	private String name;
	private Boolean adjunct;
	
	public Instructor()
	{
		name="";
		ID="";
		adjunct=false;
	}
	
	public Instructor(String mID, String mName, Boolean mAdjunct){
		name=mName;
		ID=mID;
		adjunct=mAdjunct;
	}
	
	public String getName()
	{
		return name; //return instructor's name
	}
	
	public String getID()
	{
		return ID; //return instructor's ID
	}
	
	public boolean isAdjunct()
	{
		return adjunct;
	}
	
	public void setName(String mName){
		name = mName;
	}
	
	public void setID(String mID){
		ID = mID;
	}
	
	public void setAdjunct(boolean mAdjunct){
		adjunct = mAdjunct;
	}
	
}
