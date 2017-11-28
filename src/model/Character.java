package model;

public abstract class Character {

	String name;
	
	public Character(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
