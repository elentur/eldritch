package oldVersion.gameMechanics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Object> events;
	
	
	public Event (Object...events){
		this.events=new ArrayList<Object>(Arrays.asList(events));
	}
	
	public List<Object> getEvents(){
		return events;
	}
}
