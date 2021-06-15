package org.academiadecodigo.shellmurais.sniperelite.gameobject;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.props.*;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.decor.*;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.enemies.*;


// Implements the factory design pattern. All game objects are created here. The exception being SniperRifle which is
// never dynamically generated in this case. One game = one SniperRifle.
public class GameObjectFactory {

    public static GameObject produceObject(){
        int randomSpawner = (int) (Math.random() * 100);

        if (randomSpawner <= 20) {
            return new Tree();
        }

        if (randomSpawner <= 55) {
            return new SoldierEnemy();
        }

        if (randomSpawner <=85) {
            return new ArmouredEnemy();
        }

        return new Barrel();
    }
}
