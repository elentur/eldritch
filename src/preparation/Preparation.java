package preparation;

import enums.TestTyp;
import model.Investigator;
import model.Monster;

public interface Preparation {

    Investigator getInvestigator();

    Monster getMonster();

    TestTyp getTestTyp();

    int getModification();

    void setTestTyp(TestTyp testTyp);

    void setModification(int mod);

    int getModifiedSkill();
}
