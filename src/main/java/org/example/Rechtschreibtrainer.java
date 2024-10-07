package org.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rechtschreibtrainer {
	private List<TrainingsDaten> wortBildPaare = new ArrayList<>();
	private TrainingsDaten aktuellesPaar;
	private int gesamteVersuche = 0;
	private int richtigeVersuche = 0;
	private int falscheVersuche = 0; // Neues Feld für falsche Versuche

	// Fügt ein neues Wort-Bild-Paar hinzu
	public void addWortBildPaar(String wort, String urlString) {
		try {
			URL url = new URL(urlString);
			wortBildPaare.add(new TrainingsDaten(wort, url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Wählt ein zufälliges Wort-Bild-Paar aus
	public void waehleZufall() {
		Random random = new Random();
		aktuellesPaar = wortBildPaare.get(random.nextInt(wortBildPaare.size()));
	}

	// Überprüft die Eingabe des Nutzers
	public boolean pruefeAntwort(String eingabe) {
		gesamteVersuche++;
		boolean isRichtig = aktuellesPaar.getWort().equalsIgnoreCase(eingabe);
		if (isRichtig) {
			richtigeVersuche++;
		} else {
			falscheVersuche++;
		}
		return isRichtig;
	}

	// Getter und Setter
	public TrainingsDaten getAktuellesPaar() {
		return aktuellesPaar;
	}

	public int getGesamteVersuche() {
		return gesamteVersuche;
	}

	public void setGesamteVersuche(int gesamteVersuche) {
		this.gesamteVersuche = gesamteVersuche;
	}

	public int getRichtigeVersuche() {
		return richtigeVersuche;
	}

	public void setRichtigeVersuche(int richtigeVersuche) {
		this.richtigeVersuche = richtigeVersuche;
	}

	public int getFalscheVersuche() {
		return falscheVersuche;
	}

	public void setFalscheVersuche(int falscheVersuche) {
		this.falscheVersuche = falscheVersuche;
	}

	public List<TrainingsDaten> getTrainingsDatenListe() {
		return wortBildPaare;
	}

	public void setTrainingsDatenListe(List<TrainingsDaten> wortBildPaare) {
		this.wortBildPaare = wortBildPaare;
	}
}
