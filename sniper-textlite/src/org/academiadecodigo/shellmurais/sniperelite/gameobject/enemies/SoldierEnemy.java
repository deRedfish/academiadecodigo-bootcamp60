package org.academiadecodigo.shellmurais.sniperelite.gameobject.enemies;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.HitType;


// Base enemy, has no special features, simply extends what is already built in Enemy in an instantiable class.

public class SoldierEnemy extends Enemy{

    public void hit(int dmg, HitType hitType) {
        super.hit(dmg, hitType);
    }

}
