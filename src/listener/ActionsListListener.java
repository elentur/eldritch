package listener;


import elements.Investigator;
import enums.Actions;
import enums.Path;
import gameBuild.Global;
import javafx.collections.ListChangeListener;

public class ActionsListListener<T> implements ListChangeListener<Actions>{
	private Investigator investigator;

	public ActionsListListener(Investigator investigator) {
		this.investigator = investigator;
	}

	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Actions> c) {
			if(c.getList().size()==2){
				Actions a =c.getList().get(1);
				if(a == Actions.travel){
					if(investigator.getShipTickets()==0 && investigator.getTrainTickets()==0){
						Global.game.getRound().next();
					}else if(investigator.getShipTickets()>0 && 
							!Global.game.getGameBoard().getInvestigatorField(investigator).getNeighbours().containsValue(Path.ship)
							&& investigator.getTrainTickets()==0){
						Global.game.getRound().next();
					}else if(investigator.getTrainTickets()>0 && 
							!Global.game.getGameBoard().getInvestigatorField(investigator).getNeighbours().containsValue(Path.train)
							&& investigator.getShipTickets()==0){
						Global.game.getRound().next();
					}
				}else{
					Global.game.getRound().next();
				}
				
				
			}
			
	}


}
