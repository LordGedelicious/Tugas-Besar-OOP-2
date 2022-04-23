package com.aetherwars.model;

public class LVL extends Spell {
    int Amount;
    LVL(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.Amount = 1;
    }

    public int getAmount() {
        return this.Amount;
    }

    // @Override
    // public void use(Character target) {
        
    // }

    public void level(String direction) {
        // level up
        if (direction == "UP") {
            this.getUser().levelUp();
        }
        // level down
        else {
            this.getUser().levelDown();
        }
    }
    
}
