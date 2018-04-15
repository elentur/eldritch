package container;

import model.Item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class ItemContainer<T extends Item> extends ArrayList<T>{

    public ItemContainer(List<T> list){
       super(list);
    }
    public ItemContainer(){
        super();
    }



    public T get(String s) {
        if(s == null){
            return null;
        }
        Optional<T> opt= this.stream().filter(item -> s.equals(item.getId())).findFirst();
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }


}
