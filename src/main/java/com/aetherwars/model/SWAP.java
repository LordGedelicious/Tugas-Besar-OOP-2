package com.aetherwars.model;

public class SWAP extends Spell {
    int Duration; // Duration left
    SWAP(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int Duration) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.Duration = Duration;
    }

    public int getDuration() {
        return this.Duration;
    }

    public void DecreaseDuration() {
        this.Duration -= 1;
        if (this.Duration < 0) {
            // { TODO } remove from active spell
            // swap back
            this.swapAction();
        }
    }

    public void swap() {

        if (getUser().baseAtk == 0) {
            // { TODO } kill character
        }

        if (true) { // { TODO } check if in active spell
            // swap action
            swapAction();
            // { TODO } add to active spell
        }
        else {
            // { TODO } add duration to active spell
        }

    }

    public void swapAction() {
        int temp;
        temp = this.getUser().baseAtk;
        this.getUser().baseAtk = this.getUser().baseHp;
        this.getUser().baseHp = temp;
    }



}
