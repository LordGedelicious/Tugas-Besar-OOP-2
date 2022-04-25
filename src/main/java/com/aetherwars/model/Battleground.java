package com.aetherwars.model;

import java.util.*;

public class Battleground{
    private List<SummonedChar> ActiveCard;

    public Battleground(){
        ActiveCard = new ArrayList<SummonedChar>(5);
    }

    public boolean isFull(){
        return ActiveCard.size() == 5;
    }

    public void addCard(SummonedChar c){
        this.ActiveCard.add(c);
    }

    public void removeChar(int i){
        this.ActiveCard.remove(i);
    }

    public SummonedChar getChar(int i){
        return this.ActiveCard.get(i);
    }
}
