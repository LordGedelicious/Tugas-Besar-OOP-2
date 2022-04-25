package com.aetherwars.model;

import java.util.List;
import java.util.ArrayList;

public class SummonedChar {
    public Character c;
    public int Exp;
    public int Level;
    public int Exp_need;
    public int max_level;
    public int baseAtk;
    public int baseHp;
    public int max_Atk;
    public int max_Hp;
    public boolean alreadyAttack;
    public List<TempSpell> activeSpells;

    public SummonedChar(Character c) {
        this.c = c;
        this.Exp = 0;
        this.Exp_need = 1;
        this.baseAtk = c.getAttack();
        this.baseHp = c.getHealth();
        this.max_Atk = c.getAttack();
        this.max_Hp = c.getHealth();
        this.Level = 1;
        this.max_level = 10;
        this.alreadyAttack = false;
        this.activeSpells = new ArrayList<TempSpell>();
    }

    public int getExp() {
        return Exp;
    }

    public int getLevel() {
        return Level;
    }
    public void addExp(int exp) {
        if (this.Level != this.max_level) {
            this.Exp += exp;

        // automate level up
            while (this.Exp >= this.Exp_need) {
                this.Exp -= Exp_need;
                this.Exp_need += 2;
                this.levelUp();
            }
        }
    }

    public void addSpell(TempSpell s) {
        this.activeSpells.add(s);
    }

    public void removeSpell(TempSpell s) {
        this.activeSpells.remove(s);
    }

    public void levelUp() {
        if (this.Level < this.max_level) {
            this.Level += 1;
            this.baseAtk += c.getAttackUp();
            this.baseHp += c.getHealthUp();
        }
    }

    public void levelDown() {
        if (this.Level > 1) {
            this.Level -= 1;
            this.baseAtk -= c.getAttackUp();
            this.baseHp -= c.getHealthUp();
        }
    }

    public void resetExp() {
        this.Exp = 0;
    }

    public boolean checkDie() {
        return (this.baseHp <= 0);
    }

    public int calcAttack(SummonedChar sc) {
        int attack = this.baseAtk;
        if (this.c.tipe == TypeChar.NETHER) {
            if (sc.c.tipe == TypeChar.END) {
                attack *= 0.5;
            } else if (sc.c.tipe == TypeChar.OVERWORLD) {
                attack *= 2;
            } else if (sc.c.tipe == TypeChar.NETHER) {
                attack *= 1;
            }
        } else if (this.c.tipe == TypeChar.END) {
            if (sc.c.tipe == TypeChar.END) {
                attack *= 1;
            } else if (sc.c.tipe == TypeChar.OVERWORLD) {
                attack *= 0.5;
            } else if (sc.c.tipe == TypeChar.NETHER) {
                attack *= 2;
            }
        } else if (this.c.tipe == TypeChar.OVERWORLD) {
            if (sc.c.tipe == TypeChar.END) {
                attack *= 2;
            } else if (sc.c.tipe == TypeChar.OVERWORLD) {
                attack *= 1;
            } else if (sc.c.tipe == TypeChar.NETHER) {
                attack *= 0.5;
            }
        }

        return attack;
    }

    public void attackCharacter(SummonedChar sc) {
        this.baseHp -= sc.calcAttack(this);
        sc.baseHp -= this.calcAttack(sc);
        if (this.checkDie()) {
            sc.addExp(this.Level);
        }
        if (sc.checkDie()) {
            this.addExp(sc.Level);
        }
    }

    public void attackPlayer(Player p) {
        p.takeDamage(this.baseAtk);
    }

}
