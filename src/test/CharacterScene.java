package test;

import java.util.ArrayList;

import game.Settings;
import model.Scene;
import model.Villagers;

public class CharacterScene extends Scene {
	
	private boolean normalScene = false;
	private Villagers villager = Settings.DUMMY_VILLAGER;
	
	private ArrayList<String> options = new ArrayList<>();
	private ArrayList<Integer> values = new ArrayList<>();
	
	private String imgPath = ".//pictures//schwarz.jpg";
	private ArrayList<Scene> nextScenes = new ArrayList<Scene>();
	private int numberOfOptions = 0;
	private int chosenOption = 0;
	
	public CharacterScene(String grundtext, String option1, int value1, String option2, int value2, String option3, int value3, String option4, int value4, Villagers villager) {
		super(grundtext, option1, option2, option3, option4);		
		
		normalScene = false;
		
		this.villager = villager;
		
		for(int i=0; i<5; i++) {
			System.out.println("Erstelle Value "+i);
			values.add(0);
		}
		
		values.set(1, value1);
		values.set(2, value2);
		values.set(3, value3);
		values.set(4, value4);
		
	}

	public boolean isNormalScene() {
		return normalScene;
	}

	public Villagers getVillager() {
		return villager;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public ArrayList<Integer> getValues() {
		return values;
	}

	public String getImgPath() {
		return imgPath;
	}

	public ArrayList<Scene> getNextScenes() {
		return nextScenes;
	}

	public int getNumberOfOptions() {
		return numberOfOptions;
	}

	public int getChosenOption() {
		return chosenOption;
	}
	
}
