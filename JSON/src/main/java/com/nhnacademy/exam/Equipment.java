package com.nhnacademy.exam;

public class Equipment {
    String modelName;
    int armor;
    int attack;

    public Equipment(String modelName, int amor, int attack) {
        this.modelName = modelName;
        this.armor = amor;
        this.attack = attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getAttack() {
        return attack;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public String toString() {
        return this.modelName + " " + this.attack + " " + armor;
    }
}
