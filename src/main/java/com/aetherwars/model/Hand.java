package com.aetherwars.model;

import java.util.List;
import java.util.ArrayList;

public class Hand{
    public List<Card> inHand;

    public Hand(){
        inHand = new ArrayList<Card>(5);
    }

    public void getThreeFirst(Deck deck){
        int i = 3;
        int j = 0;
        while (i != 0){
            if (deck.getCard(j) != null){
                inHand.add(deck.returnCard(j));
                i--;
            }
            j++;
        }
    }

    public boolean isFull(){
        return inHand.size() == 5;
    }

    public int getSize() {
        return this.inHand.size();
    }

    public void addCard(Card s){
        if (this.isFull()){
            removeCard(0);
        }
        inHand.add(s);
    }

    public void removeCard(int i){
        inHand.remove(i);
    }

    public Card getCard(int i){
        return inHand.get(i);
    }
}

