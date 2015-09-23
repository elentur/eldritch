package gameItems;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import elements.Investigator;
import elements.Monster;
import enums.Actions;
import enums.Path;
import enums.Space;

public class GameBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Field,Space> fields;
	
	public GameBoard(Map<Field,Space> fields){
		this.fields =fields;
	}
	
	public Map<Field,Space> getFields(){
		return fields;
	}
	
	public Field getField(String name){
		for(Field field : fields.keySet()){
			if (field.toString() ==name)return field;
		}
		return null;
	}
	
	public Field getInvestigatorField(Investigator investigator){
		for(Field field:fields.keySet()){
			if(field.containsInvestigator(investigator))return field;
		}
		return null;
	}
	public Field getMonsterField(Monster monster){
		for(Field field:fields.keySet()){
			if(field.containsMonster(monster))return field;
		}
		return null;
	}
	public void moveInvestigator(Investigator investigator,Field newField){
		//if(!investigator.travel()) return
		
		Field oldField = getInvestigatorField(investigator);
		//Testen ob der Weg mit allen mitteln zu schafen ist
		//if(investigator.getShipTickets()+ investigator.getTrainTickets()+1 < shortestWays(oldField, newField).get(0).size()-1)return;
		List<Actions> actions= investigator.getActions();
		//Erlaubt sind nur einfeldwege aber das wird �ber Das Interface realisiert
		if(investigator.travel()){//!actions.contains(Actions.travel) && actions.size()<2){
			oldField.removeInvestigator(investigator);
			newField.addInvestigator(investigator);
			actions.add(Actions.travel);
		}else if( actions.get(actions.size()-1)==Actions.travel ){
			
			Path path =oldField.getNeighbours().get(newField);
			if(path ==Path.ship && investigator.getShipTickets()>0){
				investigator.removeShipTicket();
			}else if(path ==Path.train && investigator.getTrainTickets()>0){
				investigator.removeTrainTicket();
			}else{
				return;
			}
			oldField.removeInvestigator(investigator);
			newField.addInvestigator(investigator);
		}
		
	}
	public void moveMonster(Monster monster,Field newField){
		Field oldField = getMonsterField(monster);
		oldField.removeMonster(monster);
		newField.addMonster(monster);
	}
	
	public List<Field> getAllInvestigatorFields(){
		List<Field> allInvestigatorFields = new ArrayList<Field>();
		for(Field field:fields.keySet()){
			if (!field.getInvestigators().isEmpty()) allInvestigatorFields.add(field);
		}
		return allInvestigatorFields;
	}
	public Set<Field> nextInvestigatorField(Field field){
		List<Field> fields = new ArrayList<Field>();
		Set<Field> fieldSet= new HashSet<Field>();
		if(field.getInvestigators().size()>0){
			fieldSet.add(field);
			return fieldSet;
		}
		List<Field> allInvestigatorFields = getAllInvestigatorFields();
		List<List<Field>> ways = new ArrayList<List<Field>>();
		for(Field investigatorField:allInvestigatorFields){
			ways.addAll(shortestWays(investigatorField,field));
		}
		ways =reduceWays(ways,field);
		for(List<Field> list:ways){
			fields.add(list.get(0));
		}
		
		for(Field f:fields){
			fieldSet.add(f);
		}
		return fieldSet;
	}
	
	public List<List<Field>> shortestWays(Field start,Field end){
		List<List<Field>> ways = new ArrayList<List<Field>>();
		
		if(start==end){
			ways.add(new ArrayList<Field>());
			ways.get(0).add(start);
			return ways;
		}
		Set<Field> visited = new HashSet<Field>();
		ways = shortestWays(start,end,visited,ways);
		ways = splitWays(ways,end);
		
		return reduceWays(ways, end);
	}
	

	private  List<List<Field>> reduceWays(List<List<Field>> ways,Field end){
		int shortest=0;
		List<List<Field>> newWays = new ArrayList<List<Field>>();
		for(List<Field> list:ways){
			if(list.get(list.size()-1) == end){
				newWays.add(list);
				if(shortest==0||shortest >list.size()){
					shortest=list.size();
				}
			}
		}
		ways.clear();
		for(List<Field> list:newWays){
			if(list.size() == shortest){
				ways.add(list);
			}
		}
		return ways;
	}
	private List<List<Field>> splitWays(List<List<Field>> ways,Field end){
		List<List<Field>> splitingWays= new ArrayList<List<Field>>();
		//startKnoten setzen
		splitingWays.add(new ArrayList<Field>());
		splitingWays.get(0).add(ways.get(0).get(0));
		
		int count=0;
		int numberOfFields=0;
		while(true){
			numberOfFields= ways.get(count).size()-2;
			for(int j = 0; j<splitingWays.size();j++){
				if(splitingWays.get(j).get(splitingWays.get(j).size()-1)== ways.get(count).get(0)){
					//dublizieren
					for(int i = 0 ; i < numberOfFields;i++){
						List<Field> list = new ArrayList<Field>();
						list.addAll(splitingWays.get(j));
						splitingWays.add(j,list);
					}
					//einf�gen
					int index=1;
					for(int x = j; x <j+numberOfFields+1;x++){
						try{
								splitingWays.get(x).add(ways.get(count).get(index));
						}catch(Exception e){
							System.out.println("Exception");
							}
					
						index++;
					}
				}
				
			}
			count++;
			if(count ==ways.size())break;
		}
		return splitingWays;
	}
	
	private List<List<Field>> shortestWays(Field start,Field end,Set<Field> visited, List<List<Field>> ways){
		Point i=new Point(0,0);
		boolean run=true;
		while(run){
			if(!visited.contains(start)){
				visited.add(start);
				ways.add(new ArrayList<Field>());
				ways.get(ways.size()-1).add(start);
				for(Field field : start.getNeighbours().keySet()){
					if(!visited.contains(field)){
						ways.get(ways.size()-1).add(field);
					}
				}
			}
			
			while(ways.get(i.x).isEmpty()){
				
				if(i.x< ways.size()-1){
					i.x++;
				}else{
					run=false;
					break;
				}
			}
				if(run)start = ways.get(i.x).get(i.y);
			if (start==end)run=false;
			if (ways.get(i.x).size()-1 ==i.y){
				i.y=0;
				if(i.x< ways.size()-1){
					i.x++;
				}else{
					run =false;
				}
			}else{
				i.y++;
			}
			
		}
		return ways;	
	}
}
