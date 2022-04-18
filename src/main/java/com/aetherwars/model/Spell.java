package com.aetherwars.model;

public class Spell extends Card {
    public TypeSpell tipe;
    public Character char_user;

    Spell(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe) {
        // super(ID, Nama, ImagePath, Deskripsi, Mana);
        this.tipe = tipe;
    }

    public TypeSpell getType() {
        return this.tipe;
    }

    public void setUser(Character char_user) {
        // set user character
        this.char_user = char_user;
    }
    public Character getUser() {
        return this.char_user;
    }

    // public void use(Character target) { // virtual
    //     System.out.println("Spell used");
    // }


}
