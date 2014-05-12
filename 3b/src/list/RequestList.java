package list;

import java.util.ArrayList;

public class RequestList {

	private ArrayList<String[]> items = new ArrayList<String[]>();
	
	public RequestList(){
		
	};
	
	public int getSize(){
		return items.size();
	}
	
	public String[] getArray(int index){
		return items.get(index);
	}
	
	public String getItem(int lineIndex, int index){
		return items.get(lineIndex)[index];
	}
	
	public void add(String[] input){
		items.add(input);
	}
	
	public void remove(int index){
		items.remove(index);
	}
	
}
