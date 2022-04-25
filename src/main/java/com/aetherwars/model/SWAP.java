package com.aetherwars.model;

public class SWAP extends TempSpell {
    SWAP(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int Duration) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe, Duration);
    }

    @Override
    public void use() {

        if (getUser().baseAtk == 0) {
            // { TODO } kill character
        }
        // find matching spell
        for (TempSpell s : getUser().activeSpells) {
            if (s.ID == this.ID) {
                s.duration += this.duration;
                return;
            }
        }
        // no match
        swapAction();

    }
    @Override
    public void revert() {
        swapAction();
    }

    public void swapAction() {
        int temp;
        temp = this.getUser().baseAtk;
        this.getUser().baseAtk = this.getUser().baseHp;
        this.getUser().baseHp = temp;
    }
}
