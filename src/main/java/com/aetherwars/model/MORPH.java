package com.aetherwars.model;

public class MORPH extends Spell {
    Character char_swap_target;
    MORPH(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
    }

    public void setTarget(Character target) {
        this.char_swap_target = target;
    }
    public Character getTarget() {
        return this.char_swap_target;
    }

    
    public void morph() {
        // copy attribute
        getUser().Attack = getTarget().Attack;
        getUser().AttackUp = getTarget().AttackUp;
        getUser().Deskripsi = getTarget().Deskripsi;
        getUser().Exp = getTarget().Exp;
        getUser().Health = getTarget().Health;
        getUser().HealthUp = getTarget().HealthUp;
        getUser().ImagePath = getTarget().ImagePath;
        getUser().Level = getTarget().Level;
        getUser().Mana = getTarget().Mana;
        getUser().Nama = getTarget().Nama;
        getUser().tipe = getTarget().tipe;

        // reset lvel and exp
        getUser().Level = 1;
        getUser().Exp = 0;
        
    }
}
