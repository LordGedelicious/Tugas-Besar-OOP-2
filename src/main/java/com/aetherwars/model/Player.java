package com.aetherwars.model;

import java.lang.Math;

public class Player {
    private String name;
    public Integer HP;
    private Integer maxMana;
    public Integer curMana;
    private Deck deck;
    public Hand hand;

    public Player(String name){
        this.name = name;
        this.HP = 80;
        this.maxMana = 0;
        this.deck = new Deck();
        //this.deck.fillDeck(filename, lib);
        //this.hand = new Hand(this.deck.returnCard(0),this.deck.returnCard(1),this.deck.returnCard(2));
    }

    public String getName() {
        return this.name;
    }

    //MANA
    public void increaseMana(){
        this.maxMana = Math.min(this.maxMana += 1, 10);
    }

    public void resetMana(){
        this.curMana = this.maxMana;
    }

    public void showMana(){
        System.out.println("Mana: " + curMana + "/" + maxMana);
    }

    public void depleteMana(Integer mana) throws Exception{
        Integer tempMana = this.curMana - mana;
        if (tempMana < 0){
            throw new Exception();
        }
        else{
            this.curMana = tempMana;
        }
    }

    //HP
    public void takeDamage(Integer damage){
        System.out.println("Mendapat " + damage + " serangan");
        this.HP = Math.max(0, this.HP-damage);
        if (this.HP <= 0){
            System.out.println("Tewas");
        }
        else{
            System.out.println("Current HP " + this.HP);
        }
    }

    public void showStatus(){
        System.out.println("PLAYER STATUS");
        System.out.println("Name: " + this.name);
        System.out.println("HP: " + HP + "/80");
        showMana();
    }
}
