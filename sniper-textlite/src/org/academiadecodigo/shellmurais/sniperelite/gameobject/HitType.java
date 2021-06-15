package org.academiadecodigo.shellmurais.sniperelite.gameobject;


// Represents all types of hits on a target (and miss) which determine how an object will handle getting shot.
public enum HitType {
    HEADSHOT(0),
    HIT(1),
    MISS(2),
    BALLSEYE(3);

    private int hitNum;

    HitType(int hitNum) {
        this.hitNum = hitNum;
    }
}
