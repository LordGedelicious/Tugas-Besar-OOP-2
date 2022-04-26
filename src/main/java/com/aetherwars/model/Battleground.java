package com.aetherwars.model;

import java.util.*;

public class Battleground{
    private HashMap<String, SummonedChar> ActiveCard;

    public Battleground(){
        ActiveCard = new HashMap<String, SummonedChar>();
    }

    public boolean isFull(){
        return ActiveCard.size() == 5;
    }

    public void addCard(SummonedChar c, String pos) throws Exception{
        if (this.ActiveCard.containsKey(pos)){
            throw new Exception();
        }
        this.ActiveCard.put(pos, c);
    }

    public void removeChar(String pos){
        this.ActiveCard.remove(pos);
    }

    public SummonedChar getChar(String pos){
        return this.ActiveCard.get(pos);
    }

    public void checkMati() {
        for (Map.Entry<String, SummonedChar> set :
             ActiveCard.entrySet()) {
            if (set.getValue().checkDie()) {
                removeChar(set.getKey());
            }
        }
    }

    public void printBG() {
        for (Map.Entry<String, SummonedChar> set :
             ActiveCard.entrySet()) {
            System.out.println(set.getKey() + "= " + set.getValue().c.Nama);
        }
    }
}
