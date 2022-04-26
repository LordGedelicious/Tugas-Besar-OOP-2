package com.aetherwars.model;

public class Main {

    public static void test_1() {
        Player p1 = new Player("Budi", 40);
        Player p2 = new Player("Charlie", 40);
        p1.showStatus();
    }
    

    public static void main(String[] args) {
        CardLibrary cLib = new CardLibrary();
        cLib.fillLibrary();
        System.out.println(cLib.getCardByID(1).Nama);
        test_1();
        System.out.println("halo2");
    }
}
/*
javac com/aetherwars/model/Main.java
java com.aetherwars.model/*.java
*/