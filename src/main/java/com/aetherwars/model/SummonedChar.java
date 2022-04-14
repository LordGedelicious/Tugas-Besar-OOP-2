package com.aetherwars.model;

public class SummonedChar implements ISummoned {
    public int Exp;
    public int Level;
    public int Exp_need;
    public int Level_need;
    public int baseAtk;
    public int baseHp;

    public int getLevel() {
        return 0;
    }

    public int getExp() {
        return 0;
    }
  
  
    // meningkatkan level karakter sebanyak 1
    // mereset xp ke 0
    // meningkatkan baseAtk dan baseHp sebanyak attackUp dan healthUp
    public void levelUp() {}
}
