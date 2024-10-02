package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Rechtschreibtrainer {
	private int gesamteVersuche;
	private int richtig;
	private int falsch;
	private ArrayList<TrainingsDaten> trainingsDatenListe;
	private TrainingsDaten aktuellesPaar;

	public Rechtschreibtrainer() {
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		trainingsDatenListe = new ArrayList<>();
	}

	public Rechtschreibtrainer(ArrayList<TrainingsDaten> trainingsDatenListe) {
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		this.trainingsDatenListe = trainingsDatenListe;
	}

	public void selectPaar(int index) {
		if (index >= 0 && index < trainingsDatenListe.size()) {
			aktuellesPaar = trainingsDatenListe.get(index);
		} else {
			System.out.println("Index außerhalb der Grenzen!");
		}
	}

	public void selectRandomPaar() {
		if (!trainingsDatenListe.isEmpty()) {
			aktuellesPaar = trainingsDatenListe.get((int) (Math.random() * (trainingsDatenListe.size())));
		}
	}

	public void addWortBildPaar(String wort, String url) throws MalformedURLException {
		URL imageUrl = new URL(url);
		TrainingsDaten neuesPaar = new TrainingsDaten(wort, imageUrl);
		if (neuesPaar.checkValid()) {
			trainingsDatenListe.add(neuesPaar);
		} else {
			System.out.println("Ungültige URL, das Paar wurde nicht hinzugefügt.");
		}
	}

	public String getStatistic() {
		return "Von " + gesamteVersuche + " sind " + richtig + " Richtig und " + falsch + " Falsch.";
	}

	public void recordAttempt(boolean isCorrect) {
		gesamteVersuche++;
		if (isCorrect) {
			richtig++;
		} else {
			falsch++;
		}
	}

	public int getGesamteVersuche() {
		return gesamteVersuche;
	}

	public void setGesamteVersuche(int gesamteVersuche) {
		this.gesamteVersuche = gesamteVersuche;
	}

	public ArrayList<TrainingsDaten> getTrainingsDatenListe() {
		return trainingsDatenListe;
	}

	public void setTrainingsDatenListe(ArrayList<TrainingsDaten> trainingsDatenListe) {
		this.trainingsDatenListe = trainingsDatenListe;
	}

	public JPanel createImageInputPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		if (aktuellesPaar != null) {
			try {
				URL imageUrl = aktuellesPaar.getUrl();
				ImageIcon imageIcon = new ImageIcon(imageUrl);

				// Bild skalieren
				Image image = imageIcon.getImage();
				Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);

				JLabel imageLabel = new JLabel(scaledIcon);
				panel.add(imageLabel, BorderLayout.CENTER);
			} catch (Exception e) {
				System.out.println("Fehler beim Laden des Bildes: " + e.getMessage());
			}
		} else {
			System.out.println("Kein Bild ausgewählt.");
			JLabel messageLabel = new JLabel("Kein Bild verfügbar");
			panel.add(messageLabel, BorderLayout.CENTER);
		}

		JTextField textField = new JTextField(20);
		panel.add(textField, BorderLayout.SOUTH);
		return panel;
	}

	public TrainingsDaten getAktuellesPaar() {
		return aktuellesPaar;
	}

	public int getRichtig() {
		return richtig;
	}

	public void setRichtig(int richtig) {
		this.richtig = richtig;
	}

	public int getFalsch() {
		return falsch;
	}

	public void setFalsch(int falsch) {
		this.falsch = falsch;
	}

	public boolean pruefeAntwort(String eingabe) {
		gesamteVersuche++;
		boolean isRichtig = aktuellesPaar.getWort().equalsIgnoreCase(eingabe);
		if (isRichtig) {
			richtig++;
		}
		return isRichtig;
	}
}
