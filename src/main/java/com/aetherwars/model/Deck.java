package com.aetherwars.model;
import java.util.*;
import java.io.File;


public class Deck {
    private List<Card> cards;
    private int currentSize;
    private final int maxSize = 60;

    Deck() {
        this.cards = new ArrayList<>(maxSize);
        this.currentSize = 0;
    }

    public void addCard(Card card) {
        if (this.currentSize < this.maxSize) {
            this.cards.add(card);
            this.currentSize++;
        }
    }

    public void fillDeck(String filename, CardLibrary lib) {
        ImportDeck importDeck = new ImportDeck(new File(filename));
        try{
            importDeck.readDeck(this, lib);
        }
        catch(Exception e){
        }
    }

    public void fillRandom(CardLibrary lib, Integer deckSize){
        List<Integer> keysAsArray = new ArrayList<Integer>(lib.library.keySet());
        Random r = new Random();
        for (int i = 0; i < deckSize; i++){
            this.addCard(lib.library.get(keysAsArray.get(r.nextInt(keysAsArray.size()))));
        }
    }

    public Card getCard(int position){
        return this.cards.get(position);
    }

    public Card returnCard(int position) {
        currentSize--;
        return this.cards.remove(position);
    }

    public List<Card> showTopThreeCards() {
        List<Card> shownCards = new ArrayList<>();
        Integer tersisa = Math.min(currentSize, 3);
        for (int i = 0; i < tersisa; i++) {
            shownCards.add(this.cards.get(i));
        }
        return shownCards;
    }

    public void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public boolean isEmpty(){
        return this.cards.size() == 0;
    }
}
