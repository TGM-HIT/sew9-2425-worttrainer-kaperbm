package org.example;

import java.util.ArrayList;
import java.util.List;

public class Rechtschreibtrainer {

	private int gesamteVersuche;

	private int richtig;

	private int falsch;

	private ArrayList<TrainingsDaten> TrainingsDatenListe;

	private TrainingsDaten aktuellesPaar;


	public Rechtschreibtrainer(){
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		TrainingsDatenListe = new ArrayList<TrainingsDaten>();
	}

	public Rechtschreibtrainer(ArrayList<TrainingsDaten> trainingsDatenListe){
		gesamteVersuche = 0;
		richtig = 0;
		falsch = 0;
		TrainingsDatenListe = trainingsDatenListe;
	}



	/**
	 * Diese Methode wählt einen Trainigsdatenset durch einen Index
	 * @param index ist der index zum neu ausgewählendem Trainsingsdatenset
	 */
	public void selectPaar(int index) {
		aktuellesPaar = TrainingsDatenListe.get(index);
	}


	/**
	 * Wählt einen zufälligen Trainingsdatenset
	 */
	public void selectRandomPaar() {
		aktuellesPaar =  TrainingsDatenListe.get((int) (Math.random() * (TrainingsDatenListe.size())));
	}

	/**
	 * Fügt neue Trainingsdaten zum Array hinzu
	 * @param wort beschreibung des Bildes
	 * @param url url des Bildes
	 */
	public void addWortBildPaar(String wort, String url) {
		TrainingsDatenListe.add(new TrainingsDaten (wort, url));
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



}
