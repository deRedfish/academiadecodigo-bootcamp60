package org.academiadecodigo.shellmurais.sniperelite.gameobject.enemies;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.Destroyable;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.GameObject;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.HitType;


/*  Enemies are the game's targets. The game is won by finishing all of them (depleting HP to 0).
    This is an super class with no instantiation. Only its specific varieties (Amoured and Soldier) are created.
    Properties and behaviour described here are shared by all specific enemy types  (e.g. HP is always 100).
 */
abstract public class Enemy extends GameObject implements Destroyable {

    // health is HP. Enemy dies when it reaches 0.
    private int health = 100;
    // isDead flag is raised when HP reaches 0. Signals to the game that it can move on to next target.
    private boolean isDead = false;

    // Getter used to check if isDead flag has been raised.
    public boolean isDead() {
        return isDead;
    }

    // Enemy implements Destroyable (game only fires at destroyable objects, does not fire at trees).
    @Override
    public boolean isDestroyed() {
        return isDead();
    }

    // Implemented from Destroyable interface. Is used as the kill method, which raises isDead flag, when the enemy is
    // caught in an explosion's radius (from explosive barrels being destroyed).
    @Override
    public void blowUp() {
        isDead = true;
        System.out.println("This enemy was caught up in an explosion and died a fiery death!");
    }

    // Handles common getting hit behaviour from all enemy types.
    // Hits do base damage, headshots do 2x damage, crotchshots do 3x damage (forgive the immaturity).
    public void hit(int dmg, HitType hitType) {

        switch (hitType) {

            case HIT:
                this.health = this.health - dmg;
                System.out.println("Body hit for " + dmg +" damage!");
                break;

            case HEADSHOT:
                this.health = this.health - (dmg * 2);
                System.out.println("HEADSHOT! Hit for " + (dmg * 2) + " damage!");
                break;

            case BALLSEYE:
                this.health = this.health - (dmg * 3);
                System.out.println("BALLSHOT! Hit for " + (dmg * 3) + " damage!");
                break;

        }

        // If HP is below 0 after this hit, raise isDead flag.
        if (this.health <= 0) {
            this.isDead = true;
        }

        // Prints out the enemy's response to being shot.
        System.out.println(getMessage());
    }

    public String getMessage() {

        // Says "Ah I'm dead" if dead and "Ouch" if simply wounded.
        if (health <= 0) {
            return ("'Ai mataram-me!' -- Target eliminated.");
        }
        return ("'Ui!' -- Target at " + this.health + " HP!");
    }
}
