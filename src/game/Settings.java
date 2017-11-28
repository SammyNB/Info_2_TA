package game;

import java.util.HashSet;
import java.util.Iterator;

import model.OptionValue;
import model.Scene;
import model.Villagers;

public class Settings {	
	
	// Valued Scenes
	public static Scene changeValueTest;
	
	// Charakterscenes
	public static Scene characterDummyScene;
	
	// Dummys
	public static Scene DUMMY_SCENE = new Scene("dummy", "dummyOption", "dummyOption", "", "", ".//res//bird.jpg");
	public static Villagers DUMMY_VILLAGER = new Villagers("Horst", 2, true);
	public static OptionValue DUMMY_OPTIONVALUE = new OptionValue();
	
	// Villager List
	public static HashSet<Villagers> villagers = new HashSet<>();
	
	// Szenen
	public static Scene ONE_ONE = new Scene("Du hast gerade deinen Sohn verabschiedet, der beim hiesigen...", "Händler", "Schafhüter", "Henker", "");
	
	public static Scene one_Two_Handler = new Scene("Jetzt wird er mit ihm auf Reisen gehen, und du bleibst alleine im Dorf.", "", ">", "", "");
	public static Scene one_Two_Schaf = new Scene("Jetzt wird er über den Tag nicht im Hause sein.", "", ">", "", "");
	public static Scene one_Two_Henker = new Scene("Er wird bei dir bleiben, aber immer wieder, je nach Bedarf, abgerufen werden.", "", ">", "", "");
	
	public static Scene one_Three_One = new Scene("Als er das Haus verließ, hast du aus dem Augenwinkel einen Schatten gesehen.", "", ">", "", "");
	public static Scene one_Three_Two = new Scene("Schon beim Aufwachen hast du mal wieder frisches Brot gerochen - das kam öfter vor.", "", ">", "", "");
	public static Scene one_Three_Three = new Scene("Besonders seit du dich bei Hans bedankt hast.", "", ">", "", "");
	public static Scene one_Three_Four = new Scene("Woraufhin er meinte das wäre nicht er gewesen.", "", ">", "", "");
	public static Scene one_Three_Five = new Scene("Er wüsste gar nicht wie man Brot backt.", "", ">", "", "");
	public static Scene one_Three_Six = new Scene("Wer war dann dafür verantwortlich?", "", ">", "", "");
	
	public static Scene one_Four_One = new Scene("SWISCH SWISCH", "", "Hallo...?", "", "");
	public static Scene one_Four_Two = new Scene("SWISCH!! BAM! Die Eingangstür geht plötzlich zu.", "", "Uhm...", "", "");
	public static Scene one_Four_Three = new Scene("Langsam wird das echt zu viel.", "", ">", "", "");
	public static Scene one_Four_Four = new Scene("Ich frage mal in der Stadt, irgendwer muss wissen, was vor sich geht.", "", ">", "", "");

	
	public static void createValuedOptions() {
		changeValueTest = new Scene("Hallo, ich ändere die Werte!!!", new OptionValue("Ich bin toll!", getHashSet(new String[] {"Otto", "Peter"}), 50), new OptionValue("Ich mag keinen Käse.", getHashSet(new String[] {"Heinrich", "Peterdd"}), -50), new OptionValue(), new OptionValue()); 
		characterDummyScene = new Scene("Hallo!! Ich bin Peter!", "Du doof!", -5, "Cool, hi", 500, "hmm", 0, "", 0, getVillByName("Peter"));
	}
	
	public static HashSet<Villagers> getHashSet(String[] names) {
		Iterator<Villagers> it = villagers.iterator();
		
		HashSet<Villagers> activeVillagers = new HashSet<>();
		
		while(it.hasNext()) {
			Villagers vill = it.next();
			for(int i=0; i<names.length; i++) {

				if(vill.getName().equals(names[i])) {
					activeVillagers.add(vill);
					System.out.println("Added! | "+vill.getName()+" - "+names[i]);
//				}else {
//					System.out.println("Not added! | "+vill.getName()+" - "+names[i]);
				}
			}
		}
		
		return activeVillagers;
	}
	
	private static Villagers getVillByName(String name) {
		Iterator<Villagers> it = villagers.iterator();
		
		Villagers correctVill;
	
		while(it.hasNext()) {
			Villagers vill = it.next();

			if(vill.getName().equals(name)) {
				correctVill = vill;
				System.err.println(name+" found");
				return correctVill;
			}
		}
		
		return Settings.DUMMY_VILLAGER;
	}
	
	/* Hier werden Szenen miteinander verknüpft!
	 * 
	 */
	public static void defineScenes() {
		ONE_ONE.defineNextSceneForOption(1, one_Two_Handler);
		ONE_ONE.defineNextSceneForOption(2, one_Two_Schaf);
		ONE_ONE.defineNextSceneForOption(3, one_Two_Henker);
		
//		one_Two_Handler.defineNextSceneForOption(2, one_Three_One);
		one_Two_Handler.defineNextSceneForOption(2, characterDummyScene);
		
		one_Two_Schaf.defineNextSceneForOption(2, one_Three_One);
		one_Two_Henker.defineNextSceneForOption(2, one_Three_One);
		
		one_Three_One.defineNextSceneForOption(2, one_Three_Two);
		one_Three_Two.defineNextSceneForOption(2, one_Three_Three);
		one_Three_Three.defineNextSceneForOption(2, one_Three_Four);
	}
	
	public static void createVillagers() {
		villagers.add(new Villagers("Otto", 55, true));
		villagers.add(new Villagers("Peter", 55, true));
		villagers.add(new Villagers("Heinrich", 55, true));

		Iterator iterator = villagers.iterator();
		
		Villagers villagerObject;
		
		while(iterator.hasNext()) {
			villagerObject = (Villagers) iterator.next();
			System.out.println(villagerObject.getName());
		}
		
	}
}
