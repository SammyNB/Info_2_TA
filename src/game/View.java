/**
 * TEXTADVENTURE - DIE WAHRE SAGE DES MÜMMELSEES
 * by Sam Bachmann, November 2017
 * Many thanks to Tim Dreger, who helped me with this a lot,
 * and stopped me from despairing over it!
 * 17.11.2017
 */

package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
import model.Character;
import model.Scene;

public class View {
	
	public static JPanel imagePanel = new JPanel();
	public static JTextArea hereComesTheText;
	
	private JButton choice1, choice2, choice3, choice4;
	private ArrayList<JButton> buttons = new ArrayList<>();
	
	
	public static JLabel imageLabel = new JLabel();
	JFrame window;
	Container con;
	JPanel panelIntro, introButtonPanel, panelBeginning, panelButtonBeginning, panelButtons, panelMain, playerPanel, birdPanel;
	JLabel labelIntro, labelBeginning, energyLabel, energyLabelNumber, doubtLevelLabel, dLLNumber, birdLabel;
	Font intro = new Font("Verdana", Font.PLAIN, 45);
	Font runningText = new Font("Verdana", Font.PLAIN, 28);
	Font dialogueText = new Font("Verdana", Font.PLAIN, 20);
	Font playerInfo = new Font("Verdana", Font.PLAIN, 15);
	int doubtLevel, trustLevel, energy, warning;
	JButton introButton, clickMe;
	ImageIcon birdImage;
	
	String chosenOptionString = "";
	int chosenOption = 0;
	Scene currentScene = Settings.DUMMY_SCENE;
	
	java.util.List<String> lines;
	
	JTextArea beginningTextArea, birdText;
	String[] beginningTexts = new String[7];
	String[] toText;
	int clicked = -1;
	String scene;
	boolean done = false;
	
	Character[] characters;
	
	//sayings courtesy of http://sprichwort.gener.at/or/
	String[] sayings = {"Träume sind Goldes wert.", "An der Leine isst der König Brot.", "Auch in einer alten Kirche frisst der Teufel Fliegen.",
			"Die Frau ist besser als Nachsicht.", "Was Hänschen nicht lernt, muss man in den Beinen haben.", "Besser den Spatz in der Hand, als reich und krank.",
			"Ein gebranntes Kind wird endlich gut.", "Zeit ist der erste Weg zur Besserung.", "Wer den Schaden hat, ist der größte Reichtum.", "Wer schön sein will, ist ein Eichhörnchen.",
			"Besser ein Ende mit Schrecken, als langsam verdorben.", "Was dem einen recht ist, bringt Kummer und Sorgen.", "Wer Wind sät, tut selten gut.", "Dem Glücklichen ist übel geigen.",
			"Der Teufel ist dicker als Wasser.", "In der Nacht ist der Hahn König.", "Einem nackten Mann ist übel geigen.", "In der Not liegt die Wahrheit.",
			"Zeit ist halbes Leid.", "Wer flüstert, wird Grillen fangen.", "Die Hoffnung ist des Teufels Hure.", "Wer nichts wird, lacht am besten.", "Aller Anfang verdirbt den Charakter.",
			"Geld allein tut selten gut.", "Armut ist das halbe Leben.", "Argwohn hat keine Taschen.", "Arbeite klug, gegessen wird zuhause.", 
			"Auch Rom rostet nicht.", "Appetit holt man sich woanders, beharren ist eine Kunst.", "Hochmut sticht auch ins rote Meer.", "Wer Wind sät, kommt beim Essen",
			"Rom steckt im Detail.", "Angst ist Geld.", "Jedes Böhnchen ist dicker als Wasser.", "Trautes Heim, kurzer Sinn.", "Im Wein ist man immer klüger.",
			"Der Ton macht lustig.", "Gottes Wege haben kurze Beine.", "Scherben soll man nicht wecken.", "Habgier muss fühlen.", "Kinder und Narren haben auch schöne Töchter.",
			"Ein blindes Huhn lacht am besten.", "Lügen bringt Glück.", "Menschenfleisch ist die Mutter der Porzellankiste.", "Das Leben wurde nicht an einem Tag erbaut.",
			"Ärzte sind Schäume.", "Die Katze kann tief fallen.", "Gott findet seinen Deckel.", "Eigenlob gibt ein Tönchen.", "Klappe zu, alles gut."};
	
	
	public View() {
		window = new JFrame();
		window.setSize(1024, 768);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setResizable(false);
		
		con = window.getContentPane();
		
		panelIntro = new JPanel();
		panelIntro.setBounds(100, 50, 824, 300);
		panelIntro.setBackground(Color.black);
		panelIntro.setLayout(new GridBagLayout());
		
		labelIntro = new JLabel("Die wahre Mümmelseesage");
		labelIntro.setForeground(Color.lightGray);
		labelIntro.setFont(intro);
		labelIntro.setVerticalAlignment(JLabel.CENTER);
		
		panelIntro.add(labelIntro);
		
		introButtonPanel = new JPanel();
		introButtonPanel.setBounds(400, 400, 200, 100);
		introButtonPanel.setBackground(Color.black);
		introButtonPanel.setLayout(new GridBagLayout());
		
		introButton = new JButton("Fange an...");
		introButton.setBackground(Color.black);
		introButton.setForeground(Color.LIGHT_GRAY);
		introButton.setFont(runningText);
		introButton.setFocusPainted(false);
//		introButton.addActionListener(tsHandler);
		
		introButtonPanel.add(introButton);
		
		con.add(panelIntro);
		con.add(introButtonPanel);
		
		window.setVisible(true);
		
		for(int i=0; i<5; i++) {
			buttons.add(new JButton());
		}
	}
	
	public JButton getIntroButton() {
		return introButton;
	}

	public void setIntroButton(JButton introButton) {
		this.introButton = introButton;
	}


	public void createBeginning() {
		panelIntro.setVisible(false);
		introButtonPanel.setVisible(false);
	
		beginningTexts[0] = "Hallo.";
		beginningTexts[1] = "Du bist eine Dorfbewohnerin und hast viel zu tun. Da dein ehemaliger "
				+ "Ehemann verstorben ist, lebst du alleine mit deinem Sohn in einem Dorf im Schwarzwald.";
		beginningTexts[2] = "Frauen, die alleine und mit Kind wohnen, sind nicht sehr hoch angesehen...";
		beginningTexts[3] = "Aber du kommst zurecht. Meistens konzentrierst du dich darauf, das Haus sauber zu halten, den "
				+ "kleinen Garten zu pflegen, und Bücher zu lesen, die der nette Händler dir manchmal mitbringt.";
		beginningTexts[4] = "Etwas ist jedoch merkwürdig an diesem Dorf.";
		beginningTexts[5] = "Aber du denkst nicht weiter darüber nach; du hast genug zu tun.";
		beginningTexts[6] = "Mit diesem Gedanken im Kopf fängt die Geschichte an...";
				
		panelBeginning = new JPanel();
		panelBeginning.setBounds(100, 100, 824, 250);
		panelBeginning.setBackground(Color.darkGray);
		panelBeginning.setLayout(new GridBagLayout());
		
		con.add(panelBeginning);
		
		beginningTextArea = new JTextArea("Hey.");
		beginningTextArea.setBounds(120, 120, 784, 210);
		beginningTextArea.setBackground(Color.DARK_GRAY);
		beginningTextArea.setForeground(Color.white);
		beginningTextArea.setFont(runningText);
		beginningTextArea.setLineWrap(true);
		beginningTextArea.setWrapStyleWord(true);
		
		panelBeginning.add(beginningTextArea);
		
		panelButtonBeginning = new JPanel();
		panelButtonBeginning.setBounds(368, 400, 300, 68);
		panelButtonBeginning.setBackground(Color.black);
		panelButtonBeginning.setLayout(new GridBagLayout());
		con.add(panelButtonBeginning);
		
		clickMe = new JButton(">");
		clickMe.setBackground(Color.black);
		clickMe.setForeground(Color.white);
		clickMe.setFont(runningText);
		clickMe.setFocusPainted(false);
		clickMe.setVerticalAlignment(JButton.CENTER);
		clickMe.addActionListener(new ActionListener(){

		//2 Buttons, jeweils hin und wieder zurück
		public void actionPerformed(ActionEvent e) {
				if (clicked < 6) {
					clicked++;
					beginningTextArea.setText(beginningTexts[clicked]);
				} else if (clicked == 6) {
					createMainGame();
				}
				//System.out.println(clicked);
			}
		});
		
		JButton clickMe2 = new JButton("<");
		clickMe2.setBackground(Color.black);
		clickMe2.setForeground(Color.white);
		clickMe2.setFont(runningText);
		clickMe2.setFocusPainted(false);
		clickMe2.setVerticalAlignment(JButton.CENTER);
		clickMe2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (clicked <= 6) {
					clicked--;
					beginningTextArea.setText(beginningTexts[clicked]);
				} else if (clicked > 6) {
					createMainGame();
				} else if (clicked == 0) {
					beginningTextArea.setText(beginningTexts[0]);
				}
				//System.out.println(clicked);
			}
		});
		
		
		panelButtonBeginning.add(clickMe2);
		panelButtonBeginning.add(clickMe);
		panelBeginning.setVisible(false);
		panelButtonBeginning.setVisible(false);
			
	}
	
	public void createMainGame() {
		//Just the layout for the main game~
		//panelBeginning.setVisible(false);
		//panelButtonBeginning.setVisible(false);
		panelIntro.setVisible(false);
		introButtonPanel.setVisible(false);
		
		panelMain = new JPanel();
		
		con.add(panelMain);
		
		playerPanel = new JPanel();
		playerPanel.setBounds(50, 25, 924, 50);
		playerPanel.setBackground(Color.DARK_GRAY);
		playerPanel.setLayout(new GridLayout(1,4));
		panelMain.add(playerPanel);
		con.add(playerPanel);
		
		energyLabel = new JLabel("   Energie: ");
		energyLabel.setFont(playerInfo);
		energyLabel.setForeground(Color.LIGHT_GRAY);
		playerPanel.add(energyLabel);
		
		energyLabelNumber = new JLabel();
		energyLabelNumber.setFont(playerInfo);
		energyLabelNumber.setForeground(Color.LIGHT_GRAY);
		playerPanel.add(energyLabelNumber);
		
		doubtLevelLabel = new JLabel("Misstrauen: ");
		doubtLevelLabel.setFont(playerInfo);
		doubtLevelLabel.setForeground(Color.LIGHT_GRAY);
		playerPanel.add(doubtLevelLabel);
		
		dLLNumber = new JLabel();
		dLLNumber.setFont(playerInfo);
		dLLNumber.setForeground(Color.LIGHT_GRAY);
		playerPanel.add(dLLNumber);
		playerPanel.add(Box.createRigidArea(new Dimension(50, 50)));
		playerPanel.add(Box.createRigidArea(new Dimension(50, 50)));
		playerPanel.add(Box.createRigidArea(new Dimension(50, 50)));
		playerPanel.add(Box.createRigidArea(new Dimension(50, 50)));
		
		birdText = new JTextArea();
		birdText.setBackground(Color.BLACK);
		birdText.setFont(dialogueText);
		birdText.setForeground(Color.white);
		birdText.setLineWrap(true);
		birdText.setWrapStyleWord(true);
		birdText.setBounds(354, 200, 250, 150);
		birdText.setVisible(false);
		con.add(birdText);
		
		birdPanel = new JPanel();
		birdPanel.setBounds(354, 387, 60, 82);
		birdPanel.setBackground(Color.BLACK);
		birdPanel.addMouseListener(new birdListener());
		try {
			BufferedImage birdPicture = ImageIO.read(new File(".//res//bird_kleiner.jpg"));
			JLabel birdLabel = new JLabel(new ImageIcon(birdPicture));
			birdPanel.add(birdLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		con.add(birdPanel);
		
		hereComesTheText = new JTextArea();
		hereComesTheText.setBounds(55, 100, 285, 370);
		hereComesTheText.setBackground(Color.BLACK);
		hereComesTheText.setForeground(Color.LIGHT_GRAY);
		hereComesTheText.setFont(dialogueText);
		hereComesTheText.setLineWrap(true);
		hereComesTheText.setWrapStyleWord(true);
		panelMain.add(hereComesTheText);
		con.add(hereComesTheText);
		
		imagePanel.setBounds(427, 95, 546, 375);
		imagePanel.setBackground(Color.black);
		panelMain.add(imagePanel);
		con.add(panelMain);
		
		imagePanel.add(imageLabel);
		
		con.add(imagePanel);
		
		panelButtons = new JPanel();
		panelButtons.setBounds(50, 510, 924, 200);
		panelButtons.setBackground(Color.black);
		panelButtons.setLayout(new GridLayout(2, 2));
		con.add(panelButtons);
		
		for(int i=1; i<5; i++) {
			buttons.get(i).setBackground(Color.black);
			buttons.get(i).setForeground(Color.LIGHT_GRAY);
			buttons.get(i).setFont(runningText);
			buttons.get(i).setFocusPainted(false);
			panelButtons.add(buttons.get(i));
		}
	}
	
	public class birdListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
				Timer birdTimer = new Timer(200, event->{
					birdText.setVisible(true);
					int birdInt = (int) (Math.random()*50);
					birdText.setText(sayings[birdInt]);
					Timer endTimer = new Timer(2000, ev->{
						birdText.setVisible(false);
					});
					endTimer.setRepeats(false);
					endTimer.start();
				});
				birdTimer.setRepeats(false);
				birdTimer.start();
		}
		
		public void mouseEntered(MouseEvent e) {
			
		}
		
		public void mousePressed(MouseEvent e) {
			
		}
		
		public void mouseExited(MouseEvent e) {
			
		}
		
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}


	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}
	
	
}