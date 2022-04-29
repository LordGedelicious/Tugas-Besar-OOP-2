package com.aetherwars.model;

public class PTN extends TempSpell {
    public int Attack;
    public int HP;
    public int Duration; // Duration left

    PTN(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int Attack, int HP, int Duration) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe, Duration);
        this.Attack = Attack;
        this.HP = HP;
    }

    // @Override
    // public void use(Character target) {
        
    // }
    @Override
    public void use() {

        // Attack : increase or reduce attack temporarily
        // { TODO }

        // Health : increase or reduce health temporarily
        getUser().baseAtk += this.Attack;
        getUser().baseHp += this.HP;
            
        // Add to active spell
        getUser().addSpell(this);



        
    }
    @Override
    public void revert() {
        // { TODO } remove health or atk effect
        getUser().baseHp -= this.HP;
        getUser().baseAtk -= this.Attack;
    }
}
