package oldVersion.gameMechanics;

import oldVersion.enums.Skills;

public class BoniValue {
	private int value;
	private Skills skill;
	
	public BoniValue(int value, Skills skill) {
		this.value = value;
		this.skill = skill;
	}

	public int getValue() {
		return value;
	}

	public Skills getSkill() {
		return skill;
	}

}
