package preparation;

import container.BonusContainer;
import enums.EventTimeType;
import enums.TestTyp;
import model.Investigator;
import model.Item.ItemBonus;
import model.Monster;

public interface Preparation {

    Investigator getInvestigator();

    Monster getMonster();

    TestTyp getTestTyp();

    int getModification();

    void setTestTyp(TestTyp testTyp);

    void setModification(int mod);

    int getModifiedSkill();

    BonusContainer<ItemBonus> getBoni(EventTimeType eventTim);

    int getBonusModification();

}
