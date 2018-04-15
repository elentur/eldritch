package factory;

import container.ItemContainer;
import enums.ElementTyp;
import model.Investigator;
import model.SkillSet;
import model.StartingPossession;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InvestigatorFactoryTest {
    Investigator investigator;

    @BeforeMethod
    public void setUp() throws Exception {
        investigator = new Investigator();
        investigator.setId("&agnesBaker");
        investigator.setFirstName("Agnes");
        investigator.setLastName("Baker");
        investigator.setOccupation("${agnesBakerOccupation}");
        investigator.setSanity(5);
        investigator.setHealth(7);
        investigator.setStartingSpace("London");
        SkillSet skillSet = new SkillSet(4,3,2,2,2);
        investigator.setSkillSet(skillSet);
       ItemContainer<StartingPossession> startingPossessions = new ItemContainer<>();
       startingPossessions.add(new StartingPossession("&profaneTome",1,ElementTyp.ASSET));
        startingPossessions.add(new StartingPossession("&stormOfSpirits",1,ElementTyp.SPELL));
        investigator.setStartingPossessions(startingPossessions);
    }

    @Test
    public void testGetAssets() throws Exception {

        InvestigatorFactory investigatorFactory = new InvestigatorFactory();
        List<Investigator> investigators = investigatorFactory.getInvestigators();
        Assert.assertTrue((investigators.contains(investigator)));
        Assert.assertEquals(investigator.getOccupation(), "The Waitress");
    }

}