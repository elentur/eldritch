package preparation;

import enums.TestTyp;

public class CombatPreparation implements Preparation{

        private TestTyp testTyp;


        private int modification;

        public CombatPreparation() {
            this.testTyp = TestTyp.STRENGTH;
            this.modification = 0;
        }

        @Override
        public TestTyp getTestTyp() {
            return testTyp;
        }

        @Override
        public int getModification() {
            return modification;
        }
}
