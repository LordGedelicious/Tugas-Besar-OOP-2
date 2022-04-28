package com.aetherwars.model;

interface ISummoned {
    public void attackCharacter(SummonedChar sc);
    public void attackPlayer(Player p);
    public int getExp();
    public int getLevel();
}
