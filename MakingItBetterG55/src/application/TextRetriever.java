package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextRetriever {
    //path to the CSV containing all text in the game
    private static final String CSV_PATH = "MakingItBetterG55/src/making-it-better-text.csv";

    //list containing all text from the CSV
    private List<String[]> translations;

    //index representing current language
    private int currentLanguageIndex;

    //class is initialised by converting the CSV values into a List and currentLanguageIndex set to 0
    public TextRetriever(){
        this.translations = readTranslationsFromCSV(CSV_PATH);
        this.currentLanguageIndex = 0;
    }

    //retrieves text based on provided index and language index
    public String getText(int index, int languageIndex){
        if(index >= 0 && index < translations.size()){
            if(languageIndex >= 0 && languageIndex < translations.get(0).length){
                return translations.get(index)[languageIndex];
            } else {
                return "Invalid Text";
            }
        } else {
            return "Invalid text";
        }
    }

    //changes the current language index
    public void setLanguage(int languageIndex){
        if(languageIndex >= 0 && languageIndex < translations.get(0).length){
            currentLanguageIndex = languageIndex;
        } else {
            System.out.println("No language index found");
        }
    }

    //reads translations from the CSV file and returns a List of string arrays
    private List<String[]> readTranslationsFromCSV(String csvFilePath){
        List<String[]> translations = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                translations.add(row);
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + csvFilePath);
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return translations;
    }



}
