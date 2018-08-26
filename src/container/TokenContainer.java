package container;

import enums.ItemType;
import model.Item.Token;
import model.Item.token.ExpeditionToken;
import model.Item.token.GateToken;

import java.util.ArrayList;
import java.util.List;


public class TokenContainer extends ArrayList<Token>{

    public TokenContainer(List<Token> list){
       super(list);
    }
    public TokenContainer(){
        super();
    }



    public GateToken getGate() {
       return  (GateToken) this.stream().filter(toke->toke.getSubType().equals(ItemType.GATE_TOKEN)).findFirst().orElse(null);
    }

    public ExpeditionToken getExpedition() {
        return  (ExpeditionToken) this.stream().filter(toke->toke.getSubType().equals(ItemType.EXPEDITION_TOKEN)).findFirst().orElse(null);
    }



}
