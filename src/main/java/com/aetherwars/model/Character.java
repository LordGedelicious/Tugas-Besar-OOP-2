package com.aetherwars.model;

public class Character extends Card {
    public TypeChar tipe;
    public int Attack;
    public int Health;
    public int AttackUp;
    public int HealthUp;

    public Character(int id, String name ,TypeChar tipe, String description , String imagePath, int Attack, int Health, int Mana, int AttackUp, int HealthUp) {
        super(id, name ,description , imagePath, Mana);
        this.Attack = Attack;
        this.Health = Health;
        this.AttackUp = AttackUp;
        this.HealthUp = HealthUp;
        this.tipe = tipe;
    }

    public TypeChar getTipe() {
        return tipe;
    }

    public int getAttack() {
        return Attack;
    }

    public int getHealth() {
        return Health;
    }

    public int getAttackUp() {
        return AttackUp;
    }

    public int getHealthUp() {
        return HealthUp;
    }
}
