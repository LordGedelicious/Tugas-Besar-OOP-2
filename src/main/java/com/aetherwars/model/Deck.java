package com.aetherwars.model;
import java.util.*;


public class Deck {
    private List<Card> cards;
    private int currentSize;
    private final int maxSize = 60;
    private final int minSize = 0;

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

    public void fillDeck() {
        // ini buat ngefill deck pertama kali sampe isinya 60, cuma blm tau gmn cara baca filenya
    }

    public Card returnCard(int position) {
        return this.cards.remove(position);
    }

    public List<Card> showTopThreeCards() {
        List<Card> shownCards = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            shownCards.add(this.cards.get(i));
        }
        return shownCards;
    }

    public void shuffleCards() {
        Collections.shuffle(this.cards);
    }
}
