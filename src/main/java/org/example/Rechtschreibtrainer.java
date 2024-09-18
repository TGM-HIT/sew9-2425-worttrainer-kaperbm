package org.example;

import java.util.List;

public class Rechtschreibtrainer {

	private int gesamteVersuche;

	private int richitg;

	private int falsch;

	private List<TrainingsDaten> TrainingsDatenListe;

	private int aktuellerIndex;

	/**
	 * Diese Methode wählt einen Trainigsdatenset durch einen Index
	 * @param index ist der index zum neu ausgewählendem Trainsingsdatenset
	 */
	public void selectPaar(int index) {
		aktuellerIndex = index;
	}


	/**
	 * Wählt einen zufälligen Trainingsdatenset
	 */
	public void selectRandomPaar() {
		aktuellerIndex =  (int) (Math.random() * (TrainingsDatenListe.size()));
	}

	/**
	 * Gibt die Statistik zurück
	 * Dabei wird die gesamtAnzahl, wie viele Richtig waren, und wie viele Falsch waren zurückgegeben
	 * @return gibt die Statistik in Form eines Textes zurück
	 */
	public String getStatistic() {
		String text = "Von " + gesamteVersuche + " sind " + richitg + " Richtig und " + falsch + " Falsch";
		return text;
	}

}
