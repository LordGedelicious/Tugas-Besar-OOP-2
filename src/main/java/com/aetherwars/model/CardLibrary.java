package com.aetherwars.model;

import java.util.HashMap;
import java.io.File;

public class CardLibrary {
    public HashMap<Integer, Card> library;
    public File file;

    public CardLibrary(){
        this.library = new HashMap<Integer,Card>();
    }

    public void fillLibrary(File characterCSVFile, File lvlCSVFile, File morphCSVFile, File ptnCSVFile, File swapCSVFile){
        CardReader reader = new CardReader();
        reader.readCharacter(this, characterCSVFile);
        reader.readLvl(this, lvlCSVFile);
        reader.readPTN(this, ptnCSVFile);
        reader.readMorph(this, morphCSVFile);
        reader.readSwap(this, swapCSVFile);
    }

    public void addCard(Card c) throws Exception{
        if (this.library.containsKey(c.ID)){
            throw new Exception(); //masukan ID kartu salah
        }
        this.library.put(c.ID,c);
    }

    public Card getCardByID(int i){
        return this.library.get(i);
    }
}
