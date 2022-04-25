package com.aetherwars.model;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportDeck {
    private File importFile;

    public ImportDeck(File importFile){
        this.importFile = importFile;
    }

    public void readDeck(Deck deck, CardLibrary lib) throws IOException{
        String line;
        FileReader fileReader = new FileReader(this.importFile);
        BufferedReader br = new BufferedReader(fileReader);
        while ((line = br.readLine()) != null){
            Card card = lib.getCardByID(Integer.parseInt(line));
            deck.addCard(card);
        }
        br.close();
    }
}
