package com.aetherwars.model;

public interface IHand {

    //Periksa apakah hand/board full
    boolean isPlayer1Full();
    boolean isPlayer2Full();

    //Menambahkan kartu ke hand/board
    void addCardPlayer1(Card c);
    void addCardPlayer2(Card c);

    //Membuang kartu pada index i
    void removeCardPlayer1(int i);
    void removeCardPlayer2(int i);
}

//ntar tambahin aja kalo ada perlu