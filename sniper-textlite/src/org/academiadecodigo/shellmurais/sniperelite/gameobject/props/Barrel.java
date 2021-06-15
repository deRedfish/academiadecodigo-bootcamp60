package org.academiadecodigo.shellmurais.sniperelite.gameobject.props;

import org.academiadecodigo.shellmurais.sniperelite.gameobject.Destroyable;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.GameObject;
import org.academiadecodigo.shellmurais.sniperelite.gameobject.HitType;


/*  Instances of barrel can be of any BarrelType. Their HP works by incrementation towards a maxDamage defined by their BarrelType.
    When it is reached, a 'destroyed' flag is raised.
 */
public class Barrel extends GameObject implements Destroyable {

    private BarrelType barrelType;
    private int currentDamage = 0;
    private boolean destroyed = false;

    public Barrel() {

        //Barrel type is chosen at random when a barrel is spawned by the GameObjectFactory in Game's createObjects() call.
        this.barrelType = BarrelType.values()[(int) (Math.random() * BarrelType.values().length)];
    }

    // Getter to check whether to keep firing or not.
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    // Getter to check if barrel should explode on destruction or not.
    public boolean isExplosive() {
        if (this.barrelType == BarrelType.EXPLOSIVE) {
            return true;
        }
        return false;
    }

    // Barrels can also be caught and destroyed in an explosion.
    @Override
    public void blowUp() {
        destroyed = true;

        // Prints out a message announcing that a chain explosion occured.
        if(this.barrelType == BarrelType.EXPLOSIVE){
            System.out.println("WOW CHAIN EXPLOSION! Combo! This barrel was explosive too!");
            return;
        }

        System.out.println("This barrel was destroyed in an explosion!");
    }


    // Handler for increasing currentDamage on each hit until maxDamage is reached. 'destroyed' flag is then raised.
    @Override
    public void hit(int damage, HitType hitType) {
        currentDamage += damage;

        if (currentDamage >= barrelType.getMaxDamage()) {
            destroyed = true;

            System.out.println(getMessage());
            return;
        }

        System.out.println(getMessage());
    }


    // Prints specific messages depending on barrel type and the 'destroyed' flag.
    public String getMessage() {

        if (destroyed) {

            if (barrelType == BarrelType.EXPLOSIVE) {
                return ("BOOM GOES THE BARREL! Quick, don't look at the explosion!");
            }

            return (barrelType.getMaterial() + " barrel has been destroyed!");
        }
        return (barrelType.getMaterial() + " is still standing! -- " + (barrelType.getMaxDamage() - currentDamage) + " damage left to be destroyed.");
    }
}
