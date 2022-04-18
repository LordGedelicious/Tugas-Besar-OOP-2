package com.aetherwars.model;

import java.lang.Math;
import java.util.*;

public class Player {
    private String name;
    //private String description;
    public Integer HP;
    public Integer maxMana;
    public Integer curMana;
    private Integer deckSize;
    private ArrayList<Integer> deck;
    private Hand hand;
    //private Type type;
  
    public Player() {
      this.name = "";
      this.HP = 80;
      this.maxMana = 0;
      this.deck = new ArrayList<Integer>();
      // this.hand = new ArrayList<Integer>(5);
      this.deckSize = 40;
    }

      public Player(String name, Integer deckSize){
          Random rd = new Random();
          this.name = name;
          this.HP = 80;
          this.maxMana = 0;
          this.deck = new ArrayList<Integer>(deckSize); //ntar random
  
          for (int i=0;i<deckSize;i++){
              this.deck.add(rd.nextInt(40));
          }
  
          //this.hand = new ArrayList<Integer>(5);
          this.deckSize = deckSize;
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
  
      public void depleteMana(Integer mana){
          Integer tempMana = this.curMana - mana;
          if (tempMana < 0){
              System.out.println("Not valid input");
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
  
      //DECK
      public ArrayList<Integer> getFromDeck(){
          ArrayList<Integer> top = new ArrayList<Integer>(3);
          int i = 0;
          while (i < deckSize && i != 3){
              top.add(deck.get(i));
              i += 1;
          }
          return top;
      }
  
      public Integer chooseCard(){
          Integer chosen;
          ArrayList<Integer> cards = getFromDeck();
          Scanner obj = new Scanner(System.in);
  
          Integer used = obj.nextInt();
          chosen = cards.get(used);
          deck.remove(used);
          deckSize -= 1;
  
          obj.close();
          return chosen;
      }
}
