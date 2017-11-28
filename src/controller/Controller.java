package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;

import game.Settings;
import game.View;
import model.Character;
import model.OptionValue;
import model.Scene;
import model.Villagers;
import test.CharacterScene;

public class Controller {

	private HashSet<Villagers> tempVillHolder = new HashSet<>();
	
	private HashMap<Integer, HashSet<Villagers>> test = new HashMap<>();
	
	private View view;
	
	JPanel imagePanel = View.imagePanel;
	JLabel imageLabel = View.imageLabel;
	ImageIcon image;
	JLabel dLLNumber, energyLabelNumber;
	JButton choice1, choice2, choice3, choice4;
	int doubtLevel, trustLevel, energy, warning;
	
	String chosenOptionString = "";
	int chosenOption = 0;
	Scene currentScene = Settings.DUMMY_SCENE;
	
	java.util.List<String> lines;
	
	String[] toText;
	int clicked = -1;
	String scene;
	boolean done = false;
	
	//I love Chandler :3
	ChoiceHandler cHandler = new ChoiceHandler();	
	
	public Controller(View view) {
		this.view = view;
		
		Settings settings = new Settings();
		
		view.getIntroButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.createMainGame();
				playerSetup();
			}
		});
		
		setListenerAndCommands();
	}
	
	public void setListenerAndCommands() {
		
		System.out.println(view.getButtons().size());
		
		view.getButtons().get(1).setActionCommand("c1");
		view.getButtons().get(2).setActionCommand("c2");
		view.getButtons().get(3).setActionCommand("c3");
		view.getButtons().get(4).setActionCommand("c4");
		
		for(int i=1; i<5; i++) {
			view.getButtons().get(i).addActionListener(cHandler);
		}
	}
	
	
	public void playerSetup() {
		//main character
		//doubtLevel between 0-100
		doubtLevel = 78;
		
		Settings.createVillagers();
		Settings.createValuedOptions();
		Settings.defineScenes();
		printNextScene(Settings.ONE_ONE);
	}
	
	/**
	 * Zeigt die nächste Szene.
	 * @param scene
	 */
	public void printNextScene(Scene scene) {
		currentScene = scene;
		setStuff();
		
		View.hereComesTheText.setText(scene.getGrundtext());
				
		for(int i=1; i<5; i++) {
			view.getButtons().get(i).setText(scene.getOptions().get(i));
			
			if(currentScene.getOptions().get(i).equals("")){
				view.getButtons().get(i).setEnabled(false);
			}else {
				view.getButtons().get(i).setEnabled(true);
			}
		}
		
		// Normal Scene?
		if(scene.isNormalScene()) {
			// Does the scene change values?
			if(scene.isChangesValues()) {
				modifyVillagers(scene);
				System.out.println("\n\tModified Villagers!");
			}
		// Scene is Character Scene
		}else {
			// changes Image
//			modifyVillager(scene);
		}
		
	}
	
	/**
	 * 
	 * @param scene
	 * @return
	 */
	public boolean setOption(Scene scene) {
		
		System.out.println("\n\tGewählte Option: "+Integer.toString(chosenOption));

		scene.setChosenOption(chosenOption);
		System.out.println("\n\tOption saved");
		
		printNextScene(scene.getNextScenes().get(chosenOption));
		System.out.println("\\n\\tNext Scene printed");

		if(!(scene.isNormalScene())) {
			modifyVillager(scene);
		}
		
		return true;
	}
	
	private void modifyVillagers(Scene scene) {

		OptionValue choosenOptionValue = scene.getOptionsValues().get(chosenOption);
		
		Iterator<Villagers> it = choosenOptionValue.getAffectedVillagers().iterator();
		
		while(it.hasNext()) {
			Villagers currentVillager = it.next();

			System.out.println("Wert vorher: "+currentVillager.getDoubtLevel());
			
			int newDoubtLevel = currentVillager.getDoubtLevel() + choosenOptionValue.getValue();
			currentVillager.setDoubtLevel(newDoubtLevel);
			
			System.out.println("Wert nachher: "+Integer.toString(newDoubtLevel));
		}
	}
	
	private void modifyVillager(Scene scene) {

		int value = scene.getValues().get(chosenOption);
		int oldValue = scene.getVillager().getDoubtLevel();
		
		System.out.println("Wert vorher: "+Integer.toString(oldValue));
		scene.getVillager().setDoubtLevel(oldValue + value);
		System.out.println("Wert nachher: "+Integer.toString(scene.getVillager().getDoubtLevel()));
	}

	/**
	 * Setzt Bilder, Musik usw passend zur Szene
	 */
	public void setStuff() {
		System.out.println(currentScene.getGrundtext());
		System.out.println("Test SetStuff: " + currentScene.getGrundtext());
		System.out.println(currentScene.getImgPath());
		displayPicture(imagePanel, imageLabel, image, currentScene.getImgPath());
	}
	
	
//	public void setText(String filePath) {
//		File file = new File(filePath);
//		lines = new ArrayList<String>();
//		String line;
//		
//		try{
//			Scanner scanner = new Scanner(file);
//			while(scanner.hasNext()) {
//				line = scanner.nextLine();
//				lines.add(line);
//			}
//			scanner.close();
//		} catch (FileNotFoundException en) {
//			System.out.println("File not found!");
//		}
//		
//		
//		toText = lines.toArray(new String[]{});
//		resetClicked();
//		clearButtons();
//		choice2.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {	
//				if(clicked < toText.length-1) {
//					clicked++;
//					hereComesTheText.setText(toText[clicked]);
//					System.out.println("bla");
//				}else {
//					done = true;					
//				}
//				System.out.println(done);
//			}
//		});
//	}
	
	
	public void resetClicked() {
		clicked = -1;
	}
	
	//Zum doubtLevel Punkte hinzufügen
	private int addDL(int raise) {
		doubtLevel = doubtLevel + raise;
		return doubtLevel;
	}

	//Immer, wenn sich das Bild ändern soll:
//	private void displayPictures(JPanel imagePanel, JLabel imageLabel, ImageIcon image, String picSrc) {
//		this.imagePanel = imagePanel;
//		image = new ImageIcon(picSrc);
//		imageLabel.setIcon(image);
//		imagePanel.add(imageLabel);
//	}
//
//	public void displayPicture(JPanel imagePanel, JLabel imageLabel, BufferedImage image, String picSrc) {
//		try {
//			imagePanel = View.imagePanel;
//			imageLabel = View.imageLabel;
//			image = ImageIO.read(new File(picSrc));
//			imagePanel.add(imageLabel);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	
	public void displayPicture(JPanel imagePanel, JLabel imageLabel, ImageIcon image, String picSrc) {
		this.imagePanel = View.imagePanel;
		this.imageLabel = View.imageLabel;
		image = new ImageIcon(picSrc); 
		imageLabel.setIcon(image);
		imagePanel.add(imageLabel);
	}
	
	protected ImageIcon createImageIcon(String picSrc) {
		if(picSrc != null) {
			return new ImageIcon(picSrc);
		} else {
			System.out.println("Couldn't find file: " + picSrc);
			return null;
		}
	}
	
	/**
	 * Wird aufgerufen, sobald ein Button gedürckt wurde. ChoosenOption wird gesetzt und setOption aufgerufen.
	 * @author Doktor 2.0
	 *
	 */
	public class ChoiceHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			chosenOptionString = event.getActionCommand();
			
			switch (chosenOptionString) {
			case "c1":
				chosenOption = 1;
				break;
			case "c2":
				chosenOption = 2;
				break;
			case "c3":
				chosenOption = 3;
				break;
			case "c4":
				chosenOption = 4;
				break;

			default:
				break;
			}
			
			setOption(currentScene);
		} //Ende actionPerformed()
	} //Ende ChoiceHandler
//			
//			switch(scene) {
//			case "sceneOne":
//				switch(choosedOptionString) {
//				case "c1": addDL(-2);
//						dLLNumber.setText(doubtLevel + "");
//						hereComesTheText.setText("...eine Ausbildung macht. Jetzt wird er mit ihm auf Reisen gehen,"
//						+ "und du bleibst alleine im Dorf.");
//						sceneThree();
//				case "c2": addDL(1);
//						dLLNumber.setText(doubtLevel + "");
//						hereComesTheText.setText("...eine Ausbildung macht. Jetzt wird er über den Tag nicht im Hause sein.");
//						sceneTwo();
//						
//				break;
//				case "c3": addDL(2); 
//						dLLNumber.setText(doubtLevel + "");
//						hereComesTheText.setText("...eine Ausbildung macht. Er wird bei dir bleiben, muss aber auf Abruf "
//								+ "verfügbar sein.");
//						sceneTwo();
//				break;
//				}
//			}
		//}
}