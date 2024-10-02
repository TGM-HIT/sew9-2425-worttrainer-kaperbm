package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonPersistenceStrategy {

    public void save(Rechtschreibtrainer trainer, String filePath) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gesamteVersuche", trainer.getGesamteVersuche());
        jsonObject.put("richtig", trainer.getRichtig());
        jsonObject.put("falsch", trainer.getFalsch());

        // Umwandlung von TrainingsDatenListe in JSON
        JSONArray jsonArray = new JSONArray();
        for (TrainingsDaten daten : trainer.getTrainingsDatenListe()) {
            JSONObject datenObject = new JSONObject();
            datenObject.put("wort", daten.getWort());
            datenObject.put("url", daten.getUrl().toString());
            jsonArray.put(datenObject);
        }
        jsonObject.put("trainingsDatenListe", jsonArray);

        // Speichern der JSON-Daten in eine Datei
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString(4)); // Formatierung der Ausgabe mit Einrückung
        }
    }

    public Rechtschreibtrainer load(String filePath) throws IOException {
        StringBuilder jsonString = new StringBuilder();

        // Einlesen der JSON-Datei
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
        }

        // Erstellen des Trainer-Objekts
        Rechtschreibtrainer trainer = new Rechtschreibtrainer();
        JSONObject jsonObject = new JSONObject(jsonString.toString());

        trainer.setGesamteVersuche(jsonObject.getInt("gesamteVersuche"));
        trainer.setRichtig(jsonObject.getInt("richtig"));
        trainer.setFalsch(jsonObject.getInt("falsch"));

        // Laden der TrainingsDatenListe
        JSONArray jsonArray = jsonObject.getJSONArray("trainingsDatenListe");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject datenObject = jsonArray.getJSONObject(i);
            String wort = datenObject.getString("wort");
            String url = datenObject.getString("url");
            trainer.addWortBildPaar(wort, url);
        }

        return trainer;
    }
}
