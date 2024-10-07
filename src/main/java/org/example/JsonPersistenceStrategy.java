package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonPersistenceStrategy {

    // Speichert die Daten in einer JSON-Datei
    public void save(Rechtschreibtrainer trainer, String filePath) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gesamteVersuche", trainer.getGesamteVersuche());
        jsonObject.put("richtig", trainer.getRichtigeVersuche());
        jsonObject.put("falsch", trainer.getFalscheVersuche());

        JSONArray datenArray = new JSONArray();
        for (TrainingsDaten daten : trainer.getTrainingsDatenListe()) {
            JSONObject paar = new JSONObject();
            paar.put("wort", daten.getWort());
            paar.put("url", daten.getUrl().toString());
            datenArray.put(paar);
        }
        jsonObject.put("trainingsDatenListe", datenArray);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toString(4)); // 4 für die Einrückung
        }
    }

    // Lädt die Daten aus einer JSON-Datei
    public Rechtschreibtrainer load(String filePath) throws Exception {
        Rechtschreibtrainer trainer = new Rechtschreibtrainer();
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
        trainer.setGesamteVersuche(jsonObject.getInt("gesamteVersuche"));
        trainer.setRichtigeVersuche(jsonObject.getInt("richtig"));
        trainer.setFalscheVersuche(jsonObject.getInt("falsch"));

        JSONArray datenArray = jsonObject.getJSONArray("trainingsDatenListe");
        List<TrainingsDaten> datenListe = new ArrayList<>();
        for (int i = 0; i < datenArray.length(); i++) {
            JSONObject paar = datenArray.getJSONObject(i);
            String wort = paar.getString("wort");
            URL url = new URL(paar.getString("url"));
            datenListe.add(new TrainingsDaten(wort, url));
        }
        trainer.setTrainingsDatenListe(datenListe);

        return trainer;
    }
}
