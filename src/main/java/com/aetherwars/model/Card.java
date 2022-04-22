package com.aetherwars.model;

public class Card {
    public int ID;
    public String Nama;
    public String ImagePath;
    public String Deskripsi;
    public int Mana;

    public Card(int ID, String Nama, String Deskripsi , String ImagePath, int Mana) {
        this.ID = ID;
        this.Nama = Nama;
        this.ImagePath = ImagePath;
        this.Deskripsi = Deskripsi;
        this.Mana = Mana;
    }
}
