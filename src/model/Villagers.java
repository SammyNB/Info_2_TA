package model;

public class Villagers extends Character {
	
	private int doubtLevel;
	private boolean superstitious;
	private String imgString;
	
	public Villagers(String name, int doubtLevel, boolean superstitious) {
		super(name);
		this.doubtLevel = doubtLevel;
		this.superstitious = superstitious;
	}
	
	public int getDoubtLevel() {
		return doubtLevel;
	}
	
	public boolean superstitious() {
		return superstitious;
	}

	public boolean isSuperstitious() {
		return superstitious;
	}

	public void setDoubtLevel(int doubtLevel) {
		this.doubtLevel = doubtLevel;
	}
	
	
	
}
