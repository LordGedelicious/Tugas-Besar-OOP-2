package com.aetherwars.model;

import java.util.*;

public class MORPH extends PermSpell {
    int id_swap_target;
    MORPH(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int id_swap_target) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
    }

    @Override
    public void use() {
        // find id of character

        Character c = new Character(999, "-", TypeChar.END, "-", "-", 0, 0, 0, 0, 0); // change with
        
        getUser().c = c;
        getUser().Exp = 0;
        getUser().Exp_need = 1;
        getUser().baseAtk = c.getAttack();
        getUser().baseHp = c.getHealth();
        getUser().max_Atk = c.getAttack();
        getUser().max_Hp = c.getHealth();
        getUser().Level = 1;
        getUser().max_level = 10;
        getUser().alreadyAttack = false;
        getUser().activeSpells = new ArrayList<TempSpell>();
    }
}
