package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rechtschreibtrainer {

	private int gesamteVersuche;

	private int richtig;

	private int falsch;

	private ArrayList<TrainingsDaten> trainingsDatenListe;

	private TrainingsDaten aktuellesPaar;

	private PersistenceStrategy persistenceStrategy;

	public Rechtschreibtrainer(){
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		trainingsDatenListe = new ArrayList<TrainingsDaten>();
	}

	public Rechtschreibtrainer(ArrayList<TrainingsDaten> trainingsDatenListe){
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		trainingsDatenListe = trainingsDatenListe;
	}



	/**
	 * Diese Methode wählt einen Trainigsdatenset durch einen Index
	 * @param index ist der index zum neu ausgewählendem Trainsingsdatenset
	 */
	public void selectPaar(int index) {
		if (index >= 0 && index < trainingsDatenListe.size()) {
			aktuellesPaar = trainingsDatenListe.get(index);
		} else {
			System.out.println("Index außerhalb der Grenzen!");
		}
	}


	/**
	 * Wählt einen zufälligen Trainingsdatenset
	 */
	public void selectRandomPaar() {
		if (!trainingsDatenListe.isEmpty()) {
			aktuellesPaar = trainingsDatenListe.get((int) (Math.random() * (trainingsDatenListe.size())));
		}
	}

	/**
	 * Fügt neue Trainingsdaten zum Array hinzu
	 * @param wort beschreibung des Bildes
	 * @param url url des Bildes
	 */
	public void addWortBildPaar(String wort, String url) {
		TrainingsDaten neuesPaar = new TrainingsDaten(wort, url);
		if (neuesPaar.checkValid()) {
			trainingsDatenListe.add(neuesPaar);
		} else {
			System.out.println("Ungültige URL, das Paar wurde nicht hinzugefügt.");
		}
	}

	/**
	 *  * Fügt neue Trainingsdaten zum Array hinzu
	 * @param neuesPaar Das neue Paar
	 */
 	public void addWortBildPaar(TrainingsDaten neuesPaar){
		if (neuesPaar.checkValid()) {
			trainingsDatenListe.add(neuesPaar);
		} else {
			System.out.println("Ungültige URL, das Paar wurde nicht hinzugefügt.");
		}
	}

	/**
	 * Gibt die Statistik zurück
	 * Dabei wird die gesamtAnzahl, wie viele Richtig waren, und wie viele Falsch waren zurückgegeben
	 * @return gibt die Statistik in Form eines Textes zurück
	 */
	public String getStatistic() {
		String text = "Von " + gesamteVersuche + " sind " + richtig + " Richtig und " + falsch + " Falsch";
		return text;
	}

	public void updateStatistic(boolean isCorrect) {
		gesamteVersuche++;
		if (isCorrect) {
			richtig++;
		} else {
			falsch++;
		}
	}

	public TrainingsDaten getAktuellesPaar() {
		return aktuellesPaar;
	}

	public ArrayList<TrainingsDaten> getTrainingsDatenListe() {
		return trainingsDatenListe;
	}

	public void setTrainingsDatenListe(ArrayList<TrainingsDaten> trainingsDatenListe) {
		this.trainingsDatenListe = trainingsDatenListe;
	}

	public void setPersistenceStrategy(PersistenceStrategy strategy) {
		this.persistenceStrategy = strategy;
	}

	public void saveSession(String fileName) throws IOException {
		if (persistenceStrategy != null) {
			persistenceStrategy.save(this, fileName);
		}
	}

	public void loadSession(String fileName) throws IOException {
		if (persistenceStrategy != null) {
			Rechtschreibtrainer trainer = persistenceStrategy.load(fileName);
			this.gesamteVersuche = trainer.gesamteVersuche;
			this.richtig = trainer.richtig;
			this.falsch = trainer.falsch;
			this.trainingsDatenListe = trainer.trainingsDatenListe;
			this.aktuellesPaar = trainer.aktuellesPaar;
		}
	}

	public int getGesamteVersuche() { return gesamteVersuche; }
	public void setGesamteVersuche(int gesamteVersuche) { this.gesamteVersuche = gesamteVersuche; }
	public int getRichtig() { return richtig; }
	public void setRichtig(int richtig) { this.richtig = richtig; }
	public int getFalsch() { return falsch; }
	public void setFalsch(int falsch) { this.falsch = falsch; }
	public void setAktuellesPaar(TrainingsDaten aktuellesPaar) { this.aktuellesPaar = aktuellesPaar; }

}
