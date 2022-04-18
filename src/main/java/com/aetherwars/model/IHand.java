package com.aetherwars.model;

public interface IHand {

    //Periksa apakah hand/board full
    // boolean isPlayer1Full();
    // boolean isPlayer2Full();
    boolean isFull();

    //Menambahkan kartu ke hand/board
    // void addCardPlayer1(Card c);
    // void addCardPlayer2(Card c);
    void addCard(Card c);

    //Membuang kartu pada index i
    // void removeCardPlayer1(int i);
    // void removeCardPlayer2(int i);
    void removeCard(int i);
}

//ntar tambahin aja kalo ada perlu