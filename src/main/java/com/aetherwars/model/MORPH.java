package com.aetherwars.model;

import java.util.*;

import com.aetherwars.AetherWars;

public class MORPH extends PermSpell {
    public int id_swap_target;
    public Character char_swap_target;

    MORPH(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, int id_swap_target) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.id_swap_target = id_swap_target;
    }

    public void setTarget(Character c) {
        this.char_swap_target = c;
    }

    @Override
    public void use() {
        getUser().c = char_swap_target;
        getUser().Exp = 0;
        getUser().Exp_need = 1;
        getUser().baseAtk = char_swap_target.getAttack();
        getUser().baseHp = char_swap_target.getHealth();
        getUser().max_Atk = char_swap_target.getAttack();
        getUser().max_Hp = char_swap_target.getHealth();
        getUser().Level = 1;
        getUser().max_level = 10;
        getUser().alreadyAttack = false;
        getUser().activeSpells = new ArrayList<TempSpell>();
    }
}
