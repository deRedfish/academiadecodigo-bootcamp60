package org.academiadecodigo.shellmurais.sniperelite.gameobject.enemies;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.HitType;


/*  ArmouredEnemy is an Enemy.
    His particularity is that he has a second health bar, which represents his armour. His HP only decrements either when
    the 'armor HP bar' reaches 0 or when critical hits are scored (crotchshots, forgive the immaturity).
 */
public class ArmouredEnemy extends Enemy {

    // armour represents the amount of armor 'HP' this type of enemy has on top of HP inherited from super.
    private int armour = 100;


    /*  Handles this enemy's specific behaviour on getting hit.
        Adds handling crotchshots which skip armour and go straight to HP.
        HitTypes are being passed through the SniperRifle class.
     */
    public void hit(int dmg, HitType hitType) {

        // Critical hits go straight through armour.
        if (hitType == HitType.BALLSEYE) {
            System.out.println("'MOTHERF*CKER!' -- CRITICAL! Armor ignored...");
            super.hit(dmg, hitType);
            return;
        }

        /*  When armour reaches 0, hp is depleted instead. If there is a damage overflow from armour HP (which would set
            it negative), the overflow is passed onto HP as damage.
        */
        if (this.armour > 0) {

            if (this.armour - dmg < 0) {
                this.armour = 0;
                super.hit(Math.abs(this.armour - dmg), hitType);
                return;
            }

            this.armour = this.armour - dmg;

            if (hitType == HitType.HEADSHOT) {
                System.out.println("'My helmet protected me, yankee! Better luck next time!' -- Target has " + this.armour + " armour left!");
                return;
            }

            System.out.println("'You're gonna have to hit harder than that!' -- Target has " + this.armour + " armour left!");
            return;
        }
        super.hit(dmg, hitType);
        return;
    }

}
