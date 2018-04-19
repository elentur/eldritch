package preparation;

import enums.TestTyp;

public interface Preparation {
    TestTyp getTestTyp();

    int getModification();

    void setTestTyp(TestTyp testTyp);

    void setModification(int mod);

    int getModifiedSkill();
}
