package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        Rechtschreibtrainer trainer = new Rechtschreibtrainer();
        JsonPersistenceStrategy persistence = new JsonPersistenceStrategy();
        String filePath = "/home/kacper/IdeaProjects/sew9-2425-worttrainer-kaperbm/src/main/java/org/example/worttrainer.json";

        // Überprüfen, ob die Datei existiert, um Daten zu laden
        File file = new File(filePath);
        if (file.exists()) {
            try {
                trainer = persistence.load(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Initiale Wort-Bild-Paare hinzufügen, wenn keine Datei existiert
            // trainer.addWortBildPaar("Spike", "https://res.ldrescdn.com/faq/images/en/e9d37afc-54cf-46bf-85b3-a20174af6eed.jpg");
            // trainer.addWortBildPaar("Spike", "https://res.ldrescdn.com/faq/images/en/e9d37afc-54cf-46bf-85b3-a20174af6eed.jpg");
            // trainer.addWortBildPaar("Spike", "https://res.ldrescdn.com/faq/images/en/e9d37afc-54cf-46bf-85b3-a20174af6eed.jpg");
        }

        // GUI-Loop für den Rechtschreibtrainer
        while (true) {
            trainer.selectRandomPaar();
            TrainingsDaten aktuellesPaar = trainer.getAktuellesPaar();
            ImageIcon imageIcon = new ImageIcon(aktuellesPaar.getUrl());

            // Eingabeaufforderung mit Bild
            JPanel panel = new JPanel();
            JLabel label = new JLabel(imageIcon);
            JTextField textField = new JTextField(10); // Eingabefeld für das Wort
            panel.add(label);
            panel.add(textField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Wie heißt das Bild?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String eingabe = textField.getText().trim(); // Eingabe des Benutzers

                if (eingabe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bitte gib ein Wort ein."); // Warnung, wenn das Feld leer ist
                    continue; // Schleife neu starten
                }

                boolean isRichtig = trainer.pruefeAntwort(eingabe);
                String nachricht = isRichtig ? "Richtig!" : "Falsch! Das richtige Wort ist: " + aktuellesPaar.getWort();
                JOptionPane.showMessageDialog(null, nachricht);
            } else {
                break; // Schleife brechen, wenn Abbrechen gewählt wurde
            }
        }

        // Persistieren der Daten beim Programmende
        try {
            persistence.save(trainer, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
