package com.aetherwars.model;

public class TempSpell extends Spell {
    public int duration;
    TempSpell(int ID, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int duration) {
        super(ID, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.duration = duration;
    }
    public void use() {
        
    }
    public int getDuration() {
        return this.duration;
    }
    public void DecreaseDuration() {
        this.duration -= 1;
    }
    public void revert() {

    }
}
