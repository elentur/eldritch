package gamemechanics.encounter;

import Service.GameService;
import enums.EncounterType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.choice.InformationChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StandardEncounter extends Encounter {

    private final String encounterID;
    private final Field field;
    private Preparation preparation;

    public StandardEncounter(Investigator inv, String encounterID) {
       this(inv,encounterID,EncounterType.STANDARD_ENCOUNTER);
    }
    public StandardEncounter(Investigator inv, String encounterID, EncounterType encounterType) {
        super(encounterType);
        setInvestigator(inv);
        this.encounterID = encounterID;
        setTestType(new TestType[3]);
        setMinNumberOfSuccesses(new int[]{1,1,1});
        setMod(new int[]{0,0,0});
        setEffect(new Effect[3][2]);
        setSituationType(SituationType.STANDARD_ENCOUNTER);
        setGame(GameService.getInstance());
        this.field = getGame().getFieldOfInvestigator(inv);
    }


    @Override
    public String getNameId() {
        return "${standard_encounter}";
    }


    @Override
    public String getId() {
        return "&standard_encounter";
    }


    public Preparation getPreparation() {
if(preparation ==null){
    preparation = new Preparation(getTestType()[getEncounterPart()], getInvestigator(), getSituationType(), this);
}
        return preparation;
    }

    public String getEncounterStartText() {
        return ResourceUtil.get("${" + encounterID + "_" + field.getType().getText().toLowerCase() + "_start}", getNameId().replaceAll("[{}\\$]", ""));
    }

    public String getEncounterFailText() {
        String key = "${" + encounterID + "_" + field.getType().getText().toLowerCase() + "_fail}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    public String getEncounterSuccessText() {
        String key = "${" + encounterID + "_" + field.getType().getText().toLowerCase() + "_success}";
        String value = ResourceUtil.get(key, getNameId().replaceAll("[{}\\$]", ""));
        if (value.equals(key)) {
            return ResourceUtil.get("${standard}", getNameId().replaceAll("[{}\\$]", ""));
        }
        return value;
    }

    public int completeEncounterPart() {
        String header = "";
        String text = "";
        List<Effect> effects = new ArrayList<>();
        if (result.isSuccess()) {
            header = ResourceUtil.get("${success}", "ui");
            text = getEncounterSuccessText()+"\n"+getEffect()[getEncounterPart()][PASS].getText();
            effects.add(getEffect()[getEncounterPart()][PASS]);
        } else {
            header = ResourceUtil.get("${fail}", "ui");
            text = getEncounterFailText()+"\n"+getEffect()[getEncounterPart()][FAIL].getText();
            effects.add(getEffect()[getEncounterPart()][FAIL]);
        }
        getGame().addChoice(new InformationChoice(header, text,effects ));
        setEncounterPart(3);
        return getEncounterPart();
    }
}
