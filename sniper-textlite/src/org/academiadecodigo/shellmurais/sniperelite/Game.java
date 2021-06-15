package org.academiadecodigo.shellmurais.sniperelite;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.*;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.props.Barrel;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.weapon.SniperRifle;


/*  See Main.java for full description of the game's purpose.

    Game class brings all the objects together, carries out the game and declares victory when reached.

 */
public class Game {

    // Array of all the game objects which the game will have to iterate through, shooting or skipping, before reaching the end.
    // Can contain any instantiable objects in gameobject/.
    private GameObject[] gameObjects;

    // This game is self-played. The game class uses the SniperRifle class to play as if it was a human player.
    private SniperRifle sniperRifle;

    // Counts the numbers of shots fired in total for all targets.
    private int shotsFired = 0;

    // Constructor takes in a number of objects to generate and go through.
    public Game(int numObjects) {

        this.gameObjects = new GameObject[numObjects];

        // Fills gameObjects with randomly chosen props, enemies and decor.
        createObjects();
        this.sniperRifle = new SniperRifle();
    }

    public void start() {

        // Explosion radius is a variable used by start() to know, on each iteration of the following for loop, if the
        // current index target is still caught up in an explosive barrel's previous explosion.
        // At game start, no explosions have taken place.
        int explosionRadius = 0;

        // Goes through all targets in the objects array.
        for (GameObject target : gameObjects) {

            // Game has no graphics, so some formatting has been given to structure console prints.
            System.out.println("-------- Next target --------");

            // Checks if the target is still in reach of an explosion. Explosions automatically destroy the 2 targets
            // after an exploded barrel.
            if (explosionRadius > 0) {

                // only destroyable targets are blown up. We love trees.
                if (target instanceof Destroyable) {
                    ((Destroyable) target).blowUp();

                    // if one of the exploded targets was another explosive barrel, then there is a chain explosion.
                    if (target instanceof Barrel) {

                        if (((Barrel)target).isExplosive()) {
                            explosionRadius = 2;
                        }
                    }



                } else {
                    System.out.println("You can't destroy trees! Nature is sacred!");
                }

                // For loop will advance, so next target will be farther away from the explosion's start (in array positions).
                explosionRadius--;
                continue;
            }

            if (validEnemy(target)) {

                // Shoots at enemies and props until they are destroyed.
                while (!((Destroyable) target).isDestroyed()) {
                    if (sniperRifle.shoot((Destroyable)target)) {
                        shotsFired++;
                    }
                }

                // if target destroyed was an explosive barrel, it explodes.
                if (target instanceof Barrel) {

                    if (((Barrel)target).isExplosive()) {
                        explosionRadius = 2;
                    }
                }

            }

            //  Prints out a message if object was a piece of decor (AKA tree).
            if (!validEnemy(target)) {
                System.out.println(target.getMessage());
            }

        }

        // Game won console print. The game always wins.
        System.out.println("========= ALL TARGETS DESTROYED. 'MURICA WINS. ==========");
        System.out.println("========= Total shots fired: " + shotsFired + " ==========");
    }


    // Calls GameObjectFactory to fill the array of targets with random objects.
    public void createObjects() {

        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i] = GameObjectFactory.produceObject();
        }
    }

    // True if Destroyable (enemy or prop), False if not (decor).
    public boolean validEnemy(GameObject target) {

        if (target instanceof Destroyable) {
            return true;
        }
        return false;
    }
}
