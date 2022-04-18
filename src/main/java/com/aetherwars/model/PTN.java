package com.aetherwars.model;

public class PTN extends Spell {
    int Attack;
    int HP;
    int Duration; // Duration left
    PTN(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int Attack, int HP, int Duration) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.Attack = Attack;
        this.HP = HP;
        this.Duration = Duration;
    }

    // @Override
    // public void use(Character target) {
        
    // }

    public int getDuration() {
        return this.Duration;
    }

    public void DecreaseDuration() {
        this.Duration -= 1;
        if (this.Duration < 0) {
            // { TODO } remove from active spell
            // { TODO } remove extra health and attack
        }
    }

    public void potion() {
        // add character target to attribute


        // Attack : increase or reduce attack temporarily
        // { TODO }

        // Health : increase or reduce health temporarily
        // { TODO }
            // kill if HP effect + target HP <= 0
            if (this.HP + this.getUser().Health <= 0) {
                // { TODO } kill character
            }
            
        // Add to active spell
        // { TODO }
    }
}
