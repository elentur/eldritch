package gamemechanics.choice;


import enums.YesNo;

public class YesNoChoice extends Choice{
    private YesNo value;

    public YesNoChoice(String headline, String info){
        super(headline,info);
    }

    public void setValue(YesNo value){
        if(!choiceTaken.getValue()) {
            this.value = value;
            choiceTaken.setValue(true);
        }
    }
    public YesNo getValue(){
        return value;
    }

}
