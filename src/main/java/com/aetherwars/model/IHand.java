interface IHand {

    //Periksa apakah hand/board full
    Boolean isFull();

    //Menambahkan kartu ke hand/board
    void addCard(String s);

    //Membuang kartu pada index i
    void removeCard(Integer i);
}

//ntar tambahin aja kalo ada perlu