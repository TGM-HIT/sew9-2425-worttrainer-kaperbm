package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonPersistenceStrategy implements PersistenceStrategy {

    @Override
    public void save(Rechtschreibtrainer trainer, String fileName) throws IOException {
        JSONObject jsonObject = new JSONObject();

        // Trainerstatistik speichern
        jsonObject.put("gesamteVersuche", trainer.getGesamteVersuche());
        jsonObject.put("richtig", trainer.getRichtig());
        jsonObject.put("falsch", trainer.getFalsch());

        // Trainingsdaten speichern
        JSONArray trainingDataArray = new JSONArray();
        for (TrainingsDaten daten : trainer.getTrainingsDatenListe()) {
            JSONObject dataObject = new JSONObject();
            dataObject.put("wort", daten.getWort());
            dataObject.put("url", daten.getUrl());
            trainingDataArray.put(dataObject);
        }
        jsonObject.put("trainingsDatenListe", trainingDataArray);

        // Aktuelles Paar speichern
        if (trainer.getAktuellesPaar() != null) {
            JSONObject currentPair = new JSONObject();
            currentPair.put("wort", trainer.getAktuellesPaar().getWort());
            currentPair.put("url", trainer.getAktuellesPaar().getUrl());
            jsonObject.put("aktuellesPaar", currentPair);
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toString(4)); // Schreibt JSON-Daten mit Einrückung
        }
    }

    @Override
    public Rechtschreibtrainer load(String fileName) throws IOException {
        Rechtschreibtrainer trainer = new Rechtschreibtrainer();
        ArrayList<TrainingsDaten> trainingDataList = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());

            // Trainerstatistik laden
            trainer.setGesamteVersuche(jsonObject.getInt("gesamteVersuche"));
            trainer.setRichtig(jsonObject.getInt("richtig"));
            trainer.setFalsch(jsonObject.getInt("falsch"));

            // Trainingsdaten laden
            JSONArray trainingDataArray = jsonObject.getJSONArray("trainingsDatenListe");
            for (int j = 0; j < trainingDataArray.length(); j++) {
                JSONObject dataObject = trainingDataArray.getJSONObject(j);
                TrainingsDaten daten = new TrainingsDaten(dataObject.getString("wort"), dataObject.getString("url"));
                trainingDataList.add(daten);
            }
            trainer.setTrainingsDatenListe(trainingDataList);

            // Aktuelles Paar laden (falls vorhanden)
            if (jsonObject.has("aktuellesPaar")) {
                JSONObject currentPair = jsonObject.getJSONObject("aktuellesPaar");
                TrainingsDaten aktuellesPaar = new TrainingsDaten(currentPair.getString("wort"), currentPair.getString("url"));
                trainer.selectPaar(trainingDataList.indexOf(aktuellesPaar));
            }
        }

        return trainer;
    }
}
