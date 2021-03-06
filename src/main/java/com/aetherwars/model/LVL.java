package com.aetherwars.model;

public class LVL extends PermSpell {
    public String direction; // up or down

    LVL(int iD, String Nama, String ImagePath, String Deskripsi, int Mana, TypeSpell tipe, String direction) {
        super(iD, Nama, ImagePath, Deskripsi, Mana, tipe);
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setManaLVL(){
        this.Mana = (int) Math.ceil(this.getUser().Level/2);
    }

    @Override
    public void use() {
        // level up
        if (direction.equals("UP")) {
            this.getUser().levelUp();
        }
        // level down
        else {
            this.getUser().levelDown();
        }
        this.getUser().resetExp();

    }
    
}
