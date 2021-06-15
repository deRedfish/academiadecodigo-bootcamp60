package org.academiadecodigo.shellmurais.sniperelite.gameobject.weapon;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.Destroyable;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.HitType;


// Pew pew object class. Handles shooting bad guys, and is only called by Game.
public class SniperRifle {

    // Damage done to enemies and props per hit.
    private int bulletDamage = 50;

    // Chance of provoking Headshots or crotchshots.
    private int critChance = 10;

    // Chance of hit not connecting.
    private int missChance = 10;

    // Max possible shots before reloading.
    private int maxBullets = 5;

    //Bullets left in clip.
    private int bulletsLeft = 5;

    //Shoots at given target. Generates a random number from 0 to 100 to check for hit/ miss and critical hits.
    public boolean shoot(Destroyable target) {

        if (bulletsLeft == 0) {
            System.out.println("Out of ammo! Reloading!");
            bulletsLeft = maxBullets;
            return false;
        }

        HitType hitRoll = hitRoll();
        bulletsLeft--;

        if (hitRoll == HitType.MISS) {
            System.out.println("Damn! Missed!");
            return true;
        }

        target.hit(bulletDamage, hitRoll);
        return true;
    }

    public HitType hitRoll(){
        int hitDie = (int) (Math.random() * 101);
        if (hitDie <= 80) {
            return HitType.HIT;
        }
        if (hitDie <= 90) {
            return HitType.MISS;
        }
        if (hitDie <= 95) {
            return HitType.BALLSEYE;
        }
        return HitType.HEADSHOT;
    }
}
