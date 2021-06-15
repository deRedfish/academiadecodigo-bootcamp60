package org.academiadecodigo.shellmurais.sniperelite.gameobject;


// Interface that enforces behaviour for all gameObjects that are supposed to be destroyable. In this case that is
// enemies and props. Decor elements (trees) are excluded.
public interface Destroyable {

    boolean isDestroyed();

    void blowUp();

    void hit(int damage, HitType hitType);

}
