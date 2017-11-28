package model;

import java.util.ArrayList;

import game.Settings;

public class Scene {
	
	private boolean normalScene = true;
	private boolean changesValues = false;
	
	private String grundtext = "";
	
	// ArrayListen
	private ArrayList<String> options = new ArrayList<>();
	private ArrayList<OptionValue> optionsValues = new ArrayList<>();
	private ArrayList<Integer> values = new ArrayList<>();
	
	private String imgPath = ".//pictures//schwarz.jpg";
	
	private ArrayList<Scene> nextScenes = new ArrayList<Scene>();
	
	private int numberOfOptions = 0;
	private int chosenOption = 0;
	private Villagers villager = Settings.DUMMY_VILLAGER;
	
	/**
	 * Konstruktor für Szenen ohne Bild/Ton-Änderung
	 * 
	 */
	public Scene(String grundtext, String option1, String option2, String option3, String option4) {
		System.out.println("\n\tErstelle Szene.");
		
		initiate();
		
		this.grundtext = grundtext;
		
		options.set(1, option1);
		options.set(2, option2);
		options.set(3, option3);
		options.set(4, option4);
	}
	
	/**
	 * 
	 * Konstruktor für Szene mit Bild/Ton-Änderung
	 * 
	 */
	public Scene(String grundtext, String option1, String option2, String option3, String option4, String imgPath) {
		System.out.println("\n\tErstelle Szene.");
		initiate();
		
		this.grundtext = grundtext;
		this.imgPath = imgPath;

		options.set(1, option1);
		options.set(2, option2);
		options.set(3, option3);
		options.set(4, option4);
	}
	
	/**
	 * Konstruktor für Szenen, die Werte ändern
	 *
	 */
	public Scene(String grundtext, OptionValue ov1, OptionValue ov2, OptionValue ov3, OptionValue ov4) {
		System.out.println("\n\tErstelle Szene mit Valueanbindung.");
		
		initiate();
		
		changesValues = true;
		
		for(int i=0; i<5; i++) {
			optionsValues.add(Settings.DUMMY_OPTIONVALUE);
		}
		
		this.grundtext = grundtext;
		optionsValues.set(1, ov1);
		optionsValues.set(2, ov2);
		optionsValues.set(3, ov3);
		optionsValues.set(4, ov4);
		
	}
	
	/**
	 * 
	 * Konstruktor für Charakterszenen!
	 *
	 */
	public Scene(String grundtext, String option1, int value1, String option2, int value2, String option3, int value3, String option4, int value4, Villagers villager) {
		System.out.println("\n\tErstelle Szene.");
		initiate();
		
		this.grundtext = grundtext;
		options.set(1, option1);
		options.set(2, option2);
		options.set(3, option3);
		options.set(4, option4);
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
	
	private void initiate() {
		for(int i=0; i<5; i++) {
			System.out.println("Erstellt für index "+i);
			nextScenes.add(Settings.DUMMY_SCENE);
		}
		
		for(int i=0; i<5; i++) {
			System.out.println("Erstelle Option "+i);
			options.add("");
		}
		
	}
	
	//Verlinkt Szenen miteinander (siehe Settings)
	public void defineNextSceneForOption(int numberOfOption, Scene scene) {
		nextScenes.set(numberOfOption, scene);
	}

	public ArrayList<Scene> getNextScenes() {
		return nextScenes;
	}

	public void setNextScenes(ArrayList<Scene> nextScenes) {
		this.nextScenes = nextScenes;
	}

	public String getGrundtext() {
		return grundtext;
	}

	public String getImgPath() {
		return imgPath;
	}
	
	public boolean isNormalScene() {
		return normalScene;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
	public ArrayList<Integer> getValues() {
		return values;
	}

	public Villagers getVillager() {
		return villager;
	}

	public void setGrundtext(String grundtext) {
		this.grundtext = grundtext;
	}

	public int getNumberOfOptions() {
		return numberOfOptions;
	}

	public void setNumberOfOptions(int numberOfOptions) {
		this.numberOfOptions = numberOfOptions;
	}

	public int getChosenOption() {
		return chosenOption;
	}

	public void setChosenOption(int chosenOption) {
		this.chosenOption = chosenOption;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public boolean isChangesValues() {
		return changesValues;
	}

	public ArrayList<OptionValue> getOptionsValues() {
		return optionsValues;
	}
	
}
