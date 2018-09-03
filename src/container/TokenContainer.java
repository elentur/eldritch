package container;

import enums.ItemType;
import gamemechanics.encounter.RumorEncounter;
import model.Item.Token;
import model.Item.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TokenContainer extends ArrayList<Token>{

    public TokenContainer(List<Token> list){
       super(list);
    }
    public TokenContainer(){
        super();
    }

    @Override
    public boolean add(Token value){
        return value != null && super.add(value);
    }

    @Override
    public void add(int index,Token value){
        if(value==null){
            return;
        }
        super.add(index,value);
    }

    public GateToken getGate() {
       return  (GateToken) this.stream().filter(token->token.getSubType().equals(ItemType.GATE_TOKEN)).findFirst().orElse(null);
    }

    public ExpeditionToken getExpedition() {
        return  (ExpeditionToken) this.stream().filter(token->token.getSubType().equals(ItemType.EXPEDITION_TOKEN)).findFirst().orElse(null);
    }

    public MysteryToken getMystery() {
        return  (MysteryToken) this.stream().filter(token->token.getSubType().equals(ItemType.MYSTERY_TOKEN)).findFirst().orElse(null);
    }
    public RumorToken getRumor() {
        return  (RumorToken) this.stream().filter(token->token.getSubType().equals(ItemType.RUMOR_TOKEN)).findFirst().orElse(null);
    }

    public List<ClueToken> getClues() {
        return this.stream().filter(token->token.getSubType().equals(ItemType.CLUE_TOKEN)).map(token->(ClueToken)token).collect(Collectors.toList());
    }

    public List<EldritchToken> getEldritchTokens() {
        return this.stream().filter(token->token.getSubType().equals(ItemType.ELDRITCH_TOKEN)).map(token->(EldritchToken)token).collect(Collectors.toList());
    }
}
