package container;

import model.Investigator;
import model.Item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InvestigatorContainer extends ArrayList<Investigator>{

    public InvestigatorContainer(List<Investigator> list){
       super(list);
    }
    public InvestigatorContainer(){
        super();
    }



    public Investigator get(String s) {
        if(s == null){
            return null;
        }
        Optional<Investigator> opt= this.stream().filter(inv -> s.equals(inv.getId())).findFirst();
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }


}
