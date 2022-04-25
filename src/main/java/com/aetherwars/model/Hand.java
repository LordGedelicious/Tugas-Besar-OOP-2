package com.aetherwars.model;

import java.util.List;
import java.util.ArrayList;

public class Hand{
    private List<Card> inHand;

    public Hand(Card card1, Card card2, Card card3){
        inHand = new ArrayList<Card>(5);
        inHand.set(0, card1);
        inHand.set(1, card2);
        inHand.set(2, card3);
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

