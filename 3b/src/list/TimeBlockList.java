package list;

import java.util.ArrayList;

import Classes.TimeBlock;

public class TimeBlockList {
	private ArrayList<TimeBlock> timeblocks = new ArrayList<TimeBlock>();
	private ArrayList<TimeBlock> uniqueTimeBlocks = new ArrayList<TimeBlock>();
	public TimeBlockList(){};
	
	public void generateList(RequestList requests){
		String time = "";
		for(int index = 1; index < requests.getSize(); index++){ //ignore first line, title line
			time = requests.getItem(index, 9);
			System.out.println(time);
			if(time != null)
				parseTime(time);
			else
				timeblocks.add(new TimeBlock(null,null,null));
			
			time = requests.getItem(index, 10);
			System.out.println(time);
			if(time != null)
				parseTime(time);
			else
				timeblocks.add(new TimeBlock(null,null,null));
			
			time = requests.getItem(index, 11);
			System.out.println(time);
			if(time != null)
				parseTime(time);
			else
				timeblocks.add(new TimeBlock(null,null,null));
			
			time = requests.getItem(index, 12);
			System.out.println(time);
			if(time != null)
				parseTime(time);
			else
				timeblocks.add(new TimeBlock(null,null,null));
			
		}
		uniqueParse();
	}
	
	private boolean checkDuplicate(TimeBlock time){
		String day = time.getDay();
		String begin = time.getBeginTime();
		String end = time.getEndTime();
		
		for(int index = 0; index < uniqueTimeBlocks.size(); index++){
			if(day == uniqueTimeBlocks.get(index).getDay()
					&& begin == uniqueTimeBlocks.get(index).getBeginTime()
					&& end == uniqueTimeBlocks.get(index).getEndTime()
					){
				return true;
			}
		}
		return false;
	}
	
	private void parseTime(String time){
		//parsing Mon/Wed - 9:45 - 12:15 pm
		String Day, BeginTime, EndTime;
		char letter;
		String[] word = {"","","","","",""};
		int wordIndex = 0;
		for(int count = 0; count < time.length(); count++){
			letter = time.charAt(count);
			if(letter != ' '){
				//System.out.print(letter);
				word[wordIndex] = word[wordIndex] + letter;
			} else {
				wordIndex++;
			}
		}
		Day = word[0];
		BeginTime = word[2];
		EndTime = word[4];
		timeblocks.add(new TimeBlock(Day, BeginTime, EndTime));
	}
	
	private void uniqueParse(){
		for(int index = 0; index < timeblocks.size(); index++){
			if(!checkDuplicate(timeblocks.get(index))){
				uniqueTimeBlocks.add(timeblocks.get(index));
			}
		}
	}
	
	public TimeBlock getTimeBlock(int index){
		return timeblocks.get(index);
	}
	
	public int getSize(){
		return timeblocks.size();
	}

	public String[] getArrayDays(){
		String[] i = new String[timeblocks.size() + 1];
		i[0] = "";
		for(int s=0; s<timeblocks.size(); s++)
			i[s+1] = timeblocks.get(s).getDay();
		return i;
			
	}
	
	public String[] getArrayHours(){
		String[] i = new String[timeblocks.size() + 1];
		i[0] = "";
		for(int s=0; s<timeblocks.size(); s++)
			i[s+1] = timeblocks.get(s).getBeginTime() + " - " + timeblocks.get(s).getEndTime();
		return i;
			
	}
}
