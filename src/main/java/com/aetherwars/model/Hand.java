package com.aetherwars.model;

import java.util.List;
import java.util.ArrayList;

public class Hand{
    private List<Card> inHand;

    public Hand(){
        inHand = new ArrayList<Card>(5);
    }

    public void getThreeFirst(Deck deck){
        inHand.set(0, deck.returnCard(0));
        inHand.set(1, deck.returnCard(1));
        inHand.set(2, deck.returnCard(2));
    }

    public boolean isFull(){
        if (inHand.size() == 5){
            return true;
        }
        return false;
    }

    public void addCard(Card s){
        if (isFull()){
            //getinput index
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

