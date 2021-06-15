package org.academiadecodigo.shellmurais.sniperelite.gameobject.props;

public enum BarrelType {

    // Barrel Types are given specific maxDamage and material.
    PLASTIC(60, "Plastic"),
    WOOD(30, "Wood"),
    METAL(100, "Metal"),
    EXPLOSIVE(20, "Explosive");

    // Max Damage is a barrel's max HP, but it works in reverse. Shots increment it until it is reached, which destroys the barrel.
    private int maxDamage;
    private String material;

    BarrelType(int maxDamage, String material){

        this.maxDamage = maxDamage;
        this.material = material;

    }

    // Getters
    // getMaxDamage is used to check if a barrel has been destroyed or not.
    public int getMaxDamage() {
        return maxDamage;
    }

    // getMaterial is used for Barrel's getMessage method.
    public String getMaterial() {
        return material;
    }
}
