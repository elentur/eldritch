package container;

import model.Item.Investigator;

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


    @Override
    public boolean add(Investigator value){
        return value != null && super.add(value);
    }

    @Override
    public void add(int index,Investigator value){
        if(value==null){
            return;
        }
        super.add(index,value);
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
