package scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTable;

import userInterface.CourseTableModel;
import userInterface.InstructorTableModel;
import userInterface.TableModel;
import Classes.Course;
import Classes.Instructor;
import Classes.TimeBlock;
import list.RequestList;

public class CSVTools {
	public static void importCSV(TableModel main, CourseTableModel cTable, InstructorTableModel iTable){
		String fileurl;
		JFileChooser chooser = new JFileChooser();
		File file = new File("Schedule.csv");
		
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
		 file = chooser.getSelectedFile();
		 String filePath = file.getPath();
		 	if(!filePath.toLowerCase().endsWith(".csv"))
		 		{//invalid type error
		 		}
		 	
		 	try {
				Scanner in = new Scanner(file);
			
				ArrayList<String> lines = new ArrayList<String>();
				while(in.hasNextLine()){
					String l = in.nextLine();
					System.out.println(l);
						lines.add(l);
				}
				if(lines.size() > 2){	//2 lines in a table,id only, its empty
				 main.setData(loadCourseTable(lines.get(0), lines.subList(1, lines.size()-1)));
				// main.fireTableDataChanged();
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				e.printStackTrace();
			}
		}
		
	}

	private static ArrayList<Course> loadCourseTable(String type, List<String> list){
		ArrayList<Course> courses = new ArrayList<Course>();
		
		
		for(int i= 0; i<list.size(); i++){
			if(list.get(i).startsWith("<"))
				break;	//end of table found
			Scanner line = new Scanner(list.get(i));
			line.useDelimiter(",");
			
			ArrayList<String> data = new ArrayList<String>();
			while(line.hasNext()){
				String l = line.next();
				System.out.println(l);
				
					
				data.add(l);
			}
			System.out.println(data.size() + " items on this line, end found");
			
				
			Instructor prof = new Instructor(data.get(5), data.get(4), data.get(8).equals("true"));
			String start, end, t = data.get(3);
			start = t.substring(0, t.indexOf("-") - 1);
			end = t.substring(t.indexOf("-")+1);
			TimeBlock block = new TimeBlock(data.get(2), start, end);
			courses.add( new Course("" , data.get(0), block, prof, Integer.parseInt(data.get(7)), data.get(6)));
	
		}
		System.out.println(courses.size() + " courses found in file");
		return courses;
	}
		
	public static void saveCSV(JTable main, JTable cTable, JTable iTable){
		String fileurl;
		JFileChooser chooser = new JFileChooser();
		File file = new File("Schedule.csv");
		
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
		 file = chooser.getSelectedFile();
		 String filePath = file.getPath();
		 if(!filePath.toLowerCase().endsWith(".csv"))
		 {
		     file = new File(filePath + ".csv");
		 }

		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println("<main start>");
			printTable(main, writer);
			writer.println("<main end>");
			writer.println("<instructor start>");
			printTable(iTable, writer);
			writer.println("<instructor end>");
			writer.println("<course start>");
			printTable(cTable, writer);
			writer.println("<course end>");
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		} else if(returnVal == JFileChooser.CANCEL_OPTION){
			
		}
	
		
	}
	
	private static void printTable(JTable t, PrintWriter writer){
		for(int i=0; i<t.getRowCount(); i++){
			int jmax = t.getColumnCount() - 1;
			for(int j=0; j<t.getColumnCount(); j++){
				writer.print(t.getValueAt(i, j));
				if(j< jmax) writer.print(",");
			}
			writer.println();
		}
	}
	
	
public static void exportCSV(JTable main){
	String fileurl;
	JFileChooser chooser = new JFileChooser();
	File file = new File("Schedule.csv");
	
	int returnVal = chooser.showSaveDialog(null);
	if(returnVal == JFileChooser.APPROVE_OPTION){
	 file = chooser.getSelectedFile();
	 String filePath = file.getPath();
	 if(!filePath.toLowerCase().endsWith(".csv"))
	 {
	     file = new File(filePath + ".csv");
	 }

	try {
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		int max = main.getColumnCount() - 1;
		for(int i=0; i<main.getColumnCount(); i++){
		writer.print(main.getColumnName(i));
		if(i< max) writer.print(",");
		}
		writer.println();
		printTable(main, writer);
		
		writer.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	} else if(returnVal == JFileChooser.CANCEL_OPTION){
		
	}

	
}
}
