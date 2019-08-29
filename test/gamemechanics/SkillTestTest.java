package gamemechanics;

import container.Result;
import enums.FieldID;
import enums.TestType;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;
import model.SkillSet;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SkillTestTest {
    SkillSet skillSet;
    Investigator investigator;
    @BeforeMethod
    public void setUp() {
        skillSet = new SkillSet(3,3,4,2,1);
      //  investigator = new AgnesBaker();
       // investigator.setSkillSet(skillSet);
    }

    @Test
    public void testExecute_Strength_NoMod() {
        SkillTest test = new SkillTest(TestType.STRENGTH, 0);
        Result result = test.execute(investigator);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==2);
        Assert.assertEquals(result.isSuccess(),result.getNumberOfSuccess()>0);
    }

    @Test
    public void testExecute_Observation_NegativeMod() {

        SkillTest test = new SkillTest(TestType.OBSERVATION, -1);
        Result result = test.execute(investigator);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==3);
        Assert.assertEquals(result.isSuccess(),result.getNumberOfSuccess()>0);
    }

    @Test
    public void testExecute_Lore_PositiveMod() {

        SkillTest test = new SkillTest(TestType.LORE, 3);
        Result result = test.execute(investigator);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==6);
        Assert.assertEquals(result.isSuccess(),result.getNumberOfSuccess()>0);
    }

    @Test
    public void testExecute_Invalid_TestTyp() {

        SkillTest test = new SkillTest(null, 3);
        Result result = test.execute(investigator);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==4);
        Assert.assertEquals(result.isSuccess(),result.getNumberOfSuccess()>0);
    }

    @Test
    public void testExecute_Invalid_Investigator() {

        SkillTest test = new SkillTest(TestType.LORE, 3);
        Result result = test.execute(null);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==4);
        Assert.assertEquals(result.isSuccess(),result.getNumberOfSuccess()>0);
    }
}