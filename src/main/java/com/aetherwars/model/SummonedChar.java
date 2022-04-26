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
        if (TypeChar.NETHER.equals(this.c.tipe)) {
            if (TypeChar.END.equals(sc.c.tipe)) {
                attack *= 0.5;
            } else if (TypeChar.OVERWORLD.equals(sc.c.tipe)) {
                attack *= 2;
            } else if (TypeChar.NETHER.equals(sc.c.tipe)) {
                attack *= 1;
            }
        } else if (TypeChar.END.equals(this.c.tipe)) {
            if (TypeChar.END.equals(sc.c.tipe)) {
                attack *= 1;
            } else if (TypeChar.OVERWORLD.equals(sc.c.tipe)) {
                attack *= 0.5;
            } else if (TypeChar.NETHER.equals(sc.c.tipe)) {
                attack *= 2;
            }
        } else if (TypeChar.OVERWORLD.equals(this.c.tipe)) {
            if (TypeChar.END.equals(sc.c.tipe)) {
                attack *= 2;
            } else if (TypeChar.OVERWORLD.equals(sc.c.tipe)) {
                attack *= 1;
            } else if (TypeChar.NETHER.equals(sc.c.tipe)) {
                attack *= 0.5;
            }
        }
        else {
            System.out.println("here");
        }
       
        return attack;
    }

    public void attackCharacter(SummonedChar sc) {
        int enemyDmg = sc.calcAttack(this);
        int allyDmg = this.calcAttack(sc);
        int tempDmg;

        for (TempSpell ts : this.activeSpells) {
            if (ts instanceof PTN) {
            PTN temp_ptn = (PTN) ts;
            // only to + buff
            if (temp_ptn.HP > 0) {
                tempDmg = Math.min(temp_ptn.HP, enemyDmg);
                temp_ptn.HP -= tempDmg;
                enemyDmg -= tempDmg;
            } 
            if (enemyDmg == 0) {
                break;
            }
        }
        }
        for (TempSpell ts : sc.activeSpells) {
            if (ts instanceof PTN) {
            PTN temp_ptn = (PTN) ts;
            // only to + buff
            if (temp_ptn.HP > 0) {
                tempDmg = Math.min(temp_ptn.HP, allyDmg);
                temp_ptn.HP -= tempDmg;
                allyDmg -= tempDmg;
            } 
            if (allyDmg == 0) {
                break;
            }
        }
        }

        this.baseHp -= enemyDmg;
        
        sc.baseHp -= allyDmg;
        if (this.checkDie()) {
            sc.addExp(this.Level);
        }
        if (sc.checkDie()) {
            this.addExp(sc.Level);
        }
        this.alreadyAttack = true;




    }

    public int getBuffedHealth() {
        int ret = this.baseHp;
        for (TempSpell ts : this.activeSpells) {
            if (ts instanceof PTN) {
            PTN temp_ptn = (PTN) ts;
            if (temp_ptn.HP > 0) {
            ret += temp_ptn.HP;
            }
            }

        }
        return ret;
    }


    public void attackPlayer(Player p) {
        p.takeDamage(this.baseAtk);
    }

    public void printInfo() {
        System.out.println("=======PRINTINFO========");
        System.out.println("c= " + c.Nama);
        System.out.println("Exp= " + Exp);
        System.out.println("Level= " + Level);
        System.out.println("Exp_need= " + Exp_need);
        System.out.println("max_level= " + max_level);
        System.out.println("base_Atk= " + baseAtk);
        System.out.println("baseHp= " + baseHp);
        System.out.println("max_Atk= " + max_Atk);
        System.out.println("max_hp= " + max_Hp);
        System.out.println("already attack= " + alreadyAttack);

        for (TempSpell t : activeSpells){
            System.out.println(t.Nama);
        }
    }

    public void DecreaseSpellDuration() {
        for (TempSpell ts : activeSpells) {
            ts.DecreaseDuration();
        }
    }
}
