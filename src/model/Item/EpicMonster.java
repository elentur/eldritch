package model.Item;

import Service.GameService;

public class EpicMonster extends Monster {
    public EpicMonster() {
        super(1);
    }

    @Override
    public void discard(){
        GameService.getInstance().getFieldOfMonster(this).removeMonster(this);
    }
}
