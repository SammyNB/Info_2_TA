package model;

import java.util.HashSet;

public class OptionValue {

	private String optionText = "";
	private HashSet<Villagers> affectedVillagers = new HashSet<>();
	private int value = 0;
	
	public OptionValue(String optionText, HashSet<Villagers> affectedVillagers, int value) {
		this.optionText = optionText;
		this.affectedVillagers = affectedVillagers;
		this.value = value;
	}
	
	public OptionValue() {
	}

	public String getOptionText() {
		return optionText;
	}

	public HashSet<Villagers> getAffectedVillagers() {
		return affectedVillagers;
	}

	public int getValue() {
		return value;
	}
}
