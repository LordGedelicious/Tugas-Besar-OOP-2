package com.aetherwars.model;

import java.lang.ProcessBuilder.Redirect.Type;

public class Main {

    public static void test_1() {
        Player p1 = new Player("Budi", 40);
        Player p2 = new Player("Charlie", 40);
        p1.showStatus();
    }

    public static void tesChar() {
        Character c1 = new Character(1, "Dinosaurus", TypeChar.NETHER, "-", "-", 10, 100, 5, 2, 2);
        Character c2 = new Character(2, "Semut Kecil", TypeChar.END, "-", "-", 5, 150, 5, 3, 1);
        Character c3 = new Character(3, "Kalajengking bersayap", TypeChar.OVERWORLD, "-", "-", 20, 80,7, 2, 2);
        Character c4 = new Character(4, "Lapu-lapu", TypeChar.NETHER, "-", "-", 5, 250,5, 2, 2);

        SummonedChar sc1 = new SummonedChar(c1);
        SummonedChar sc2 = new SummonedChar(c2);
        SummonedChar sc3 = new SummonedChar(c3);

        PTN ptn1 = new PTN(11, "Rafaela", "-", "-", 5, TypeSpell.PTN, 5, -2, 15);
        LVL lvl1 = new LVL(12, "Will Smith Potion", "-", "-", 2, TypeSpell.LVL, "UP");
        MORPH morph1 = new MORPH(13, "Plagiarism", "-", "-", 2, TypeSpell.MORPH, 4);
        SWAP swap1 = new SWAP(14, "Tukeran", "-", "-", 5, TypeSpell.SWAP, 3);

        Battleground bg1 = new Battleground();
        Battleground bg2 = new Battleground();
        try {
            bg1.addCard(sc1, "A");
        } catch (Exception e) {

        }

        try {
            bg2.addCard(sc2, "A");   
        } catch (Exception e) {

        }

        bg1.printBG();
        bg2.printBG();

        ptn1.setUser(bg1.getChar("A"));
        ptn1.use();
        

        bg1.getChar("A").attackCharacter(bg2.getChar("A"));
        bg1.checkMati();
        bg2.checkMati();


        // sc2.printInfo();
        // sc1.attackCharacter(sc2);
        // System.out.println("berantem");
        // sc1.printInfo();
        // sc2.printInfo();

        
    }


    

    public static void main(String[] args) {
        // CardLibrary cLib = new CardLibrary();
        // cLib.fillLibrary();
        // System.out.println(cLib.getCardByID(1).Nama);
        // test_1();
        // System.out.println("halo2");
        System.out.println("halo");
        tesChar();

    }
}
/*
javac com/aetherwars/model/Main.java
java com.aetherwars.model/*.java
*/