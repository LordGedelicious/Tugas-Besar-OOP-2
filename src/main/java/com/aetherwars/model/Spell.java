package com.aetherwars.model;

public abstract class Spell extends Card {
    public TypeSpell tipe;
    public SummonedChar char_user;

    Spell(int ID, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe) {
        // super(ID, Nama, ImagePath, Deskripsi, Mana);
        super(ID, Nama, Deskripsi, ImagePath, Mana);
        this.tipe = tipe;
    }

    public TypeSpell getType() {
        return this.tipe;
    }

    public void setUser(SummonedChar char_user) {
        // set user character
        this.char_user = char_user;
    }
    public SummonedChar getUser() {
        return this.char_user;
    }

    public void use() {
        
    }


}
