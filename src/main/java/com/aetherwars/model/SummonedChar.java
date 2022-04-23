package com.aetherwars.model;

public class SummonedChar {
    private Character c;
    private int Exp;
    private int Level;
    private int Exp_need;
    private int max_level;
    public int baseAtk;
    public int baseHp;
    public boolean alreadyAttack;

    public SummonedChar(Character c) {
        this.c = c;
        this.Exp = 0;
        this.Exp_need = 1;
        this.baseAtk = c.getAttack();
        this.baseHp = c.getHealth();
        this.Level = 1;
        this.max_level = 10;
        this.alreadyAttack = false;
    }

    public int getExp() {
        return Exp;
    }

    public int getLevel() {
        return Level;
    }
    public void addExp(int exp) {
        this.Exp += exp;

        // automate level up
        levelUp();
    }

    public void levelUp() {
        while (this.Exp >= this.Exp_need && this.Level < this.max_level) {
            this.Exp -= Exp_need;
            this.Exp_need += 2;
            this.Level += 1;
            this.baseAtk += c.getAttackUp();
            this.baseHp += c.getHealthUp();
        }
    }

    public void levelDown() {
        // TODO
    }

    public boolean checkDie() {
        if (this.baseHp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void attack(SummonedChar sc) {
        this.baseHp -= sc.baseAtk;
        sc.baseHp -= this.baseAtk;
        if (this.checkDie()) {
            sc.addExp(this.Level);
        }
        if (sc.checkDie()) {
            this.addExp(sc.Level);
        }
    }
}
