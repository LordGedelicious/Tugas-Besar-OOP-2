package com.aetherwars.model;

import java.util.*;

public class Battleground implements IHand{
    private List<Card> ActiveCard;

    public Battleground(){
        ActiveCard = new ArrayList<Card>(5);
    }

    public boolean isFull(){
        return ActiveCard.size() == 5;
    }

    public void addCard(Card c){
        this.ActiveCard.add(c);
    }

    public void removeCard(int i){
        this.ActiveCard.remove(i);
    }
}
