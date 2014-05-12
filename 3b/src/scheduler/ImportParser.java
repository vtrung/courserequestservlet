package scheduler;
//METHODS:
//void parse: inputs(Scanner)
//int getSize: inputs(none)
//Request getRequest: input(none);
//ArrayList<Request> getArrayList(): input(none);
//
//USE:
//created new ImportParser object. Then create scanner object.
//use parse method with argument of scanner object
//parse into ArrayList of Request; please adjust parse method and when Request is changed

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import list.CourseList;
import list.InstructorList;
import list.RequestList;
import list.TimeBlockList;

public class ImportParser {
	private JFileChooser chooser = new JFileChooser();
	private RequestList requests = new RequestList(); //Parse csv into this RequestList
	private InstructorList instructors = new InstructorList();
	private TimeBlockList timeblocks = new TimeBlockList();
	private CourseList courses = new CourseList();
	
	public ImportParser(){}; //Constructor
	
	private void requestAdd(ArrayList<String> request){
		int listLength = request.size();
		String[] requestArray = new String[13];
		for(int count = 0; count < listLength; count++){
			requestArray[count] = request.get(count);
		}
		requests.add(requestArray);
	} //Converts ArrayList to array to import to RequestList
	
	private void generateList(){
		instructors.generateList(requests);
		timeblocks.generateList(requests);
		courses.generateList(requests,instructors,timeblocks);
	}
	
	public void chooseFile(){
		int returnVal = chooser.showOpenDialog(null);
		File myfile = new File("save.txt");
		if(returnVal == JFileChooser.APPROVE_OPTION){
			myfile = chooser.getSelectedFile();
			Scanner in;
			try {
				in = new Scanner(myfile);
				parse(in);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void parse(Scanner in){ //Reads one line at a time
		String line;
		String word;
		char letter;
		
		while(in.hasNext()){
			ArrayList<String> request = new ArrayList<String>();
			line = in.nextLine();//System.out.println(line);
			word = "";
			
			for(int count = 0; count < line.length(); count++){
				letter = line.charAt(count);
				if(letter != ','){
					//System.out.print(letter);
					word += letter;
				} else {
					request.add(word);
					//System.out.print("AR" + arrayCount);
					word = "";
				}
			}
			request.add(word);
			requestAdd(request);
		};
		
		in.close();
		generateList();
	}
	
	public int getSize(){
		return requests.getSize();
	}
	
	public String[] getRequest(int index){
		return requests.getArray(index);
	}
	
	public RequestList getRequestList(){
		return requests;
	}
	
	public CourseList getCourseList(){
		return courses;
	}
	
	public InstructorList getInstructorList(){
		return instructors;
	}
	
	public TimeBlockList getTimeBlockList(){
		return timeblocks;
	}
	
}
